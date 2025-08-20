function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
    let wordlength = word.length;
    let abbrlength = abbr.length;

    if (wordlength < abbrlength) return false;
    if (word.length < 1 || word.length > 25) return false;
    if (abbr.length < 1 || abbr.length > 15) return false;
    if (!/^[a-z]+$/.test(word)) return false;
    if (!/^[a-z]+$/.test(abbr)) return false;

    

    return true;
}