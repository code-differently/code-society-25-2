/**
 * Configuration constants
 */
const SHEET_NAMES = {
  ASSIGNMENTS: "Assignments",
  ROSTER: "Roster"
} as const;

const GRADES_COLUMNS = {
  LESSON_NUMBER: 1,  // Column A
  STUDENT_NAME: 2,   // Column B  
  PR_URL: 12,        // Column L
  FUNCTIONAL_SCORE: 5, // Column E
  TECHNICAL_SCORE: 7,  // Column G
  GRADING_STATUS: 11    // Column K
} as const;

const ROSTER_COLUMNS = {
  FULL_NAME: 1,       // Column A
  GITHUB_USERNAME: 4  // Column D
} as const;

// GitHub repository configuration
const GITHUB_CONFIG = {
  OWNER: "code-differently",
  REPO: "code-society-25-2",
  CURRICULUM_REPO: "25-2-curriculum",
  API_BASE: "https://api.github.com"
} as const;

/**
 * Main function to sync GitHub PRs with the grading sheet
 * This can be called manually or set up as a time-driven trigger
 */
function syncGitHubPRs(): void {
  try {
    const ui = SpreadsheetApp.getUi();
    
    // Get all open and recently closed PRs
    const pullRequests = getRecentPullRequests();
    
    if (pullRequests.length === 0) {
      ui.alert('No PRs Found', 'No recent pull requests found to process.', ui.ButtonSet.OK);
      return;
    }

    let updatedCount = 0;
    const results: string[] = [];

    // Process each PR
    for (const pr of pullRequests) {
      const result = processPullRequest(pr);
      if (result.updated) {
        updatedCount++;
      }
      if (result.message) {
        results.push(`${pr.user.login}: ${result.message}`);
      }
    }

    // Show summary
    const summary = `Processed ${pullRequests.length} PRs, updated ${updatedCount} rows.\n\n` +
                   results.join('\n');
    
    Logger.log(summary);
    ui.alert('Sync Complete', summary, ui.ButtonSet.OK);

  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Logger.log("Error in syncGitHubPRs: " + errorMessage);
    SpreadsheetApp.getUi().alert('Error', 'Failed to sync PRs: ' + errorMessage, SpreadsheetApp.getUi().ButtonSet.OK);
  }
}

/**
 * Process a single pull request and update the sheet if needed
 */
function processPullRequest(pr: any): {updated: boolean, message?: string} {
  try {
    const prUrl: string = pr.html_url;
    const prFormatted: string = `#${pr.number}`;
    const githubUser: string = pr.user.login;

    // 1) Get full student name from roster
    const studentName = lookupStudentName(githubUser);
    if (!studentName) {
      return {updated: false};
    }

    // 2) Get changed files and determine lesson number
    const changedFiles = getPullRequestFiles(pr.number);
    const lessonNumber = extractMaxLessonNumber(changedFiles);
    if (!lessonNumber) {
      return {updated: false};
    }

    // 3) Find matching row in assignments sheet
    const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(SHEET_NAMES.ASSIGNMENTS);
    if (!sheet) {
      return {updated: false};
    }

    const values = sheet.getDataRange().getValues();
    let targetRow = -1;

    for (let i = 1; i < values.length; i++) {
      if (values[i][GRADES_COLUMNS.LESSON_NUMBER - 1] === lessonNumber && 
          values[i][GRADES_COLUMNS.STUDENT_NAME - 1] === studentName) {
        targetRow = i + 1; // Account for 1-based index
        break;
      }
    }

    // 4) Skip if no matching row
    if (targetRow === -1) {
      return {updated: false};
    }

    // 5) Check if PR is already recorded and graded
    const currentPrUrl = sheet.getRange(targetRow, GRADES_COLUMNS.PR_URL).getValue();
    const gradingStatus = sheet.getRange(targetRow, GRADES_COLUMNS.GRADING_STATUS).getValue();
    
    const prHyperlink = `=HYPERLINK("${prUrl}", "#${pr.number}")`;
    
    // If PR link exists and submission column (K) is not empty, skip processing
    if (currentPrUrl === prHyperlink && gradingStatus && gradingStatus.toString().trim() !== "") {
      return {updated: false, message: "Already graded"};
    }
    
    // 6) Update PR URL if different
    if (currentPrUrl !== prHyperlink) {
      sheet.getRange(targetRow, GRADES_COLUMNS.PR_URL).setValue(prHyperlink);
    }

    // 7) Perform grading if not already done and if GRADING-COPILOT.md exists
    if (!gradingStatus || gradingStatus.toString().trim() === "") {
      const gradingResult = gradePullRequest(pr, lessonNumber, changedFiles, studentName);
      if (gradingResult.success) {
        // Update scores and status
        sheet.getRange(targetRow, GRADES_COLUMNS.FUNCTIONAL_SCORE).setValue(gradingResult.functionalScore);
        sheet.getRange(targetRow, GRADES_COLUMNS.TECHNICAL_SCORE).setValue(gradingResult.technicalScore);
        sheet.getRange(targetRow, GRADES_COLUMNS.GRADING_STATUS).setValue("Y");
        
        return {updated: true, message: `Graded: F${gradingResult.functionalScore}/T${gradingResult.technicalScore}`};
      } else if (gradingResult.error && !gradingResult.error.includes("No grading instructions found")) {
        // Only mark as ERROR if it's not just missing GRADING-COPILOT.md
        sheet.getRange(targetRow, GRADES_COLUMNS.GRADING_STATUS).setValue("ERROR");
        return {updated: false, message: `Grading failed: ${gradingResult.error}`};
      }
      // If no GRADING-COPILOT.md found, just skip grading (don't mark as error)
    }

    return {updated: false};

  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Logger.log("Error processing PR: " + errorMessage);
    return {updated: false, message: `Error processing PR: ${errorMessage}`};
  }
}

