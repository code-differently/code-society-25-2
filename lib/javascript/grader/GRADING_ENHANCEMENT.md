# GitHub Grader - Auto-Grading Enhancement

This enhancement adds automatic grading functionality to the GitHub Grader system. Here's how it works:

## Overview

The enhanced grader now performs these steps:

1. **Early Exit**: If a PR link has already been recorded and grading status is "GRADED", processing stops
2. **Grading Instructions**: Fetches `GRADING.md` from the corresponding lesson folder
3. **Automatic Grading**: If GRADING.md is found, analyzes PR changes against grading criteria
4. **Draft Review**: Creates a draft review comment on the original PR
5. **Score Recording**: Records functional and technical scores in the spreadsheet

## Key Feature: GRADING.md-Based Activation

**Grading only occurs when a `GRADING.md` file exists in the lesson folder.** This means:
- ✅ Lessons with `lesson_XX/GRADING.md` will be automatically graded
- ⏭️ Lessons without `GRADING.md` will only have PR links recorded (no grading)
- 🎯 No configuration needed - simply add GRADING.md files when ready to grade

## New Spreadsheet Columns

The system now uses these additional columns:
- **Column M (13)**: Functional Score (0-10)
- **Column N (14)**: Technical Score (0-10) 
- **Column O (15)**: Grading Status ("GRADED", "ERROR", or empty)

## Required Setup

### 1. Create GRADING.md Files
Each lesson folder should contain a `GRADING.md` file with grading criteria:

```
lesson_00/
  GRADING.md
lesson_01/
  GRADING.md
lesson_02/
  GRADING.md
```

### 2. GRADING.md Format
See the example in `lesson_00/GRADING.md`. Include:
- Functional requirements with point values
- Technical requirements with point values
- Common issues to check
- Notes for graders

### 3. GitHub Token Permissions
Your GitHub token needs these permissions:
- `repo` scope (for private repos) or `public_repo` (for public repos)
- `pull_requests:write` (to create review comments)

## Menu Options

The "GitHub Grader" menu includes:
- **🔄 Sync GitHub PRs Now**: Manual sync of all recent PRs
- **⚙️ Setup GitHub Token**: Configure GitHub access
- **🕐 Setup Auto-Sync (Hourly)**: Enable automatic syncing
- **� Disable Auto-Sync**: Disable automatic syncing
- **🧪 Test Student Lookup**: Test student name resolution
- **🧪 Test Grading System**: Test grading on a specific PR
- **📋 View Sync Logs**: Instructions for viewing execution logs

## How Grading Works

### 1. Early Exit Logic
```typescript
if (currentPrUrl === prHyperlink && gradingStatus === "GRADED") {
  return {updated: false, message: "Already graded"};
}
```

### 2. Automatic Grading Detection
The system automatically attempts grading for any ungraded PR:
- Tries to fetch `lesson_XX/GRADING.md` from the repository
- If found: proceeds with grading analysis
- If not found: skips grading (no error, just logs the absence)

### 3. Basic Grading Heuristics
Current implementation includes:
- Tests presence check (+2 functional points)
- Documentation check (+1 technical point)
- File count analysis (penalty for too many files)

## Enhancing the Grading Logic

### Option 1: Rule-Based Enhancement
Enhance `analyzeSubmission()` function with more sophisticated rules:

```typescript
function analyzeSubmission(prContent: PRContent, gradingInstructions: string, changedFiles: string[]): GradingAnalysis {
  // Add checks for:
  // - Code quality metrics
  // - Specific file requirements
  // - Naming conventions
  // - Git commit quality
  // - Code complexity
}
```

### Option 2: AI Integration
Integrate with OpenAI or other AI services:

```typescript
function analyzeWithAI(prContent: string, gradingInstructions: string): Promise<GradingAnalysis> {
  const apiKey = PropertiesService.getScriptProperties().getProperty("OPENAI_API_KEY");
  
  const response = UrlFetchApp.fetch("https://api.openai.com/v1/chat/completions", {
    method: "post",
    headers: {
      "Authorization": "Bearer " + apiKey,
      "Content-Type": "application/json"
    },
    payload: JSON.stringify({
      model: "gpt-4",
      messages: [
        {
          role: "system",
          content: "You are a code grader. Analyze the PR content against the grading criteria and provide scores."
        },
        {
          role: "user", 
          content: `Grading Criteria:\n${gradingInstructions}\n\nPR Content:\n${prContent}`
        }
      ]
    })
  });
  
  // Parse response and extract scores
}
```

## Draft Review Format

The system creates draft reviews with this format:

```markdown
## 🎓 Automated Grading Report

**Student:** John Doe
**Date:** 7/31/2025

### Scores
- **Functional Score:** 7/10
- **Technical Score:** 8/10

### Feedback
✅ Includes test files
✅ Includes documentation
⚠️ Large number of changed files - consider smaller commits

### Grading Criteria Used
[First 500 characters of GRADING.md content]...

---
*This is an automated preliminary review. Please review and adjust before finalizing.*
```

## Best Practices

### For Instructors
1. **Review Draft Comments**: Always review and edit before publishing
2. **Consistent GRADING.md**: Keep grading criteria consistent across lessons
3. **Regular Testing**: Use the test function to verify grading logic
4. **Gradual Rollout**: Add GRADING.md files to lessons when ready to grade them
5. **Selective Grading**: Only lessons with GRADING.md files will be graded

### For GRADING.md Files
1. **Clear Criteria**: Specify exactly what earns each point
2. **Point Distribution**: Aim for 70% functional, 30% technical
3. **Common Issues**: List frequently seen problems
4. **Examples**: Include examples of good/bad implementations

### For Maintenance
1. **Monitor Logs**: Check execution logs regularly
2. **Error Handling**: The system gracefully handles missing files
3. **Backup Scores**: Spreadsheet serves as permanent record
4. **Rate Limits**: GitHub API has rate limits (5000 requests/hour)

## Troubleshooting

### Common Issues
1. **Missing GRADING.md**: System logs info message, skips grading (no error)
2. **GitHub API Errors**: Check token permissions and rate limits  
3. **Draft Review Fails**: Verify token has pull request write access
4. **No Lesson Detected**: Ensure PR changes files in `lesson_XX/` folders

### Error Messages
- `"No grading instructions found for L00"`: No `lesson_00/GRADING.md` file (this is normal if grading not needed)
- `"GitHub API error: 403"`: Check token permissions
- `"Failed to create review"`: Verify pull request write access

## Future Enhancements

1. **AI Integration**: Use GPT-4 or Claude for sophisticated analysis
2. **Custom Rubrics**: Allow per-lesson custom grading logic
3. **Plagiarism Detection**: Compare submissions across students
4. **Code Quality Metrics**: Integrate with ESLint, SonarQube, etc.
5. **Automated Testing**: Run unit tests and grade based on results
6. **Peer Review**: Allow students to review each other's work

## Security Considerations

1. **Token Storage**: GitHub tokens stored securely in Script Properties
2. **Draft Reviews**: Reviews are drafts by default, require manual publishing
3. **Data Privacy**: No student data leaves Google Sheets environment
4. **Access Control**: Only spreadsheet collaborators can access grading functions

This enhancement provides a solid foundation for automated grading while maintaining instructor control over the final grades and feedback.
