/**
 * Utility class for checking alphabet-based abbreviations.
 *
 * An abbreviation is considered valid if:
 * - It starts and ends with the same character as the word.
 * - It correctly skips letters based on alphabetical offsets.
 */
export class Abbreviation {
  /**
   * Determines whether a given abbreviation is valid for a given word.
   *
   * The abbreviation alternates between:
   * - a "number" step: an abbreviation character represents a skip of
   *   `charCode - 'a'.charCodeAt(0) + 1` positions in the word.
   * - a "letter" step: the abbreviation character must directly match the word.
   *
   * @param {string} word - The full word to validate against.
   * @param {string} abbr - The abbreviation to check.
   * @return {boolean} True if the abbreviation is valid for the word, false otherwise.
   */
  isValidAlphaAbbreviation(word: string, abbr: string): boolean {
    if (word.length < abbr.length) {
      return false;
    }

    // First and last characters must match.
    if (
      word[0] !== abbr[0] ||
      word[word.length - 1] !== abbr[abbr.length - 1]
    ) {
      return false;
    }

    let num = true;
    let wordIndex = 1;
    let abbrIndex = 1;

    while (wordIndex < word.length && abbrIndex < abbr.length) {
      // If num is true, treat the abbreviation character as a number.
      // If num is false, treat it as a letter.
      if (num) {
        // Interpret abbreviation character as a skip.
        const skip = abbr[abbrIndex].charCodeAt(0) - "a".charCodeAt(0) + 1;
        const newWordIndex = wordIndex + skip;
        // If out of bounds, check if it matches the last character.
        if (newWordIndex >= word.length) {  
          if (word[wordIndex] !== abbr[abbrIndex]) {
            return false;
          }
          wordIndex++;
        } else {
          wordIndex = newWordIndex;
          num = false;
        }
        abbrIndex++;
      } else {
        // Match letters if num isn't true.
        if (word[wordIndex] !== abbr[abbrIndex]) {
          return false;
        }
        wordIndex++;
        abbrIndex++;
        num = true;
      }
    }

    return wordIndex === word.length && abbrIndex === abbr.length;
  }
}

// Example usage:
const abbrChecker = new Abbreviation();
console.log(abbrChecker.isValidAlphaAbbreviation("internationalization", "irn"),); // true
console.log(abbrChecker.isValidAlphaAbbreviation("internationalization", "imzdn"),); // true
console.log(abbrChecker.isValidAlphaAbbreviation("apple", "abe")); // false
console.log(abbrChecker.isValidAlphaAbbreviation("bags", "bas")); // false