/**
 * Get recent pull requests from GitHub repository
 */
function getRecentPullRequests(): any[] {
  const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
  if (!token) {
    throw new Error("GitHub token not found. Please set up your GitHub token first.");
  }

  try {
    // Get only open PRs for grading
    const openPRs = fetchPullRequests('open');
    
    // Sort by updated date (most recent first)
    return openPRs.sort((a, b) => 
      new Date(b.updated_at).getTime() - new Date(a.updated_at).getTime()
    );

  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Logger.log("Error fetching pull requests: " + errorMessage);
    throw new Error(`Failed to fetch pull requests: ${errorMessage}`);
  }
}

/**
 * Fetch pull requests with specified state
 */
function fetchPullRequests(state: 'open' | 'closed'): any[] {
  const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
  const url = `${GITHUB_CONFIG.API_BASE}/repos/${GITHUB_CONFIG.OWNER}/${GITHUB_CONFIG.REPO}/pulls?state=${state}&per_page=100`;

  const response = UrlFetchApp.fetch(url, {
    headers: {
      "Authorization": "token " + token,
      "Accept": "application/vnd.github.v3+json"
    }
  });

  if (response.getResponseCode() !== 200) {
    throw new Error(`GitHub API error: ${response.getResponseCode()} - ${response.getContentText()}`);
  }

  return JSON.parse(response.getContentText());
}

/**
 * Get files changed in a specific pull request
 */
function getPullRequestFiles(prNumber: number): string[] {
  const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
  if (!token) {
    Logger.log("GITHUB_TOKEN not found in script properties");
    return [];
  }

  let files: string[] = [];
  let page = 1;

  while (true) {
    try {
      const url = `${GITHUB_CONFIG.API_BASE}/repos/${GITHUB_CONFIG.OWNER}/${GITHUB_CONFIG.REPO}/pulls/${prNumber}/files?page=${page}&per_page=100`;
      
      const response = UrlFetchApp.fetch(url, {
        headers: {
          "Authorization": "token " + token,
          "Accept": "application/vnd.github.v3+json"
        }
      });

      const json = JSON.parse(response.getContentText());
      if (json.length === 0) break;

      files = files.concat(json.map((f: any) => f.filename));
      page++;
    } catch (error) {
      const errorMessage = error instanceof Error ? error.message : String(error);
      Logger.log("Error fetching PR files: " + errorMessage);
      break;
    }
  }

  return files;
}

/**
 * Lookup student full name using GitHub username (case-insensitive)
 */
function lookupStudentName(githubUser: string): string | null {
  const rollSheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(SHEET_NAMES.ROSTER);
  if (!rollSheet) {
    Logger.log(`${SHEET_NAMES.ROSTER} sheet not found`);
    return null;
  }

  const rollValues = rollSheet.getDataRange().getValues();

  for (let i = 1; i < rollValues.length; i++) {
    if (rollValues[i][ROSTER_COLUMNS.GITHUB_USERNAME - 1].toString().toLowerCase() === githubUser.toLowerCase()) { // GitHub username column
      return rollValues[i][ROSTER_COLUMNS.FULL_NAME - 1].toString(); // Full name column
    }
  }
  return null;
}

