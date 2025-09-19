/**
 * Main GitHub Grader Script (Refactored)
 * Orchestrates the grading process using modular services
 */

import { CACHE_CONFIG, GRADING_STATUS } from './config';
import { GitHubApiService } from './github-api';
import { GradingService } from './grading-service';
import { SheetsService } from './sheets-service';
import { ProcessResult, PullRequest, SyncSummary } from './types';
import { Utils } from './utils';

/**
 * Main function to sync GitHub PRs with the grading sheet
 * This can be called manually or set up as a time-driven trigger
 */
function syncGitHubPRs(): void {
  const startTime = Date.now();
  const ui = SpreadsheetApp.getUi();
  
  try {
    Utils.logInfo("Starting GitHub PR sync");
    
    // Validate sheets exist
    const sheetValidation = SheetsService.validateSheets();
    if (!sheetValidation.valid) {
      ui.alert('Missing Sheets', `Please ensure you have the following sheets: ${sheetValidation.missingSheets.join(', ')}`, ui.ButtonSet.OK);
      return;
    }
    
    // Get all recent PRs
    const pullRequests = Utils.performanceWrapper('getRecentPullRequests', 
      () => GitHubApiService.getRecentPullRequests()
    );
    
    if (pullRequests.length === 0) {
      ui.alert('No PRs Found', 'No recent pull requests found to process.', ui.ButtonSet.OK);
      return;
    }
    
    // Filter out already processed PRs
    const existingPrNumbers = SheetsService.getExistingPrNumbers();
    const unprocessedPRs = Utils.filterUnprocessedPRs(pullRequests, existingPrNumbers);
    
    Utils.logInfo(`Found ${pullRequests.length} total PRs, ${unprocessedPRs.length} unprocessed`);

    const summary = processMultiplePRs(unprocessedPRs, startTime);
    
    const finalSummary = Utils.createSyncSummary(
      pullRequests.length,
      summary.processedCount,
      summary.updatedCount,
      summary.results,
      summary.errors
    );
    
    Logger.log(finalSummary);
    ui.alert('Sync Complete', finalSummary, ui.ButtonSet.OK);

  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Utils.logError("Error in syncGitHubPRs: " + errorMessage);
    ui.alert('Error', 'Failed to sync PRs: ' + errorMessage, ui.ButtonSet.OK);
  }
}

/**
 * Process multiple PRs with time and count limits
 */
function processMultiplePRs(pullRequests: PullRequest[], startTime: number): SyncSummary {
  const summary: SyncSummary = {
    processedCount: 0,
    updatedCount: 0,
    results: [],
    errors: []
  };

  for (const pr of pullRequests) {
    // Check time and count limits
    if (Utils.checkTimeLimit(startTime, CACHE_CONFIG.MAX_PROCESSING_TIME) || 
        summary.processedCount >= CACHE_CONFIG.MAX_PRS_PER_RUN) {
      Utils.logInfo(`Stopping early: processed ${summary.processedCount} PRs due to limits`);
      break;
    }
    
    try {
      const result = processPullRequest(pr);
      summary.processedCount++;
      
      if (result.updated) {
        summary.updatedCount++;
      }
      
      if (result.message) {
        summary.results.push(`${pr.user.login}: ${result.message}`);
      }
      
    } catch (error) {
      const errorMessage = error instanceof Error ? error.message : String(error);
      summary.errors.push(`${pr.user.login}: ${errorMessage}`);
      Utils.logError(`Error processing PR #${pr.number}: ${errorMessage}`);
    }
  }

  return summary;
}

/**
 * Process a single pull request and update the sheet if needed
 */
