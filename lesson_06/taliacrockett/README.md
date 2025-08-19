## Function Signature

```
function isValidAlphaAbbreviation(word: string, abbr: string): boolean 
```

### Abbreviation Implementation
```
function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
    let wordIndex = 0;
    let abbrIndex = 0;
    
    while (abbrIndex < abbr.length && wordIndex < word.length) {
        const currentChar = abbr[abbrIndex];
        
        // Try literal match first
        if (word[wordIndex] === currentChar) {
            wordIndex++;
            abbrIndex++;
        }
        // Otherwise, treat as skip operation
        else {
            const skipCount = currentChar.charCodeAt(0) - 'a'.charCodeAt(0) + 1;
            
            // Validate skip is possible
            if (wordIndex + skipCount > word.length) {
                return false;
            }
            
            // Perform skip
            wordIndex += skipCount;
            abbrIndex++;
        }
    }
    
    // Both should be fully consumed
    return wordIndex === word.length && abbrIndex === abbr.length;
}
```