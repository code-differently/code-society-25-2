# GitHub Grader Google Sheets Add-on

A TypeScript-based Google Sheets add-on that automatically syncs GitHub pull requests with your grading spreadsheet. Instead of using webhooks, this add-on pulls PR data directly from GitHub and updates your sheets.

## Features

✅ **Pull-based Sync** - Fetches latest PRs from GitHub repository  
✅ **Automatic Updates** - Optional hourly syncing with time-driven triggers  
✅ **Student Matching** - Maps GitHub usernames to student names  
✅ **Lesson Detection** - Extracts lesson numbers from file paths  
✅ **Smart Updates** - Only updates when PR URLs change  
✅ **Comprehensive Logging** - Detailed execution logs and error handling

## Setup

1. **Install dependencies:**
   ```bash
   npm install
   ```

2. **Login to Google Apps Script:**
   ```bash
   npx clasp login
   ```

3. **Create/Update the Apps Script project:**
   ```bash
   npm run push
   ```

## Google Sheets Setup

### Required Sheet Structure

#### "Roster" Sheet
- **Column A**: Full Name (e.g., "John Smith")  
- **Column D**: GitHub Username (e.g., "johnsmith123")

#### "Assignments" Sheet  
- **Column A**: Lesson Number (e.g., "L01", "L02")
- **Column B**: Student Name (must match names from Roster sheet)
- **Column L**: PR URL (automatically populated by the add-on)

### Configuration

The add-on uses these constants (configurable in `main.ts`):

```typescript
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

const GITHUB_CONFIG = {
  OWNER: "code-differently",
  REPO: "code-society-25-2",
  API_BASE: "https://api.github.com"
} as const;
```

## Usage

### Binding to Your Spreadsheet

1. **Open your grading spreadsheet**
2. **Go to Extensions → Apps Script**
3. **Replace default code or import your script:**
   - Script ID: `1ZhztdBcZAxh5ufBPJIVajC4u_Dgx5J741np7a_PiKotFZG8GeMGP4CPb`
4. **Save and reload your spreadsheet**

### Initial Setup

1. **First time setup:**
   - Run the `initializeAddon()` function from the Apps Script editor
   - Or use the "GitHub Grader" menu once it appears

2. **GitHub Token Setup:**
   - Create a GitHub Personal Access Token with `repo` or `public_repo` scope
   - Use "Setup GitHub Token" from the menu to store it securely

### Menu Options

The "GitHub Grader" menu provides:

- **🔄 Sync GitHub PRs Now** - Manual sync of all recent PRs
- **⚙️ Setup GitHub Token** - Store your GitHub authentication token  
- **🕐 Setup Auto-Sync (Hourly)** - Enable automatic hourly syncing
- **🛑 Disable Auto-Sync** - Turn off automatic syncing
- **🧪 Test Student Lookup** - Test GitHub username to student name mapping
- **📋 View Sync Logs** - Instructions for viewing execution logs

### How It Works

1. **Fetches Recent PRs** - Gets all open and recently closed PRs from the last 30 days
2. **Student Lookup** - Maps GitHub username to full name using Roster sheet
3. **Lesson Detection** - Extracts lesson number from changed file paths (e.g., `lesson_03/`)
4. **Sheet Updates** - Finds matching row and updates PR URL column
5. **Results Summary** - Shows count of processed and updated entries

## Development

### Building and Deploying

```bash
# Build TypeScript
npm run build

# Push to Google Apps Script  
npm run push

# Open in web editor
npm run open
```

### File Structure

```
├── src/
│   ├── main.ts          # Main add-on logic
│   └── appsscript.json  # Apps Script manifest
├── dist/                # Compiled JavaScript (auto-generated)
├── .clasp.json         # Apps Script project configuration
├── tsconfig.json       # TypeScript configuration  
└── package.json        # Dependencies and build scripts
```

## Advantages Over Webhooks

✅ **No External Dependencies** - Works entirely within Google's ecosystem  
✅ **Better Reliability** - No webhook delivery failures  
✅ **Easier Setup** - No need to configure repository webhooks  
✅ **Historical Data** - Can process older PRs retroactively  
✅ **Rate Limiting Control** - Manages GitHub API usage effectively  
✅ **Private Repository Support** - Works with private repos using tokens

## GitHub Token Requirements

For the GitHub API access, you need a Personal Access Token with:

- **Public repositories**: `public_repo` scope
- **Private repositories**: `repo` scope  

Create tokens at: https://github.com/settings/tokens

## Troubleshooting

### Common Issues

1. **"No student found"** - Check GitHub usernames in Roster sheet (Column D)
2. **"No lesson number found"** - Ensure PR contains files in `lesson_XX/` format  
3. **"Sheet not found"** - Verify sheet names match the constants
4. **API errors** - Check GitHub token has correct permissions

### Viewing Logs

1. Go to Extensions → Apps Script
2. Click "Executions" in left sidebar  
3. Click any execution to see detailed logs

## Notes

- Processes PRs from the last 30 days on each sync
- Only updates spreadsheet when PR URL actually changes
- Uses GitHub API v3 with proper authentication
- Handles pagination for large numbers of PRs
- Includes comprehensive error handling and logging