function processPullRequest(pr: PullRequest): ProcessResult {
  try {
    const prUrl: string = pr.html_url;
    const githubUser: string = pr.user.login;

    // 1) Get full student name from roster
    const studentName = SheetsService.lookupStudentName(githubUser);
    if (!studentName) {
      return {updated: false};
    }

    // 2) Get changed files and determine lesson number
    const changedFiles = GitHubApiService.getPullRequestFiles(pr.number);
    const lessonNumber = Utils.extractMaxLessonNumber(changedFiles);

    if (!lessonNumber) {
      return {updated: false};
    }

    // 3) Find matching row in assignments sheet
    const targetRow = SheetsService.findAssignmentRow(lessonNumber, studentName);
    if (targetRow === -1) {
      return {updated: false};
    }

    // 4) Check if PR is already recorded and graded
    const currentPrUrl = SheetsService.getCurrentPrUrl(targetRow);
    const gradingStatus = SheetsService.getCurrentGradingStatus(targetRow);
    
    const prHyperlink = Utils.createPrHyperlink(prUrl, pr.number);
    
    // If PR link exists and submission column is not empty, skip processing
    if (currentPrUrl === prHyperlink && gradingStatus && gradingStatus !== "") {
      return {updated: false, message: "Already graded"};
    }
    
    // Skip if there's already a different PR URL/hyperlink in the cell 
    if (currentPrUrl !== "" && currentPrUrl !== prHyperlink) {
      return {updated: false};
    }
    
    // 5) Update PR URL only if cell is empty (first submission for this student/lesson)
    if (currentPrUrl === "") {
      SheetsService.updatePrUrl(targetRow, prHyperlink);
    }

    // 6) Perform grading if not already done
    if (!gradingStatus || gradingStatus === "") {
      const gradingResult = GradingService.gradePullRequest(pr, lessonNumber, changedFiles, studentName);
      if (gradingResult.success && gradingResult.functionalScore !== undefined) {
        // Update scores and status
        SheetsService.updateGradingResults(
          targetRow,
          gradingResult.functionalScore,
          gradingResult.technicalScore || 0,
          gradingResult.stretchScore || 0,
          GRADING_STATUS.RECEIVED
        );
        
        return {updated: true, message: `Graded: F${gradingResult.functionalScore}/T${gradingResult.technicalScore}`};
      } else if (gradingResult.error && !gradingResult.error.includes("No grading instructions found")) {
        // Only mark as ERROR if it's not just missing GRADING-COPILOT.md
        SheetsService.updateGradingStatus(targetRow, "ERROR");
        return {updated: false, message: `Grading failed: ${gradingResult.error}`};
      }
      // If no GRADING-COPILOT.md found, just skip grading (don't mark as error)
    }

    // Return success if PR was newly added (even if grading was skipped)
    if (currentPrUrl === "") {
      return {updated: true, message: "PR added (grading skipped - no criteria found)"};
    }

    return {updated: false};

  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Utils.logError("Error processing PR: " + errorMessage);
    return {updated: false, message: `Error processing PR: ${errorMessage}`};
  }
}

// ==============================================
// SETUP AND CONFIGURATION FUNCTIONS
// ==============================================

/**
 * Set up automatic syncing with time-driven triggers
 */
function setupAutoSync(): void {
  // Delete existing triggers for this function
  const triggers = ScriptApp.getProjectTriggers();
  triggers.forEach(trigger => {
    if (trigger.getHandlerFunction() === 'syncGitHubPRs') {
      ScriptApp.deleteTrigger(trigger);
    }
  });

  // Create new trigger to run every hour
  ScriptApp.newTrigger('syncGitHubPRs')
    .timeBased()
    .everyHours(1)
    .create();

  SpreadsheetApp.getUi().alert('Auto-sync Setup', 'Automatic PR syncing has been set up to run every hour.', SpreadsheetApp.getUi().ButtonSet.OK);
}

/**
 * Disable automatic syncing
 */
function disableAutoSync(): void {
  const triggers = ScriptApp.getProjectTriggers();
  let deletedCount = 0;
  
  triggers.forEach(trigger => {
    if (trigger.getHandlerFunction() === 'syncGitHubPRs') {
      ScriptApp.deleteTrigger(trigger);
      deletedCount++;
    }
  });

  const message = deletedCount > 0 ? 
    `Deleted ${deletedCount} automatic sync trigger(s).` : 
    'No automatic sync triggers found.';
    
  SpreadsheetApp.getUi().alert('Auto-sync Disabled', message, SpreadsheetApp.getUi().ButtonSet.OK);
}

/**
 * Helper function to setup GitHub token
 */
function setupGitHubToken(): void {
  const ui = SpreadsheetApp.getUi();
  const response = ui.prompt(
    'GitHub Token Setup',
    'Enter your GitHub personal access token:\n(Needs "repo" scope for private repos or "public_repo" for public repos)',
    ui.ButtonSet.OK_CANCEL
  );

  if (response.getSelectedButton() === ui.Button.OK) {
    const token = response.getResponseText().trim();
    if (token) {
      PropertiesService.getScriptProperties().setProperty("GITHUB_TOKEN", token);
      ui.alert('Success', 'GitHub token has been saved securely.', ui.ButtonSet.OK);
    } else {
      ui.alert('Error', 'Please enter a valid token.', ui.ButtonSet.OK);
    }
  }
}