/**
 * Fetch list of changed files in the PR (legacy function - kept for compatibility)
 */
function getChangedFiles(prApiUrl: string): string[] {
  // Extract PR number from API URL
  const match = prApiUrl.match(/\/pulls\/(\d+)$/);
  if (!match) {
    Logger.log("Could not extract PR number from URL: " + prApiUrl);
    return [];
  }
  
  const prNumber = parseInt(match[1], 10);
  return getPullRequestFiles(prNumber);
}

/**
 * Extract max lesson number from file paths like ".../lesson_03/..."
 * Returns "L00", "L01", etc.
 */
function extractMaxLessonNumber(files: string[]): string | null {
  let maxLesson = -1;
  const regex = /lesson_(\d{2})/;

  files.forEach(path => {
    const match = path.match(regex);
    if (match) {
      const num = parseInt(match[1], 10);
      if (num > maxLesson) {
        maxLesson = num;
      }
    }
  });

  return maxLesson >= 0 ? "L" + String(maxLesson).padStart(2, "0") : null;
}

/**
 * Grade a pull request based on lesson grading criteria
 */
function gradePullRequest(pr: any, lessonNumber: string, changedFiles: string[], studentName: string): GradingResult {
  try {
    // 1) Fetch grading instructions from the lesson folder
    const gradingInstructions = fetchGradingInstructions(lessonNumber);
    if (!gradingInstructions) {
      return {
        success: false,
        error: `No grading instructions found for ${lessonNumber}`
      };
    }

    // 2) Get PR diff/content for analysis
    const prContent = getPullRequestContent(pr.number);
    
    // 3) Analyze against grading criteria (simplified version)
    const analysis = analyzeSubmission(prContent, gradingInstructions, changedFiles);
    
    // If analysis fails (no ChatGPT available), skip grading
    if (!analysis) {
      return {
        success: false,
        error: `ChatGPT analysis unavailable for ${lessonNumber}`
      };
    }
    
    // 4) Create draft review comment
    const reviewCreated = createDraftReview(pr.number, analysis, studentName);
    
    return {
      success: true,
      functionalScore: analysis.functionalScore,
      technicalScore: analysis.technicalScore,
      reviewCreated: reviewCreated
    };
    
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Logger.log("Error grading PR: " + errorMessage);
    return {
      success: false,
      error: errorMessage
    };
  }
}

/**
 * Fetch grading instructions (GRADING-COPILOT.md) from the lesson folder
 */
function fetchGradingInstructions(lessonNumber: string): string | null {
  const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
  if (!token) {
    Logger.log("GITHUB_TOKEN not found");
    return null;
  }

  try {
    // Convert L00 format to lesson_00 format
    const lessonFolder = lessonNumber.replace('L', 'lesson_');
    const url = `${GITHUB_CONFIG.API_BASE}/repos/${GITHUB_CONFIG.OWNER}/${GITHUB_CONFIG.CURRICULUM_REPO}/contents/${lessonFolder}/GRADING-COPILOT.md`;
    
    const response = UrlFetchApp.fetch(url, {
      headers: {
        "Authorization": "token " + token,
        "Accept": "application/vnd.github.v3+json"
      }
    });

    if (response.getResponseCode() === 200) {
      const json = JSON.parse(response.getContentText());
      // Decode base64 content
      return Utilities.newBlob(Utilities.base64Decode(json.content)).getDataAsString();
    } else if (response.getResponseCode() === 404) {
      Logger.log(`GRADING-COPILOT.md not found for ${lessonFolder}`);
      return null;
    } else {
      Logger.log(`Error fetching GRADING-COPILOT.md: ${response.getResponseCode()}`);
      return null;
    }
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Logger.log("Error fetching grading instructions: " + errorMessage);
    return null;
  }
}

/**
 * Get PR content including diff and file contents
 */
