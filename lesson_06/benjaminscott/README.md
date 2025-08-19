# Stretch Assignment

```
function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
    var letterToNum = { "a": 1, "b": 2, "c": 3, "d": 4, "e": 5, "f": 6, "g": 7, "h": 8, "i": 9, "j": 10, "k": 11, "l": 12, "m": 13, "n": 14, "o": 15, "p": 16, "q": 17, "r": 18, "s": 19, "t": 20, "u": 21, "v": 22, "w": 23, "x": 24, "y": 25, "z": 26 };
    let wordIndex = 0;
    let abbrIndex = 0;

    while (abbrIndex < abbr.length && wordIndex < word.length) {
        if (abbr[abbrIndex] === word[wordIndex]) {
            wordIndex++;

        } else {
            const currentAbbrChar = abbr[abbrIndex];

            if (currentAbbrChar < 'a' || currentAbbrChar > 'z') {
                return false;
            }

            const targetNumber = letterToNum[currentAbbrChar];

            wordIndex += targetNumber;
        }

        abbrIndex++;
    }

    if (abbrIndex === abbr.length && wordIndex === word.length) {
        return true;
    }
    return false;
}
```
