function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
  let i = 0; // pointer for word
  let j = 0; // pointer for abbr 
  let prevWasSkip = true;



  // Helper: convert 'a'..'z' → 1..26
  function alphaToNum(ch: string): number {
    return ch.charCodeAt(0) - 'a'.charCodeAt(0) + 1;
  }

  // Helper: check if this is a skip letter
  function isSkipLetter(ch: string): boolean {
    if (ch.length !== 1) return false;
    const num = alphaToNum(ch);
    return num >= 1 && num <= 26;
  }
  
  
    while (j < abbr.length && i < word.length) {
        const ch = abbr[j];
        if (!prevWasSkip) {

            if (isSkipLetter(ch)) {
                const skipCount = alphaToNum(ch);
                if (i + skipCount > word.length) {
                    return false; // Skip goes beyond word length
                }
              

                prevWasSkip = true;
                j++;
                i+= skipCount; // Skip ahead in abbr
                
                continue;
            } else {
                return false; // Invalid abbreviation
            }
        }

        else if (word[i] === ch) {
            i++;
            j++;
            prevWasSkip = false;
        } else {
            return false; // Mismatch found
        }
    }

  
  return true;
}

// --- test harness skeleton ---
console.log('a'.charCodeAt(0));  // What number do you get?
console.log('b'.charCodeAt(0));  // What about this one?
console.log('z'.charCodeAt(0));  // And this?
console.log(isValidAlphaAbbreviation("internationalization", "imzdn")); // expect true
console.log(isValidAlphaAbbreviation("substitution", "sjn")); // expect true
console.log(isValidAlphaAbbreviation("substitution", "seen")); // expect false
console.log(isValidAlphaAbbreviation("test", "ab")); // expect false