function getPullRequestContent(prNumber: number): PRContent {
  const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
  if (!token) {
    return { files: [], diff: "" };
  }

  try {
    // Get PR files with patch content
    const url = `${GITHUB_CONFIG.API_BASE}/repos/${GITHUB_CONFIG.OWNER}/${GITHUB_CONFIG.REPO}/pulls/${prNumber}/files`;
    
    const response = UrlFetchApp.fetch(url, {
      headers: {
        "Authorization": "token " + token,
        "Accept": "application/vnd.github.v3.diff"
      }
    });

    if (response.getResponseCode() === 200) {
      const files = JSON.parse(response.getContentText());
      return {
        files: files,
        diff: response.getContentText()
      };
    }
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Logger.log("Error fetching PR content: " + errorMessage);
  }

  return { files: [], diff: "" };
}

/**
 * Analyze submission against grading criteria using ChatGPT 4o
 */
function analyzeSubmission(prContent: PRContent, gradingInstructions: string, changedFiles: string[]): GradingAnalysis | null {
  // Only use ChatGPT analysis - no fallback
  try {
    return analyzeWithChatGPT(prContent, gradingInstructions, changedFiles);
  } catch (error) {
    Logger.log("ChatGPT analysis failed: " + error);
    return null;
  }
}

/**
 * Analyze submission using ChatGPT 4o
 */
function analyzeWithChatGPT(prContent: PRContent, gradingInstructions: string, changedFiles: string[]): GradingAnalysis | null {
  const apiKey = PropertiesService.getScriptProperties().getProperty("OPENAI_API_KEY");
  if (!apiKey) {
    Logger.log("OPENAI_API_KEY not found in script properties");
    return null;
  }

  try {
    // Prepare the prompt with grading context
    const filesContext = changedFiles.length > 0 ? 
      `Changed files: ${changedFiles.join(', ')}` : 
      'No file information available';
    
    const diffContent = prContent.diff ? 
      prContent.diff.substring(0, 8000) : // Limit diff size to avoid token limits
      'No diff content available';

    const prompt = `You are an expert code grader. Please analyze this student's pull request submission against the provided grading criteria.

GRADING CRITERIA:
${gradingInstructions}

SUBMISSION DETAILS:
${filesContext}

CODE CHANGES:
${diffContent}

Please provide your analysis in the following JSON format:
{
  "functionalScore": <number 1-5>,
  "technicalScore": <number 1-5>,
  "feedback": "<detailed feedback explaining the scores>"
}

Guidelines:
- Be fair but thorough in your assessment
- Provide constructive feedback
- Consider both what was implemented and how well it was implemented
- Reference specific aspects from the grading criteria, but DO NOT include score in the feedback
- Keep feedback concise but helpful`;

    const response = UrlFetchApp.fetch("https://api.openai.com/v1/chat/completions", {
      method: "post",
      headers: {
        "Authorization": "Bearer " + apiKey,
        "Content-Type": "application/json"
      },
      payload: JSON.stringify({
        model: "gpt-4o",
        messages: [
          {
            role: "system",
            content: "You are an expert programming instructor and code reviewer. Analyze student submissions fairly and provide constructive feedback."
          },
          {
            role: "user",
            content: prompt
          }
        ],
        max_tokens: 1000,
        temperature: 0.3,
        response_format: { type: "json_object" }
      })
    });

    if (response.getResponseCode() === 200) {
      const result = JSON.parse(response.getContentText());
      const content = result.choices[0].message.content;
      
      // Parse the JSON response from ChatGPT
      const analysis = JSON.parse(content);
      
      return {
        functionalScore: Math.min(Math.max(analysis.functionalScore || 5, 0), 10),
        technicalScore: Math.min(Math.max(analysis.technicalScore || 5, 0), 10),
        feedback: analysis.feedback || "Analysis completed",
        gradingInstructions
      };
    } else {
      Logger.log(`OpenAI API error: ${response.getResponseCode()} - ${response.getContentText()}`);
      return null;
    }
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Logger.log("Error in ChatGPT analysis: " + errorMessage);
    return null;
  }
}

/**
 * Create a draft review comment on the PR
 */
