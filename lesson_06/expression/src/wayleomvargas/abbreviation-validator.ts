export function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
    if (word.length < 1 || word.length > 25) return false;
    if (abbr.length < 1 || abbr.length > 15) return false;
    if (!/^[a-z]+$/.test(word)) return false;
    if (!/^[a-z]+$/.test(abbr)) return false;
    
    let wordIndex = 0;
    let i = 0;
    
    while (i < abbr.length && wordIndex < word.length) {
        const ch: string = abbr[i];
        
        if (word[wordIndex] === ch) {
            wordIndex++;
            i++;
        } else {
            const skip: number = ch.charCodeAt(0) - 96;
            wordIndex += skip;
            i++;
        }
    }
    
    return wordIndex === word.length && i === abbr.length;
}