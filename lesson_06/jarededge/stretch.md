```ts
export function isValidAlphaAbbreviation(word: string, abbr: string): boolean {
  let i = 0;
  let j = 0;

  while (j < abbr.length) {
    const ch = abbr[j];

    if (ch >= "0" && ch <= "9") {
      if (ch === "0") return false;

      let num = 0;
      while (j < abbr.length && abbr[j] >= "0" && abbr[j] <= "9") {
        num = num * 10 + (abbr[j].charCodeAt(0) - "0".charCodeAt(0));
        j++;
      }

      i += num;
      if (i > word.length) return false;
    } else {
      if (i >= word.length || word[i] !== ch) {
        return false;
      }
      i++;
      j++;
    }
  }

  return i === word.length;
}
```