function createDraftReview(prNumber: number, analysis: GradingAnalysis, studentName: string): boolean {
  const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
  if (!token) {
    Logger.log("GITHUB_TOKEN not found");
    return false;
  }

  try {
    const reviewBody = generateReviewComment(analysis, studentName);
    
    const url = `${GITHUB_CONFIG.API_BASE}/repos/${GITHUB_CONFIG.OWNER}/${GITHUB_CONFIG.REPO}/pulls/${prNumber}/reviews`;
    
    const payload = {
      body: reviewBody,
      event: "COMMENT",
      comments: []
    };

    const response = UrlFetchApp.fetch(url, {
      method: "post",
      headers: {
        "Authorization": "token " + token,
        "Accept": "application/vnd.github.v3+json",
        "Content-Type": "application/json"
      },
      payload: JSON.stringify(payload)
    });

    if (response.getResponseCode() === 200) {
      Logger.log(`Draft review created for PR #${prNumber} - can be edited before publishing`);
      return true;
    } else {
      Logger.log(`Failed to create review: ${response.getResponseCode()} - ${response.getContentText()}`);
      return false;
    }
  } catch (error) {
    const errorMessage = error instanceof Error ? error.message : String(error);
    Logger.log("Error creating draft review: " + errorMessage);
    return false;
  }
}

/**
 * Generate review comment text
 */
function generateReviewComment(analysis: GradingAnalysis, studentName: string): string {
  return `## 🎓 Automated Grading Report

**Student:** ${studentName}
**Date:** ${new Date().toLocaleDateString()}

### Feedback
${analysis.feedback}

---
*This is an automated preliminary review. Please review and adjust before finalizing.*`;
}

/**
 * Type definitions for grading functionality
 */
interface GradingResult {
  success: boolean;
  functionalScore?: number;
  technicalScore?: number;
  reviewCreated?: boolean;
  error?: string;
}

interface PRContent {
  files: any[];
  diff: string;
}

interface GradingAnalysis {
  functionalScore: number;
  technicalScore: number;
  feedback: string;
  gradingInstructions: string;
}

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
    .addToUi();
}

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
    const studentName = lookupStudentName(githubUser);
    
    if (studentName) {
      ui.alert('Success', `Found student: ${studentName}`, ui.ButtonSet.OK);
    } else {
      ui.alert('Not Found', `No student found for GitHub user: ${githubUser}`, ui.ButtonSet.OK);
    }
  }
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
 * Initialize the add-on (run this once after setup)
 */
function initializeAddon(): void {
  const ui = SpreadsheetApp.getUi();
  
  // Check if required sheets exist
  const ss = SpreadsheetApp.getActiveSpreadsheet();
  const assignmentsSheet = ss.getSheetByName(SHEET_NAMES.ASSIGNMENTS);
  const rosterSheet = ss.getSheetByName(SHEET_NAMES.ROSTER);
  
  if (!assignmentsSheet || !rosterSheet) {
    ui.alert(
      'Missing Sheets',
      `Please ensure you have the following sheets:\n• ${SHEET_NAMES.ASSIGNMENTS}\n• ${SHEET_NAMES.ROSTER}`,
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
      // Get PR details
      const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
      if (!token) {
        ui.alert('Error', 'GitHub token not found. Please set up your GitHub token first.', ui.ButtonSet.OK);
        return;
      }

      const url = `${GITHUB_CONFIG.API_BASE}/repos/${GITHUB_CONFIG.OWNER}/${GITHUB_CONFIG.REPO}/pulls/${prNumber}`;
      const prResponse = UrlFetchApp.fetch(url, {
        headers: {
          "Authorization": "token " + token,
          "Accept": "application/vnd.github.v3+json"
        }
      });

      if (prResponse.getResponseCode() !== 200) {
        ui.alert('Error', `PR #${prNumber} not found.`, ui.ButtonSet.OK);
        return;
      }

      const pr = JSON.parse(prResponse.getContentText());
      const changedFiles = getPullRequestFiles(prNumber);
      const lessonNumber = extractMaxLessonNumber(changedFiles);
      const studentName = lookupStudentName(pr.user.login);

      if (!lessonNumber) {
        ui.alert('Test Result', `PR #${prNumber}: No lesson number detected from changed files.`, ui.ButtonSet.OK);
        return;
      }

      if (!studentName) {
        ui.alert('Test Result', `PR #${prNumber}: Student not found for GitHub user ${pr.user.login}.`, ui.ButtonSet.OK);
        return;
      }

      // Test grading
      const gradingResult = gradePullRequest(pr, lessonNumber, changedFiles, studentName);
      
      if (gradingResult.success) {
        ui.alert(
          'Test Successful',
          `PR #${prNumber} grading test completed:\n\n` +
          `Student: ${studentName}\n` +
          `Lesson: ${lessonNumber}\n` +
          `Functional Score: ${gradingResult.functionalScore}/10\n` +
          `Technical Score: ${gradingResult.technicalScore}/10\n` +
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
