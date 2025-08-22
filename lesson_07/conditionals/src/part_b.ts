/**
 * Returns true if the given year is a leap year, otherwise false.
 *
 * @param year
 * @returns
 */
export function isLeapYear(year: number): boolean {
  if (year % 4 === 0 && year % 100 !== 0) {
    return true;
  }
  if (year % 400 === 0) {
    return true;
  }
    return false;
}
/**
 * Returns whether the given number is even or odd.
 *
 * @param num
 * @returns
 */
export function isEvenOrOdd(num: number): string {
  if (num % 2 === 0) {
    return "even";
  } 
  else {
    return "odd";
  }
}
/**
 * Returns whether a word contains a vowel or not.
 *
 * @param word
 * @returns
 */
export function hasVowel(word: string): boolean {
  word = word.toLowerCase();

  if (
    word.includes("a") ||
    word.includes("e") ||
    word.includes("i") ||
    word.includes("o") ||
    word.includes("u")
  ) {
    return true;
  } 
  else {
    return false;
  }
}
