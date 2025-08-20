function letterToNumber(ch) {
    return ch.charCodeAt(0) - 96;
}

function validAbbreviation(word, abbr) {
    var i = 0; // index for word
    var j = 0; // index for abbr
    var lastWasNumber = false;
    while (j < abbr.length) {
        var ch = abbr[j];
        var num1 = letterToNumber(ch);
        var skipCount = null;
        if (num1 >= 1 && num1 <= 26) {
            skipCount = num1;
            if (j + 1 < abbr.length) {
                var num2 = letterToNumber(abbr[j + 1]);
                var combined = num1 * 10 + num2;
                if (combined >= 10 && combined <= 26) {
                    skipCount = combined;
                    j++;
                }
            }
        }
        if (skipCount !== null && skipCount > 0) {
            if (lastWasNumber)
                return false;
            i += skipCount;
            if (i > word.length)
                return false;
            lastWasNumber = true;
            j++;
            continue;
        }
        
        if (i >= word.length || word[i] !== ch) {
            return false;
        }
        i++;
        j++;
        lastWasNumber = false;
    }
    // Must consume the whole word
    return i === word.length;
}
// Test the function
console.log(validAbbreviation("internationalization", "i12iz4n"));
console.log(validAbbreviation("apple", "a2e")); 
