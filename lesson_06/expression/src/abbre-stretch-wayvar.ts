function isValidAlphaAbbreviation(word: string, abbr: string): boolean {

    if (word.length < 1 || word.length > 25) return false;
    if (abbr.length < 1 || abbr.length > 15) return false;
    if (!/^[a-z]+$/.test(word)) return false;
    if (!/^[a-z]+$/.test(abbr)) return false;
    if (abbr.length > word.length) return false;

    let built = "";
    let i = 0;

    while (i < word.length) {
        const ch = word[i];

        built += ch;
        i++;

        if (i < word.length) {
        const skip = ch.charCodeAt(0) - 96;
        i += skip; 
        }
    }

return built === abbr;
}
console.log(isValidAlphaAbbreviation("abbreviation", "acefn")); // true
console.log(isValidAlphaAbbreviation("abbreviation", "acehn")); // false
console.log(isValidAlphaAbbreviation("abbreviation", "acgn")); // false
console.log(isValidAlphaAbbreviation("internationalization", "imzdn")); // true
console.log(isValidAlphaAbbreviation("internationalization", "inz")); // false