/**
 * Helper function to setup OpenAI API key
 */
function setupOpenAIKey(): void {
  const ui = SpreadsheetApp.getUi();
  const response = ui.prompt(
    'OpenAI API Key Setup',
    'Enter your OpenAI API key:\n(Required for ChatGPT-powered grading. Get one from https://platform.openai.com/api-keys)',
    ui.ButtonSet.OK_CANCEL
  );

  if (response.getSelectedButton() === ui.Button.OK) {
    const apiKey = response.getResponseText().trim();
    if (apiKey) {
      PropertiesService.getScriptProperties().setProperty("OPENAI_API_KEY", apiKey);
      ui.alert('Success', 'OpenAI API key has been saved securely.\n\nChatGPT-powered grading is now enabled!', ui.ButtonSet.OK);
    } else {
      ui.alert('Error', 'Please enter a valid API key.', ui.ButtonSet.OK);
    }
  }
}

// ==============================================
// UI AND MENU FUNCTIONS
// ==============================================

/**
 * Menu creation for manual operations and configuration
 */
function onOpen(): void {
  const ui = SpreadsheetApp.getUi();
  ui.createMenu('GitHub Grader')
    .addItem('🔄 Sync GitHub PRs Now', 'syncGitHubPRs')
    .addSeparator()
    .addItem('⚙️ Setup GitHub Token', 'setupGitHubToken')
    .addItem('🤖 Setup OpenAI API Key', 'setupOpenAIKey')
    .addItem('🕐 Setup Auto-Sync (Hourly)', 'setupAutoSync')
    .addItem('🛑 Disable Auto-Sync', 'disableAutoSync')
    .addSeparator()
    .addItem('🧪 Test Student Lookup', 'testStudentLookup')
    .addItem('🧪 Test Grading System', 'testGradingSystem')
    .addItem('📋 View Sync Logs', 'viewLogs')
    .addItem('🧹 Clear Cache', 'clearAllCache')
    .addToUi();
}

/**
 * Initialize the add-on (run this once after setup)
 */
function initializeAddon(): void {
  const ui = SpreadsheetApp.getUi();
  
  // Check if required sheets exist
  const sheetValidation = SheetsService.validateSheets();
  if (!sheetValidation.valid) {
    ui.alert(
      'Missing Sheets',
      `Please ensure you have the following sheets: ${sheetValidation.missingSheets.join(', ')}`,
      ui.ButtonSet.OK
    );
    return;
  }
  
  // Check if GitHub token is set
  const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
  if (!token) {
    const response = ui.alert(
      'GitHub Token Required',
      'You need to set up a GitHub token first. Would you like to do that now?',
      ui.ButtonSet.YES_NO
    );
    
    if (response === ui.Button.YES) {
      setupGitHubToken();
    }
    return;
  }
  
  // Check if OpenAI API key is set (required for grading)
  const openaiKey = PropertiesService.getScriptProperties().getProperty("OPENAI_API_KEY");
  if (!openaiKey) {
    const response = ui.alert(
      'OpenAI API Key Required',
      'ChatGPT-powered grading requires an OpenAI API key.\n\nWithout it, grading will be skipped.\n\nWould you like to set it up now?',
      ui.ButtonSet.YES_NO
    );
    
    if (response === ui.Button.YES) {
      setupOpenAIKey();
    }
  }
  
  ui.alert(
    'Initialization Complete',
    'GitHub Grader add-on is ready to use!\n\nUse the "GitHub Grader" menu to:\n• Sync PRs manually\n• Set up automatic syncing\n• Test functionality\n\nGrading will automatically occur for lessons that have GRADING-COPILOT.md files.\n\n' +
    (openaiKey ? '🤖 AI-powered grading enabled!' : '⚠️ No OpenAI key configured - grading will be skipped'),
    ui.ButtonSet.OK
  );
}

// ==============================================
// TEST AND DEBUG FUNCTIONS  
// ==============================================

/**
 * Test function for student lookup
 */
