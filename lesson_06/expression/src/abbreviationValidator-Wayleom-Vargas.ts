function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
    if (word.length < 1 || word.length > 25) return false;
    if (abbr.length < 1 || abbr.length > 15) return false;
    if (!/^[a-z]+$/.test(word)) return false;
    if (!/^[a-z]+$/.test(abbr)) return false;
    
    let wordIndex: number = 0;
    let i: number = 0;
    
    while (i < abbr.length && wordIndex < word.length) {
        const ch: string = abbr[i]; // Process abbreviation, not word
        
        if (word[wordIndex] === ch) {
            // Literal character match
            wordIndex++;
            i++;
        } else {
            // Character represents a number - skip that many characters
            const skip: number = ch.charCodeAt(0) - 96; // a=1, b=2, etc.
            wordIndex += skip;
            i++;
        }
    }
    
    return wordIndex === word.length && i === abbr.length;
}