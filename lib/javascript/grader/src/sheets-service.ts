/**
 * Google Sheets Service
 * Handles all interactions with Google Sheets including data reading, caching, and student lookup
 */

import { CACHE_CONFIG, GRADES_COLUMNS, ROSTER_COLUMNS, SHEET_NAMES } from './config';
import { CacheEntry, SheetRow, StudentLookupMap } from './types';

export class SheetsService {
  private static sheetDataCache = new Map<string, CacheEntry<SheetRow[]>>();
  private static studentLookupCache: StudentLookupMap | null = null;
  private static studentLookupTimestamp = 0;

  /**
   * Get cached sheet data with automatic refresh
   */
  static getCachedSheetData(sheetName: string): SheetRow[] {
    const cacheKey = `sheet-${sheetName}`;
    const cached = this.sheetDataCache.get(cacheKey);
    const now = Date.now();
    
    if (cached && (now - cached.timestamp) < CACHE_CONFIG.SHEET_CACHE_DURATION) {
      return cached.data;
    }
    
    // Refresh cache
    const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(sheetName);
    if (!sheet) {
      throw new Error(`Sheet '${sheetName}' not found`);
    }
    
    const lastRow = sheet.getLastRow();
    const lastCol = sheet.getLastColumn();
    
    if (lastRow === 0 || lastCol === 0) {
      return [];
    }
    
    const values = sheet.getRange(1, 1, lastRow, lastCol).getValues();
    this.sheetDataCache.set(cacheKey, { data: values, timestamp: now });
    
    return values;
  }

  /**
   * Get the assignments sheet with caching
   */
  static getAssignmentsSheet(): GoogleAppsScript.Spreadsheet.Sheet {
    const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(SHEET_NAMES.ASSIGNMENTS);
    if (!sheet) {
      throw new Error(`${SHEET_NAMES.ASSIGNMENTS} sheet not found`);
    }
    return sheet;
  }

  /**
   * Get the roster sheet with caching
   */
  static getRosterSheet(): GoogleAppsScript.Spreadsheet.Sheet {
    const sheet = SpreadsheetApp.getActiveSpreadsheet().getSheetByName(SHEET_NAMES.ROSTER);
    if (!sheet) {
      throw new Error(`${SHEET_NAMES.ROSTER} sheet not found`);
    }
    return sheet;
  }

  /**
   * Get student lookup map (cached for performance)
   */
  static getStudentLookupMap(): StudentLookupMap {
    const now = Date.now();
    
    if (this.studentLookupCache && 
        (now - this.studentLookupTimestamp) < CACHE_CONFIG.SHEET_CACHE_DURATION) {
      return this.studentLookupCache;
    }

    // Rebuild lookup cache
    const rollValues = this.getCachedSheetData(SHEET_NAMES.ROSTER);
    this.studentLookupCache = {};

    for (let i = 1; i < rollValues.length; i++) {
      const row = rollValues[i];
      if (row && row[ROSTER_COLUMNS.GITHUB_USERNAME - 1] && row[ROSTER_COLUMNS.FULL_NAME - 1]) {
        const githubUser = row[ROSTER_COLUMNS.GITHUB_USERNAME - 1].toString().toLowerCase();
        const fullName = row[ROSTER_COLUMNS.FULL_NAME - 1].toString();
        this.studentLookupCache[githubUser] = fullName;
      }
    }

    this.studentLookupTimestamp = now;
    return this.studentLookupCache;
  }

  /**
   * Lookup student full name using GitHub username (case-insensitive)
   */
  static lookupStudentName(githubUser: string): string | null {
    const lookupMap = this.getStudentLookupMap();
    return lookupMap[githubUser.toLowerCase()] || null;
  }

  /**
   * Find a matching row in the assignments sheet
   */
  static findAssignmentRow(lessonNumber: string, studentName: string): number {
    const values = this.getCachedSheetData(SHEET_NAMES.ASSIGNMENTS);
    
    for (let i = 1; i < values.length; i++) {
      const row = values[i];
      if (row[GRADES_COLUMNS.LESSON_NUMBER - 1] === lessonNumber && 
          row[GRADES_COLUMNS.STUDENT_NAME - 1] === studentName) {
        return i + 1; // Return 1-based row number
      }
    }
    
    return -1; // Not found
  }

  /**
   * Get current PR URL from a specific row
   */
  static getCurrentPrUrl(targetRow: number): string {
    const sheet = this.getAssignmentsSheet();
    const currentPrUrl = sheet.getRange(targetRow, GRADES_COLUMNS.PR_URL).getValue();
    return currentPrUrl ? currentPrUrl.toString().trim() : "";
  }

  /**
   * Get current grading status from a specific row
   */
  static getCurrentGradingStatus(targetRow: number): string {
    const sheet = this.getAssignmentsSheet();
    const gradingStatus = sheet.getRange(targetRow, GRADES_COLUMNS.GRADING_STATUS).getValue();
    return gradingStatus ? gradingStatus.toString().trim() : "";
  }

  /**
   * Update PR URL in the sheet
   */
  static updatePrUrl(targetRow: number, prHyperlink: string): void {
    const sheet = this.getAssignmentsSheet();
    sheet.getRange(targetRow, GRADES_COLUMNS.PR_URL).setValue(prHyperlink);
    
    // Invalidate cache after update
    this.invalidateSheetCache(SHEET_NAMES.ASSIGNMENTS);
  }

