function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
    if (word.length < 1 || word.length > 25 || abbr.length < 1 || abbr.length > 15) {
        return false;
    }

    const hasNumbers = (str: string): boolean => /\d/.test(str);
    if (hasNumbers(word) || hasNumbers(abbr)) {
        return false;
    }

    let wordIndex = 0;
    let abbrIndex = 0;

    const letterToNumber = (char: string): number => {
        return char.charCodeAt(0) - 'a'.charCodeAt(0) + 1;
    };

    
}

