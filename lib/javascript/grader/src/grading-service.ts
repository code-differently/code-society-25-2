/**
 * Grading Service
 * Handles OpenAI integration and automated grading logic
 */

import { OPENAI_CONFIG } from './config';
import { GitHubApiService } from './github-api';
import { GradingAnalysis, GradingResult, PRContent, PullRequest } from './types';

export class GradingService {
  
  /**
   * Grade a pull request based on lesson grading criteria
   */
  static gradePullRequest(
    pr: PullRequest, 
    lessonNumber: string, 
    changedFiles: string[], 
    studentName: string
  ): GradingResult {
    try {
      // 1) Fetch grading instructions from the lesson folder
      const gradingInstructions = GitHubApiService.fetchGradingInstructions(lessonNumber);
      if (!gradingInstructions) {
        return {
          success: false,
          error: `No grading instructions found for ${lessonNumber}`
        };
      }

      // 2) Get PR diff/content for analysis
      const prContent = GitHubApiService.getPullRequestContent(pr.number);
      
      // 3) Analyze against grading criteria
      const analysis = this.analyzeSubmission(prContent, gradingInstructions, changedFiles);
      
      // If analysis fails (no ChatGPT available), skip grading
      if (!analysis) {
        return {
          success: false,
          error: `ChatGPT analysis unavailable for ${lessonNumber}`
        };
      }
      
      // 4) Create draft review comment
      const reviewBody = this.generateReviewComment(analysis, studentName);
      const reviewCreated = GitHubApiService.createDraftReview(pr.number, reviewBody);
      
      return {
        success: true,
        functionalScore: analysis.functionalScore,
        technicalScore: analysis.technicalScore,
        stretchScore: analysis.stretchScore,
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
   * Analyze submission against grading criteria using ChatGPT
   */
  private static analyzeSubmission(
    prContent: PRContent, 
    gradingInstructions: string, 
    changedFiles: string[]
  ): GradingAnalysis | null {
    try {
      return this.analyzeWithChatGPT(prContent, gradingInstructions, changedFiles);
    } catch (error) {
      Logger.log("ChatGPT analysis failed: " + error);
      return null;
    }
  }

  /**
   * Analyze submission using ChatGPT 4o
   */
  private static analyzeWithChatGPT(
    prContent: PRContent, 
    gradingInstructions: string, 
    changedFiles: string[]
  ): GradingAnalysis | null {
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
        this.prepareDiffForAnalysis(prContent.diff) : 
        'No diff content available';

      const prompt = this.buildGradingPrompt(gradingInstructions, filesContext, diffContent);

      const response = UrlFetchApp.fetch(OPENAI_CONFIG.API_URL, {
        method: "post",
        headers: {
          "Authorization": "Bearer " + apiKey,
          "Content-Type": "application/json"
        },
        payload: JSON.stringify({
          model: OPENAI_CONFIG.MODEL,
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
          max_tokens: OPENAI_CONFIG.MAX_TOKENS,
          temperature: OPENAI_CONFIG.TEMPERATURE,
          response_format: { type: "json_object" }
        })
      });

      if (response.getResponseCode() === 200) {
        const result = JSON.parse(response.getContentText());
        const content = result.choices[0].message.content;
        
        // Parse the JSON response from ChatGPT
        const analysis = JSON.parse(content);
        
        return {
          functionalScore: this.clampScore(analysis.functionalScore, 5),
          technicalScore: this.clampScore(analysis.technicalScore, 5),
          stretchScore: this.clampScore(analysis.stretchScore, 0),
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
   * Build the grading prompt for ChatGPT
   */
  private static buildGradingPrompt(
    gradingInstructions: string, 
    filesContext: string, 
    diffContent: string
  ): string {
    return `You are an expert code grader. Please analyze this student's pull request submission against the provided grading criteria.

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
  "stretchScore": <number 1-5, or 0 if no stretch criteria is provided>,
  "feedback": "<detailed feedback explaining the scores>"
}

Guidelines:
- Be fair but thorough in your assessment
- Provide constructive feedback
- Consider both what was implemented and how well it was implemented
- Reference specific aspects from the grading criteria, but DO NOT include scores in the feedback
- Keep feedback concise but helpful
- Do not address the student directly in the feedback, just provide the analysis`;
  }

  /**
   * Intelligently prepare diff content for analysis (limit size and prioritize important changes)
   */
  private static prepareDiffForAnalysis(diff: string): string {
    const lines = diff.split('\n');
    const maxLines = 200; // Reasonable limit to avoid token limits
    
    // Prioritize added/modified lines over context
    const importantLines = lines.filter(line => {
      const trimmed = line.trim();
      return trimmed.startsWith('+') || 
             trimmed.startsWith('-') || 
             trimmed.includes('function') || 
             trimmed.includes('class') ||
             trimmed.includes('def ') ||
             trimmed.includes('public ') ||
             trimmed.includes('private ') ||
             trimmed.includes('import ') ||
             trimmed.includes('from ');
    });
    
    // If we have fewer important lines than our limit, include some context
    let selectedLines = importantLines;
    if (importantLines.length < maxLines) {
      const remainingSlots = maxLines - importantLines.length;
      const contextLines = lines.filter(line => !importantLines.includes(line));
      selectedLines = [...importantLines, ...contextLines.slice(0, remainingSlots)];
    } else {
      selectedLines = importantLines.slice(0, maxLines);
    }
    
    return selectedLines.join('\n');
  }

  /**
   * Clamp score to valid range
   */
  private static clampScore(score: number | undefined, defaultValue: number): number {
    if (score === undefined || score === null || isNaN(score)) {
      return defaultValue;
    }
    return Math.min(Math.max(score, 0), 10);
  }

  /**
   * Generate review comment text
   */
  private static generateReviewComment(analysis: GradingAnalysis, studentName: string): string {
    return `## 🎓 Automated Grading Report

**Student:** ${studentName}
**Date:** ${new Date().toLocaleDateString()}

### Feedback
${analysis.feedback}

---
*This is an automated preliminary review. Please review and adjust before finalizing.*`;
  }

  /**
   * Perform a simple analysis without AI (fallback method)
   */
  static performSimpleAnalysis(
    changedFiles: string[], 
    lessonNumber: string
  ): GradingAnalysis {
    // Basic scoring based on file presence and lesson requirements
    const hasJavaFiles = changedFiles.some(file => file.endsWith('.java'));
    const hasTestFiles = changedFiles.some(file => file.includes('test') || file.includes('Test'));
    const hasReadme = changedFiles.some(file => file.toLowerCase().includes('readme'));
    
    let functionalScore = 3; // Base score
    let technicalScore = 3;  // Base score
    
    if (hasJavaFiles) functionalScore += 1;
    if (hasTestFiles) technicalScore += 1;
    if (hasReadme) functionalScore += 1;
    
    const feedback = `Basic analysis completed. Files changed: ${changedFiles.length}. ` +
      `Java files: ${hasJavaFiles ? 'Yes' : 'No'}. ` +
      `Test files: ${hasTestFiles ? 'Yes' : 'No'}. ` +
      `Documentation: ${hasReadme ? 'Yes' : 'No'}.`;

    return {
      functionalScore,
      technicalScore,
      stretchScore: 0,
      feedback,
      gradingInstructions: `Simple analysis for ${lessonNumber}`
    };
  }

  /**
   * Check if OpenAI integration is available
   */
  static isAIGradingAvailable(): boolean {
    const apiKey = PropertiesService.getScriptProperties().getProperty("OPENAI_API_KEY");
    return !!apiKey;
  }
}