export function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
  let i = 0; // position in word
  let j = 0; // position in abbreviation

  while (j < abbr.length) {
    const ch = abbr[j];

    if (ch >= "0" && ch <= "9") {
      // Leading zero check
      if (ch === "0") return false;

      // Parse complete number
      let num = 0;
      while (j < abbr.length && abbr[j] >= "0" && abbr[j] <= "9") {
        num = num * 10 + (abbr[j].charCodeAt(0) - "0".charCodeAt(0));
        j++;
      }

      // Apply skip
      i += num;

      // Bounds check
      if (i > word.length) return false;
    } else {
      // Literal character match
      if (i >= word.length || word[i] !== ch) {
        return false;
      }
      i++;
      j++;
    }
  }

  // Must consume entire word
  return i === word.length;
}
