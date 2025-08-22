/** Alphabetic Abbreviation Calculator
 * This module implements a special encoding system for validating and creating
 * alphabetic abbreviations where letters represent both direct matches and skips.
 */
/**
 * Converts a letter to its corresponding numeric value
 * 'a' = 1, 'b' = 2, ..., 'z' = 26
 * @param ch A lowercase letter
 * @returns The numeric value corresponding to the letter
 */
function letterToNum(ch: string): number {
  return ch.charCodeAt(0) - 'a'.charCodeAt(0) + 1;
}

/**
 * Validates whether a given abbreviation matches a word using an alphabetic encoding system.
 * This implementation uses a two-pointer approach to simulate the abbreviation process
 * @param word The original word (lowercase letters only)
 * @param abbr The proposed abbreviation (lowercase letters only)
 * @returns true if the abbreviation is valid for the word, false otherwise
 */
export function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
  // Validate constraints from the problem
  if (word.length < 1 || word.length > 25) return false;
  if (abbr.length < 1 || abbr.length > 15) return false;

  // Ensure inputs are lowercase a-z only (per spec)
  if (!/^[a-z]+$/.test(word) || !/^[a-z]+$/.test(abbr)) return false;

  // Initialize two pointers: i for word and j for abbreviation
  let i = 0; // word index
  let j = 0; // abbreviation index
  let prevWasSkip = false; // track if previous character was a skip

  // Process while we have characters in both strings
  while (i < word.length && j < abbr.length) {
    const currentChar = abbr[j];

    // Case 1: Direct character match
    if (word[i] === currentChar) {
      i++; // move to next character in word
      j++; // move to next character in abbr
      prevWasSkip = false; // reset skip flag
      continue;
    }
    // Otherwise, interpret the abbr char as a skip value (letter -> number)
    // Only allowed if previous wasn't a skip (no adjacent skips)
    if (prevWasSkip) {
      return false; // adjacent skips not allowed
    }

    // Calculate how many characters to skip
    const skipCount = letterToNum(currentChar);

    // Skip must be valid (at least 1 and not overflow the word)
    if (skipCount < 1 || i + skipCount > word.length) {
      return false;
    }

    // Apply the skip
    i += skipCount; // skip characters in word
    j++; // move to next character in abbr
    prevWasSkip = true; // mark that we just performed a skip
  }

  // For a valid abbreviation, both strings must be fully consumed
  return i === word.length && j === abbr.length;
}

/**
 * Creates an abbreviation by taking the first character of each word in a sentence.
 * @param sentence A string containing one or more words separated by spaces
 * @returns An abbreviation string consisting of the first letter of each word
 */
export function createFirstLetterAbbreviation(sentence: string): string {
  // Check if input is valid
  if (!sentence || sentence.trim().length === 0) {
    return '';
  }

  // Split the sentence into words
  const words = sentence.trim().split(/\s+/);

  // Take the first character of each word
  let abbreviation = '';
  for (const word of words) {
    if (word.length > 0) {
      abbreviation += word[0];
    }
  }

  return abbreviation;
}

/**
 * Generates a valid alphabetic abbreviation for a word if possible.
 * Tries to create an abbreviation that balances length and readability.
 * @param word The word to abbreviate (lowercase letters only)
 * @returns A valid abbreviation or null if no good abbreviation could be generated
 */
export function generateAlphaAbbreviation(word: string): string | null {
  // Basic validation
  if (word.length < 1 || word.length > 25) {
    return null;
  }

  // For very short words, just return the word itself
  if (word.length <= 3) {
    return word;
  }

  // For medium-length words, use first letter + last letter
  if (word.length <= 7) {
    const skipChar = String.fromCharCode('a'.charCodeAt(0) + word.length - 2);
    return word[0] + skipChar;
  }

  // For longer words, use a more sophisticated approach
  let abbreviation = '';
  let remaining = word;

  // Always include the first character
  abbreviation += remaining[0];
  remaining = remaining.substring(1);

  // While we still have a long enough remaining string
  while (remaining.length > 5) {
    // Skip chunks of 5-15 characters when possible
    const skipLength = Math.min(15, remaining.length - 2);

    // Find the letter representing this skip
    // If skipLength > 26, we can't represent it with a single letter
    if (skipLength <= 26) {
      const skipChar = String.fromCharCode('a'.charCodeAt(0) + skipLength - 1);
      abbreviation += skipChar;
      remaining = remaining.substring(skipLength);

      // If we can, include a direct character match
      if (remaining.length > 0) {
        abbreviation += remaining[0];
        remaining = remaining.substring(1);
      }
    } else {
      // If we can't represent with a single letter, just include the next character
      abbreviation += remaining[0];
      remaining = remaining.substring(1);
    }
  }

  // Add the last character if we have remaining characters
  if (remaining.length > 0) {
    abbreviation += remaining[remaining.length - 1];
  }

  // Verify that our abbreviation is valid
  if (isValidAlphaAbbreviation(word, abbreviation)) {
    return abbreviation;
  }

  return null;
}

