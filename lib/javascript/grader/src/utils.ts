/**
 * Utility Functions
 * Common helper functions used across the GitHub Grader system
 */

import { PullRequest } from './types';

export class Utils {
  
  /**
   * Extract max lesson number from file paths like ".../lesson_03/..."
   * Returns "L00", "L01", etc.
   */
  static extractMaxLessonNumber(files: string[]): string | null {
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
   * Create a hyperlink formula for Google Sheets
   */
  static createPrHyperlink(prUrl: string, prNumber: number): string {
    return `=HYPERLINK("${prUrl}", "#${prNumber}")`;
  }

  /**
   * Extract PR number from API URL (legacy compatibility)
   */
  static extractPrNumberFromApiUrl(prApiUrl: string): number | null {
    const match = prApiUrl.match(/\/pulls\/(\d+)$/);
    return match ? parseInt(match[1], 10) : null;
  }

  /**
   * Filter PRs to avoid reprocessing already handled ones
   */
  static filterUnprocessedPRs(pullRequests: PullRequest[], existingPrNumbers: Set<string>): PullRequest[] {
    return pullRequests.filter(pr => !existingPrNumbers.has(pr.number.toString()));
  }

  /**
   * Performance wrapper for timing function execution
   */
  static performanceWrapper<T>(functionName: string, fn: () => T): T {
    const startTime = Date.now();
    
    try {
      const result = fn();
      const duration = Date.now() - startTime;
      Logger.log(`${functionName} completed in ${duration}ms`);
      return result;
    } catch (error) {
      const duration = Date.now() - startTime;
      Logger.log(`${functionName} failed after ${duration}ms: ${error}`);
      throw error;
    }
  }

  /**
   * Check if execution time limit is approaching
   */
  static checkTimeLimit(startTime: number, maxTime: number): boolean {
    return (Date.now() - startTime) > maxTime;
  }

  /**
   * Sanitize strings for logging (remove sensitive information)
   */
  static sanitizeForLogging(text: string): string {
    // Remove potential API keys or tokens
    return text
      .replace(/token\s+[a-zA-Z0-9_-]+/gi, 'token [REDACTED]')
      .replace(/Bearer\s+[a-zA-Z0-9_-]+/gi, 'Bearer [REDACTED]')
      .replace(/api[_-]?key[:\s=]+[a-zA-Z0-9_-]+/gi, 'api_key=[REDACTED]');
  }

  /**
   * Format date for display
   */
  static formatDate(date: Date): string {
    return date.toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }

  /**
   * Truncate text to specified length with ellipsis
   */
  static truncateText(text: string, maxLength: number): string {
    if (text.length <= maxLength) {
      return text;
    }
    return text.substring(0, maxLength - 3) + '...';
  }

  /**
   * Parse lesson number from various formats (L01, lesson_01, etc.)
   */
  static parseLessonNumber(input: string): string | null {
    // Handle L01 format
    const lFormat = input.match(/L(\d{2})/);
    if (lFormat) {
      return input;
    }

    // Handle lesson_01 format
    const lessonFormat = input.match(/lesson_(\d{2})/);
    if (lessonFormat) {
      return "L" + lessonFormat[1];
    }

    // Handle plain number
    const numberMatch = input.match(/(\d{1,2})/);
    if (numberMatch) {
      const num = parseInt(numberMatch[1], 10);
      return "L" + String(num).padStart(2, "0");
    }

    return null;
  }

  /**
   * Convert lesson number to folder format (L01 -> lesson_01)
   */
  static lessonNumberToFolder(lessonNumber: string): string {
    return lessonNumber.replace('L', 'lesson_');
  }

  /**
   * Validate GitHub username format
   */
  static isValidGitHubUsername(username: string): boolean {
    // GitHub username rules: alphanumeric and hyphens, cannot start/end with hyphen
    const regex = /^[a-zA-Z0-9]([a-zA-Z0-9-]{0,37}[a-zA-Z0-9])?$/;
    return regex.test(username);
  }

  /**
   * Create a summary of sync results
   */
  static createSyncSummary(
    totalPRs: number, 
    processedCount: number, 
    updatedCount: number, 
    results: string[], 
    errors: string[]
  ): string {
    let summary = `Sync Complete: Processed ${processedCount}/${totalPRs} PRs, updated ${updatedCount} rows.`;
    
    if (results.length > 0) {
      summary += '\n\nResults:\n' + results.slice(0, 10).join('\n');
      if (results.length > 10) {
        summary += `\n... and ${results.length - 10} more`;
      }
    }
    
    if (errors.length > 0) {
      summary += '\n\nErrors:\n' + errors.slice(0, 5).join('\n');
      if (errors.length > 5) {
        summary += `\n... and ${errors.length - 5} more errors`;
      }
    }
    
    return summary;
  }

  /**
   * Retry function with exponential backoff
   */
  static async retryWithBackoff<T>(
    fn: () => T, 
    maxRetries: number = 3, 
    baseDelay: number = 1000
  ): Promise<T> {
    let lastError: Error;
    
    for (let attempt = 0; attempt <= maxRetries; attempt++) {
      try {
        return fn();
      } catch (error) {
        lastError = error instanceof Error ? error : new Error(String(error));
        
        if (attempt === maxRetries) {
          break;
        }
        
        const delay = baseDelay * Math.pow(2, attempt);
        Logger.log(`Attempt ${attempt + 1} failed, retrying in ${delay}ms: ${lastError.message}`);
        
        // Google Apps Script doesn't support async/await natively, but this pattern can be adapted
        Utilities.sleep(delay);
      }
    }
    
    throw lastError!;
  }

  /**
   * Deep clone an object (for caching purposes)
   */
  static deepClone<T>(obj: T): T {
    if (obj === null || typeof obj !== 'object') {
      return obj;
    }
    
    if (obj instanceof Date) {
      return new Date(obj.getTime()) as unknown as T;
    }
    
    if (Array.isArray(obj)) {
      return obj.map(item => this.deepClone(item)) as unknown as T;
    }
    
    const cloned = {} as T;
    for (const key in obj) {
      if (obj.hasOwnProperty(key)) {
        cloned[key] = this.deepClone(obj[key]);
      }
    }
    
    return cloned;
  }

  /**
   * Check if a string contains any of the specified patterns
   */
  static containsAny(text: string, patterns: string[]): boolean {
    const lowerText = text.toLowerCase();
    return patterns.some(pattern => lowerText.includes(pattern.toLowerCase()));
  }

  /**
   * Get file extension from filename
   */
  static getFileExtension(filename: string): string {
    const lastDot = filename.lastIndexOf('.');
    return lastDot === -1 ? '' : filename.substring(lastDot + 1).toLowerCase();
  }

  /**
   * Group array elements by a key function
   */
  static groupBy<T, K>(array: T[], keyFn: (item: T) => K): Map<K, T[]> {
    const groups = new Map<K, T[]>();
    
    array.forEach(item => {
      const key = keyFn(item);
      const group = groups.get(key) || [];
      group.push(item);
      groups.set(key, group);
    });
    
    return groups;
  }

  /**
   * Check if we're running in development mode
   */
  static isDevelopmentMode(): boolean {
    // You can set a script property for development mode
    const devMode = PropertiesService.getScriptProperties().getProperty("DEV_MODE");
    return devMode === "true";
  }

  /**
   * Log with different levels based on development mode
   */
  static logDebug(message: string): void {
    if (this.isDevelopmentMode()) {
      Logger.log(`[DEBUG] ${message}`);
    }
  }

  static logInfo(message: string): void {
    Logger.log(`[INFO] ${message}`);
  }

  static logError(message: string): void {
    Logger.log(`[ERROR] ${message}`);
  }
}