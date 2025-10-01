/**
 * Configuration constants for the GitHub Grader system
 */

export const SHEET_NAMES = {
  ASSIGNMENTS: "Assignments",
  ROSTER: "Roster"
} as const;

export const GRADES_COLUMNS = {
  LESSON_NUMBER: 1,  // Column A
  STUDENT_NAME: 2,   // Column B  
  PR_URL: 12,        // Column L
  FUNCTIONAL_SCORE: 5, // Column E
  TECHNICAL_SCORE: 7,  // Column G
  STRETCH_SCORE: 9, // Column I
  GRADING_STATUS: 11    // Column K
} as const;

export const ROSTER_COLUMNS = {
  FULL_NAME: 1,       // Column A
  GITHUB_USERNAME: 4  // Column D
} as const;

// GitHub repository configuration
export const GITHUB_CONFIG = {
  OWNER: "code-differently",
  REPO: "code-society-25-2",
  CURRICULUM_REPO: "25-2-curriculum",
  API_BASE: "https://api.github.com"
} as const;

export const GRADING_STATUS = {
  RECEIVED: "Received",
  GRADED: "Graded",
  INCOMPLETE: "Incomplete"
} as const;

// Performance and caching configuration
export const CACHE_CONFIG = {
  SHEET_CACHE_DURATION: 5 * 60 * 1000, // 5 minutes
  API_CACHE_DURATION: 10 * 60 * 1000,  // 10 minutes
  MAX_PROCESSING_TIME: 4 * 60 * 1000,  // 4 minutes
  MAX_PRS_PER_RUN: 20,
  MAX_FILES_PER_PR: 50,
  MAX_DIFF_LENGTH: 8000
} as const;

// OpenAI configuration
export const OPENAI_CONFIG = {
  MODEL: "gpt-4o",
  MAX_TOKENS: 1000,
  TEMPERATURE: 0.3,
  API_URL: "https://api.openai.com/v1/chat/completions"
} as const;