  /**
   * Update grading scores and status in batch
   */
  static updateGradingResults(
    targetRow: number, 
    functionalScore: number, 
    technicalScore: number, 
    stretchScore: number, 
    status: string
  ): void {
    const sheet = this.getAssignmentsSheet();
    
    // Batch update for better performance
    const updates = [
      [functionalScore],
      [technicalScore], 
      [stretchScore],
      [status]
    ];
    
    const startRow = targetRow;
    const startCol = GRADES_COLUMNS.FUNCTIONAL_SCORE;
    const numRows = 1;
    const numCols = 1;
    
    // Update each score individually (they're not contiguous columns)
    sheet.getRange(startRow, GRADES_COLUMNS.FUNCTIONAL_SCORE, numRows, numCols).setValues([[functionalScore]]);
    sheet.getRange(startRow, GRADES_COLUMNS.TECHNICAL_SCORE, numRows, numCols).setValues([[technicalScore]]);
    sheet.getRange(startRow, GRADES_COLUMNS.STRETCH_SCORE, numRows, numCols).setValues([[stretchScore]]);
    sheet.getRange(startRow, GRADES_COLUMNS.GRADING_STATUS, numRows, numCols).setValues([[status]]);
    
    // Invalidate cache after update
    this.invalidateSheetCache(SHEET_NAMES.ASSIGNMENTS);
  }

  /**
   * Update only grading status
   */
  static updateGradingStatus(targetRow: number, status: string): void {
    const sheet = this.getAssignmentsSheet();
    sheet.getRange(targetRow, GRADES_COLUMNS.GRADING_STATUS).setValue(status);
    
    // Invalidate cache after update
    this.invalidateSheetCache(SHEET_NAMES.ASSIGNMENTS);
  }

  /**
   * Get existing PR numbers to avoid reprocessing
   */
  static getExistingPrNumbers(): Set<string> {
    const values = this.getCachedSheetData(SHEET_NAMES.ASSIGNMENTS);
    const existingPRs = new Set<string>();
    
    values.forEach(row => {
      const prUrl = row[GRADES_COLUMNS.PR_URL - 1];
      if (prUrl && typeof prUrl === 'string') {
        const prNumber = prUrl.match(/#(\d+)/)?.[1];
        if (prNumber) {
          existingPRs.add(prNumber);
        }
      }
    });
    
    return existingPRs;
  }

  /**
   * Batch update multiple rows (for performance optimization)
   */
  static batchUpdateRows(updates: Array<{
    row: number;
    prUrl?: string;
    functionalScore?: number;
    technicalScore?: number;
    stretchScore?: number;
    status?: string;
  }>): void {
    if (updates.length === 0) return;
    
    const sheet = this.getAssignmentsSheet();
    
    // Group updates by column to minimize API calls
    const prUrlUpdates: Array<{row: number, value: string}> = [];
    const functionalUpdates: Array<{row: number, value: number}> = [];
    const technicalUpdates: Array<{row: number, value: number}> = [];
    const stretchUpdates: Array<{row: number, value: number}> = [];
    const statusUpdates: Array<{row: number, value: string}> = [];
    
    updates.forEach(update => {
      if (update.prUrl !== undefined) {
        prUrlUpdates.push({row: update.row, value: update.prUrl});
      }
      if (update.functionalScore !== undefined) {
        functionalUpdates.push({row: update.row, value: update.functionalScore});
      }
      if (update.technicalScore !== undefined) {
        technicalUpdates.push({row: update.row, value: update.technicalScore});
      }
      if (update.stretchScore !== undefined) {
        stretchUpdates.push({row: update.row, value: update.stretchScore});
      }
      if (update.status !== undefined) {
        statusUpdates.push({row: update.row, value: update.status});
      }
    });
    
    // Apply batched updates
    this.applyColumnUpdates(sheet, GRADES_COLUMNS.PR_URL, prUrlUpdates);
    this.applyColumnUpdates(sheet, GRADES_COLUMNS.FUNCTIONAL_SCORE, functionalUpdates);
    this.applyColumnUpdates(sheet, GRADES_COLUMNS.TECHNICAL_SCORE, technicalUpdates);
    this.applyColumnUpdates(sheet, GRADES_COLUMNS.STRETCH_SCORE, stretchUpdates);
    this.applyColumnUpdates(sheet, GRADES_COLUMNS.GRADING_STATUS, statusUpdates);
    
    // Invalidate cache after batch update
    this.invalidateSheetCache(SHEET_NAMES.ASSIGNMENTS);
  }

  /**
   * Apply updates to a specific column
   */
  private static applyColumnUpdates(
    sheet: GoogleAppsScript.Spreadsheet.Sheet, 
    column: number, 
    updates: Array<{row: number, value: any}>
  ): void {
    updates.forEach(update => {
      sheet.getRange(update.row, column).setValue(update.value);
    });
  }

  /**
   * Check if required sheets exist
   */
  static validateSheets(): {valid: boolean, missingSheets: string[]} {
    const ss = SpreadsheetApp.getActiveSpreadsheet();
    const missingSheets: string[] = [];
    
    if (!ss.getSheetByName(SHEET_NAMES.ASSIGNMENTS)) {
      missingSheets.push(SHEET_NAMES.ASSIGNMENTS);
    }
    
    if (!ss.getSheetByName(SHEET_NAMES.ROSTER)) {
      missingSheets.push(SHEET_NAMES.ROSTER);
    }
    
    return {
      valid: missingSheets.length === 0,
      missingSheets
    };
  }

  /**
   * Clear all cached data
   */
  static clearCache(): void {
    this.sheetDataCache.clear();
    this.studentLookupCache = null;
    this.studentLookupTimestamp = 0;
  }

  /**
   * Invalidate specific sheet cache
   */
  private static invalidateSheetCache(sheetName: string): void {
    const cacheKey = `sheet-${sheetName}`;
    this.sheetDataCache.delete(cacheKey);
  }
}