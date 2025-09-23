/**
 * GitHub API Service
 * Handles all interactions with the GitHub API including fetching PRs, files, and creating reviews
 */

import { CACHE_CONFIG, GITHUB_CONFIG } from './config';
import { CacheEntry, GitHubFile, PullRequest } from './types';

export class GitHubApiService {
  private static apiCache = new Map<string, CacheEntry<any>>();
  
  /**
   * Get recent pull requests from GitHub repository
   */
  static getRecentPullRequests(): PullRequest[] {
    const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
    if (!token) {
      throw new Error("GitHub token not found. Please set up your GitHub token first.");
    }

    try {
      // Get only open PRs for grading
      const openPRs = this.fetchPullRequests('open');
      
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
   * Fetch pull requests with specified state (with caching)
   */
  private static fetchPullRequests(state: 'open' | 'closed'): PullRequest[] {
    const cacheKey = `prs-${state}`;
    const cached = this.getCachedData<PullRequest[]>(cacheKey);
    
    if (cached) {
      return cached;
    }

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

    const data = JSON.parse(response.getContentText());
    this.setCachedData(cacheKey, data);
    return data;
  }

  /**
   * Get files changed in a specific pull request (optimized with limits)
   */
  static getPullRequestFiles(prNumber: number, maxFiles: number = CACHE_CONFIG.MAX_FILES_PER_PR): string[] {
    const cacheKey = `pr-files-${prNumber}`;
    const cached = this.getCachedData<string[]>(cacheKey);
    
    if (cached) {
      return cached.slice(0, maxFiles); // Apply limit even to cached data
    }

    const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
    if (!token) {
      Logger.log("GITHUB_TOKEN not found in script properties");
      return [];
    }

    let files: string[] = [];
    let page = 1;

    while (files.length < maxFiles) {
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

        const remainingSlots = maxFiles - files.length;
        files = files.concat(json.slice(0, remainingSlots).map((f: any) => f.filename));
        
        if (json.length < 100) break; // No more pages
        page++;
      } catch (error) {
        const errorMessage = error instanceof Error ? error.message : String(error);
        Logger.log("Error fetching PR files: " + errorMessage);
        break;
      }
    }

    this.setCachedData(cacheKey, files);
    return files;
  }

  /**
   * Get PR content including diff and file contents (optimized)
   */
  static getPullRequestContent(prNumber: number): { files: GitHubFile[], diff: string } {
    const cacheKey = `pr-content-${prNumber}`;
    const cached = this.getCachedData<{ files: GitHubFile[], diff: string }>(cacheKey);
    
    if (cached) {
      return cached;
    }

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
        const diff = response.getContentText().substring(0, CACHE_CONFIG.MAX_DIFF_LENGTH);
        
        const result = { files, diff };
        this.setCachedData(cacheKey, result);
        return result;
      }
    } catch (error) {
      const errorMessage = error instanceof Error ? error.message : String(error);
      Logger.log("Error fetching PR content: " + errorMessage);
    }

    return { files: [], diff: "" };
  }

  /**
   * Fetch grading instructions (GRADING-COPILOT.md) from the lesson folder
   */
  static fetchGradingInstructions(lessonNumber: string): string | null {
    const cacheKey = `grading-instructions-${lessonNumber}`;
    const cached = this.getCachedData<string | null>(cacheKey);
    
    if (cached !== undefined) {
      return cached;
    }

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
        const content = Utilities.newBlob(Utilities.base64Decode(json.content)).getDataAsString();
        this.setCachedData(cacheKey, content);
        return content;
      } else if (response.getResponseCode() === 404) {
        Logger.log(`GRADING-COPILOT.md not found for ${lessonFolder}`);
        this.setCachedData(cacheKey, null);
        return null;
      } else {
        Logger.log(`Error fetching GRADING-COPILOT.md: ${response.getResponseCode()}`);
        return null;
      }
    } catch (error) {
      const errorMessage = error instanceof Error ? error.message : String(error);
      Logger.log("Error fetching grading instructions: " + errorMessage);
      this.setCachedData(cacheKey, null);
      return null;
    }
  }

  /**
   * Create a draft review comment on the PR
   */
  static createDraftReview(prNumber: number, reviewBody: string): boolean {
    const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
    if (!token) {
      Logger.log("GITHUB_TOKEN not found");
      return false;
    }

    try {
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
   * Get a single PR by number (for testing)
   */
  static getPullRequest(prNumber: number): PullRequest | null {
    const token = PropertiesService.getScriptProperties().getProperty("GITHUB_TOKEN");
    if (!token) {
      return null;
    }

    try {
      const url = `${GITHUB_CONFIG.API_BASE}/repos/${GITHUB_CONFIG.OWNER}/${GITHUB_CONFIG.REPO}/pulls/${prNumber}`;
      const response = UrlFetchApp.fetch(url, {
        headers: {
          "Authorization": "token " + token,
          "Accept": "application/vnd.github.v3+json"
        }
      });

      if (response.getResponseCode() === 200) {
        return JSON.parse(response.getContentText());
      }
    } catch (error) {
      Logger.log(`Error fetching PR #${prNumber}: ${error}`);
    }
    
    return null;
  }

  /**
   * Cache management methods
   */
  private static getCachedData<T>(key: string): T | undefined {
    const cached = this.apiCache.get(key);
    if (cached && (Date.now() - cached.timestamp) < CACHE_CONFIG.API_CACHE_DURATION) {
      return cached.data as T;
    }
    return undefined;
  }

  private static setCachedData<T>(key: string, data: T): void {
    this.apiCache.set(key, { data, timestamp: Date.now() });
  }

  /**
   * Clear all cached data (useful for testing or forced refresh)
   */
  static clearCache(): void {
    this.apiCache.clear();
  }
}