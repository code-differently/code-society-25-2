# Alphabetic Abbreviation Calculator

## Overview

This project implements a special alphabetic abbreviation system where letters of the alphabet represent both direct character matches and numeric skip values. It allows validating whether a given abbreviation correctly represents a longer word according to specific encoding rules.

## Alphabet to Number Mapping

In this system, each letter corresponds to a number:

```
a = 1    g = 7     m = 13    s = 19    y = 25
b = 2    h = 8     n = 14    t = 20    z = 26
c = 3    i = 9     o = 15    u = 21
d = 4    j = 10    p = 16    v = 22
e = 5    k = 11    q = 17    w = 23
f = 6    l = 12    r = 18    x = 24
```

## How the Abbreviation System Works

The abbreviation follows these key rules:

1. Letters in the abbreviation can either:
   - **Represent themselves directly** (direct character match)
   - **Represent a "skip"** of characters (using their numeric value)

2. When a letter is used as a skip, it skips a number of characters equal to its numeric value.

3. No adjacent letters in the abbreviation can both represent skips (no adjacent abbreviated substrings).

4. Empty substrings cannot be abbreviated (must skip at least 1 character).

5. The word length is constrained to 1-25 characters.

6. The abbreviation length is constrained to 1-15 characters.

## Examples

### Example 1: "internationalization"

Abbreviation: `imzdn` is valid because:
- `i` matches the first character directly
- `m` (value 13) skips 13 characters "nternationaliza"
- `z` matches the next character directly
- `d` (value 4) skips 4 characters "atio"
- `n` matches the final character directly

Therefore: `i + [13 chars skipped] + z + [4 chars skipped] + n = "internationalization"`

### Example 2: "substitution"

Abbreviation: `sjn` is valid because:
- `s` matches the first character directly
- `j` (value 10) skips 10 characters "ubstitutio"
- `n` matches the final character directly

Therefore: `s + [10 chars skipped] + n = "substitution"`

### Example 3: "test" (Invalid Case)

Abbreviation: `ab` is invalid because:
- Both `a` and `b` would need to represent skips (adjacent skips not allowed)
- `a` skips 1 character, `b` skips 2 characters
- This would violate rule #3 (no adjacent skips)

## User Stories

### User Story 1
**As a** language processing system user,  
**I want to** validate if an abbreviation is valid for a given word,  
**So that** I can efficiently verify shorthand representations.

### User Story 2
**As a** text editor user,  
**I want to** create valid abbreviations for long words,  
**So that** I can save time and space in my writing.

### User Story 3
**As a** student learning abbreviation systems,  
**I want to** understand how alphabetic abbreviation works,  
**So that** I can apply it correctly in my note-taking.

### User Story 4
**As a** developer,  
**I want to** implement a function that validates alphabetic abbreviations,  
**So that** I can integrate it into my text processing applications.

## Solution Approach

### Two-Pointer Method for Validation

The solution uses a two-pointer approach to efficiently validate abbreviations:

1. Initialize two pointers:
   - `i`: Current position in the word
   - `j`: Current position in the abbreviation
   - `prevWasSkip`: Tracks if the previous character was used as a skip

2. For each character in the abbreviation:
   - Try direct match: Check if the current characters in both strings match
   - Try skip: If the previous character wasn't a skip, interpret the current character as a skip value

3. The algorithm enforces all rules:
   - No adjacent skips
   - Valid word and abbreviation lengths
   - Complete consumption of both the word and abbreviation

### First-Letter Abbreviation Creation

The solution also includes a function to create abbreviations by taking the first letter of each word:

1. Split the input string by whitespace to get individual words
2. Iterate through each word and extract its first character
3. Concatenate these first characters to form the abbreviation

## Implementation Details

- **Time Complexity**: O(N) where N is the word length
- **Space Complexity**: O(1) for the two-pointer approach
- **Input Constraints**:
  - All inputs are lowercase English letters
  - Word length: 1-25 characters
  - Abbreviation length: 1-15 characters

## Usage

```typescript
// Example usage for validation
const result1 = isValidAlphaAbbreviation("internationalization", "imzdn");
console.log(result1);  // true

const result2 = isValidAlphaAbbreviation("substitution", "sjn");
console.log(result2);  // true

const result3 = isValidAlphaAbbreviation("test", "ab");
console.log(result3);  // false

// Creating first-letter abbreviations
const abbr = createFirstLetterAbbreviation("United States of America");
console.log(abbr);  // "USoA"
```

## Testing Scenarios

1. **Basic Match**: Simple direct character matches
2. **Skip Usage**: Using letters to skip characters
3. **Mixed Usage**: Combination of direct matches and skips
4. **Edge Cases**: 
   - Minimum length words and abbreviations
   - Maximum length words and abbreviations
   - Attempting adjacent skips (should fail)
   - Attempting to skip empty substrings (should fail)