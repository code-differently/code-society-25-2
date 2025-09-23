/**
 * Type definitions for the GitHub Grader system
 */

export interface GradingResult {
  success: boolean;
  functionalScore?: number;
  technicalScore?: number;
  stretchScore?: number;
  reviewCreated?: boolean;
  error?: string;
}

export interface PRContent {
  files: any[];
  diff: string;
}

export interface GradingAnalysis {
  functionalScore: number;
  technicalScore: number;
  stretchScore: number;
  feedback: string;
  gradingInstructions: string;
}

export interface ProcessResult {
  updated: boolean;
  message?: string;
}

export interface CacheEntry<T> {
  data: T;
  timestamp: number;
}

export interface StudentLookupMap {
  [githubUsername: string]: string;
}

export interface SyncSummary {
  processedCount: number;
  updatedCount: number;
  results: string[];
  errors: string[];
}

export interface PullRequest {
  number: number;
  html_url: string;
  updated_at: string;
  user: {
    login: string;
  };
  [key: string]: any;
}

export interface GitHubFile {
  filename: string;
  status: string;
  patch?: string;
  [key: string]: any;
}

export interface SheetRow {
  [columnIndex: number]: any;
}

export interface GradingInstructions {
  lessonNumber: string;
  content: string;
}

export interface OpenAIResponse {
  choices: Array<{
    message: {
      content: string;
    };
  }>;
  [key: string]: any;
}

export interface MenuOptions {
  ui: GoogleAppsScript.Base.Ui;
  ButtonSet: GoogleAppsScript.Base.ButtonSet;
}

export type GradingStatus = 'Received' | 'Graded' | 'Incomplete' | 'ERROR';