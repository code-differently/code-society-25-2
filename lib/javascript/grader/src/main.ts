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
  PR_URL: 12         // Column L
} as const;

const ROSTER_COLUMNS = {
  FULL_NAME: 1,       // Column A
  GITHUB_USERNAME: 4  // Column D
} as const;

// GitHub repository configuration
const GITHUB_CONFIG = {
  OWNER: "code-differently",
  REPO: "code-society-25-2",
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

    // 5) Update PR URL if different
    const prHyperlink = `=HYPERLINK("${prUrl}", "#${pr.number}")`;
    const currentPrUrl = sheet.getRange(targetRow, GRADES_COLUMNS.PR_URL).getValue();
    if (currentPrUrl !== prHyperlink) {
      sheet.getRange(targetRow, GRADES_COLUMNS.PR_URL).setValue(prHyperlink);
      return {updated: true};
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
    // Get both open and recently closed PRs
    const openPRs = fetchPullRequests('open');
    const closedPRs = fetchPullRequests('closed');
    
    // Combine and sort by updated date (most recent first)
    const allPRs = [...openPRs, ...closedPRs].sort((a, b) => 
      new Date(b.updated_at).getTime() - new Date(a.updated_at).getTime()
    );

    // Return recent PRs (last 30 days)
    const thirtyDaysAgo = new Date();
    thirtyDaysAgo.setDate(thirtyDaysAgo.getDate() - 30);

    return allPRs.filter(pr => new Date(pr.updated_at) > thirtyDaysAgo);

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
    .addItem('🕐 Setup Auto-Sync (Hourly)', 'setupAutoSync')
    .addItem('🛑 Disable Auto-Sync', 'disableAutoSync')
    .addSeparator()
    .addItem('🧪 Test Student Lookup', 'testStudentLookup')
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
  
  ui.alert(
    'Initialization Complete',
    'GitHub Grader add-on is ready to use!\n\nUse the "GitHub Grader" menu to:\n• Sync PRs manually\n• Set up automatic syncing\n• Test functionality',
    ui.ButtonSet.OK
  );
}
