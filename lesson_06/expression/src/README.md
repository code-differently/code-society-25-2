# Stretch Assignment

```typescript
/**
 * Validates whether a given alphabetic abbreviation matches a word using a special encoding system.
 *
 * In this system:
 * - Numbers in abbreviations are replaced by their corresponding alphabet letters (a=1, b=2, ..., z=26)
 * - The abbreviation follows the same rules as standard abbreviations but uses letters instead of digits
 * - Letters cannot have leading zeros (e.g., no 'aa' for 01)
 * - Adjacent abbreviated substrings are not allowed
 * - Empty substrings cannot be abbreviated
 *
 * @param word - The original word to match against (1-25 characters, lowercase English letters)
 * @param abbr - The alphabetic abbreviation to validate (1-15 characters, lowercase English letters)
 * @returns true if the abbreviation is valid for the given word, false otherwise
 *
 * @example
 * // Example 1: Valid abbreviation
 * isValidAlphaAbbreviation("internationalization", "irzdn")
 * // Returns: true
 * // Explanation: i + (r=18 chars) + z + (d=4 chars) + n = "internationalization"
 */

function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
  /**
   * Hashmap mapping every letter to its numerical value in the encoding system.
   * Used to convert letters to their corresponding skip counts.
   * @type {Record<string, number>}
   */
  const letterToNumberMap: Record<string, number> = {
    a: 1,
    b: 2,
    c: 3,
    d: 4,
    e: 5,
    f: 6,
    g: 7,
    h: 8,
    i: 9,
    j: 10,
    k: 11,
    l: 12,
    m: 13,
    n: 14,
    o: 15,
    p: 16,
    q: 17,
    r: 18,
    s: 19,
    t: 20,
    u: 21,
    v: 22,
    w: 23,
    x: 24,
    y: 25,
    z: 26,
  };

  // Input validation: abbreviation cannot be longer than the original word
  if (word.length < abbr.length) {
    return false; // Abbreviation cannot be longer than the word
  }

  // Input validation: both strings must be non-empty
  if (word.length == 0 || abbr.length == 0) {
    return false; // Both word and abbreviation must be non-empty
  }

  let wordIndex = 0; // Current position in the original word

  // Process each character in the abbreviation
  for (
    let abbreviationIndex = 0;
    abbreviationIndex < abbr.length;
    abbreviationIndex++
  ) {
    // Pattern: even indices are literal characters, odd indices are skip counts
    if (abbreviationIndex % 2 == 0) {
      // Even index: treat as a literal character that must match the word
      if (abbr[abbreviationIndex] !== word[wordIndex]) {
        return false; // Invalid character in abbreviation
      } else {
        wordIndex++; // Move to next character in word
      }
    } else {
      // Odd index: treat as a letter representing a number of characters to skip
      wordIndex += letterToNumberMap[abbr[abbreviationIndex]];
    }
  }
  // Verify that we've consumed the entire word (no extra characters left)
  return wordIndex === word.length; // Ensure we have matched the entire word
}
```
