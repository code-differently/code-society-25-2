# isValidAlphaAbbreviation Functionality in TypeScript

This markdown file documents the implementation of a function that validates whether an abbreviation is a correct ***alpha abbreviation*** of a given word. It includes code snippets, test cases to verify correctness, and an explanation of the logic used. The document also highlights key considerations when working with TypeScript vs. plain JavaScript.

## isValidAlphaAbbreviation.ts

### Params:
- word: string → the original word to validate against.
- abbr: string → the abbreviation sequence to check.

### Return:
- boolean → true if the abbreviation correctly represents the word, false otherwise.

```typescript
function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
    if(word == "" || abbr == "") return false;
    if(word.length < abbr.length) return false;
    
    // Letters mapped to numeric skip values (a=1, ..., z=26)
    const AlphaToNumberValues = {
        "a": 1, "b": 2, "c": 3, "d": 4, "e": 5, "f": 6, "g": 7, "h": 8, "i": 9,
        "j": 10, "k": 11, "l": 12, "m": 13, "n": 14, "o": 15, "p": 16, "q": 17,
        "r": 18, "s": 19, "t": 20, "u": 21, "v": 22, "w": 23, "x": 24,
        "y": 25, "z": 26    
    };

    let wordIndex = 0;
    let isAbbreviation = true;
    for (let abbrIndex = 0; abbrIndex < abbr.length; abbrIndex++) {
        // Even indices: literal character match
        if(abbrIndex % 2 == 0) {
            if(abbr[abbrIndex] != word[wordIndex]) {
                isAbbreviation = false;
                break;
            }
            else {
                wordIndex++;
            }
        }
        // Odd indices: skip value
        else if(abbrIndex % 2 == 1) {
            wordIndex += AlphaToNumberValues[abbr[abbrIndex]];
        }
    }

    return isAbbreviation && wordIndex === word.length;
}
```

### Example Test Cases:
```typescript
    console.log("\n--- Test Case 1 ---")
    console.log("isValidAlphaAbbreviation(word: internationalization, abbr: imzdn) Should Return True")
    console.log("Result: ", isValidAlphaAbbreviation("internationalization", "imzdn"))

    console.log("\n--- Test Case 2 ---")
    console.log("isValidAlphaAbbreviation(word: internationalization, abbr: imz) Should Return False")
    console.log("Result: ", isValidAlphaAbbreviation("internationalization", "imz"))

    console.log("\n--- Test Case 3 ---")
    console.log("isValidAlphaAbbreviation(word: substitution, abbr: sjn) Should Return True")
    console.log("Result: ", isValidAlphaAbbreviation("substitution", "sjn"))

    console.log("\n--- Test Case 4 ---")
    console.log("isValidAlphaAbbreviation(word: substitution, abbr: san) Should Return False")
    console.log("Result: ", isValidAlphaAbbreviation("substitution", "san"))
    
    console.log("\n--- Test Case 5 ---")
    console.log("isValidAlphaAbbreviation(word: test, abbr: ab) Should Return False")
    console.log("Result: ", isValidAlphaAbbreviation("test", "ab")) 

    console.log("\n--- Test Case 6 ---")
    console.log("isValidAlphaAbbreviation(word: test, abbr: tbt) Should Return True")
    console.log("Result: ", isValidAlphaAbbreviation("test", "tbt")) 
```