function testStudentLookup(): void {
  const ui = SpreadsheetApp.getUi();
  const response = ui.prompt(
    'Test Student Lookup',
    'Enter a GitHub username to test student lookup:',
    ui.ButtonSet.OK_CANCEL
  );

  if (response.getSelectedButton() === ui.Button.OK) {
    const githubUser = response.getResponseText();
    const studentName = SheetsService.lookupStudentName(githubUser);
    
    if (studentName) {
      ui.alert('Success', `Found student: ${studentName}`, ui.ButtonSet.OK);
    } else {
      ui.alert('Not Found', `No student found for GitHub user: ${githubUser}`, ui.ButtonSet.OK);
    }
  }
}

/**
 * Test the grading system with a sample PR
 */
function testGradingSystem(): void {
  const ui = SpreadsheetApp.getUi();
  const response = ui.prompt(
    'Test Grading System',
    'Enter a PR number to test the grading system:',
    ui.ButtonSet.OK_CANCEL
  );

  if (response.getSelectedButton() === ui.Button.OK) {
    const prNumberText = response.getResponseText();
    const prNumber = parseInt(prNumberText, 10);
    
    if (isNaN(prNumber)) {
      ui.alert('Error', 'Please enter a valid PR number.', ui.ButtonSet.OK);
      return;
    }

    try {
      const pr = GitHubApiService.getPullRequest(prNumber);
      if (!pr) {
        ui.alert('Error', `PR #${prNumber} not found.`, ui.ButtonSet.OK);
        return;
      }

      const changedFiles = GitHubApiService.getPullRequestFiles(prNumber);
      const lessonNumber = Utils.extractMaxLessonNumber(changedFiles);
      const studentName = SheetsService.lookupStudentName(pr.user.login);

      if (!lessonNumber) {
        ui.alert('Test Result', `PR #${prNumber}: No lesson number detected from changed files.`, ui.ButtonSet.OK);
        return;
      }

      if (!studentName) {
        ui.alert('Test Result', `PR #${prNumber}: Student not found for GitHub user ${pr.user.login}.`, ui.ButtonSet.OK);
        return;
      }

      // Test grading
      const gradingResult = GradingService.gradePullRequest(pr, lessonNumber, changedFiles, studentName);
      
      if (gradingResult.success) {
        ui.alert(
          'Test Successful',
          `PR #${prNumber} grading test completed:\n\n` +
          `Student: ${studentName}\n` +
          `Lesson: ${lessonNumber}\n` +
          `Functional Score: ${gradingResult.functionalScore}/10\n` +
          `Technical Score: ${gradingResult.technicalScore}/10\n` +
          `Stretch Score: ${gradingResult.stretchScore}/10\n` +
          `Draft Review: ${gradingResult.reviewCreated ? 'Created' : 'Failed'}`,
          ui.ButtonSet.OK
        );
      } else {
        ui.alert('Test Failed', `Grading failed: ${gradingResult.error}`, ui.ButtonSet.OK);
      }

    } catch (error) {
      const errorMessage = error instanceof Error ? error.message : String(error);
      ui.alert('Error', `Test failed: ${errorMessage}`, ui.ButtonSet.OK);
    }
  }
}

/**
 * View recent logs
 */
function viewLogs(): void {
  const ui = SpreadsheetApp.getUi();
  ui.alert(
    'View Logs', 
    'To view execution logs:\n\n1. Go to Extensions → Apps Script\n2. Click on "Executions" in the left sidebar\n3. Click on any execution to see detailed logs\n\nOr check the "Stackdriver Logging" for more detailed logs.',
    ui.ButtonSet.OK
  );
}

/**
 * Clear all cached data
 */
function clearAllCache(): void {
  SheetsService.clearCache();
  GitHubApiService.clearCache();
  
  SpreadsheetApp.getUi().alert(
    'Cache Cleared',
    'All cached data has been cleared. Next sync will fetch fresh data.',
    SpreadsheetApp.getUi().ButtonSet.OK
  );
}

// ==============================================
// LEGACY COMPATIBILITY FUNCTIONS
// ==============================================

/**
 * Legacy function - kept for backwards compatibility
 */
function getChangedFiles(prApiUrl: string): string[] {
  const prNumber = Utils.extractPrNumberFromApiUrl(prApiUrl);
  return prNumber ? GitHubApiService.getPullRequestFiles(prNumber) : [];
}

/**
 * Legacy function - kept for backwards compatibility  
 */
function extractMaxLessonNumber(files: string[]): string | null {
  return Utils.extractMaxLessonNumber(files);
}

/**
 * Legacy function - kept for backwards compatibility
 */
function lookupStudentName(githubUser: string): string | null {
  return SheetsService.lookupStudentName(githubUser);
}