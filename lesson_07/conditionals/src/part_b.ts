/**
 * Returns true if the given year is a leap year, otherwise false.
 *
 * @param year
 * @returns
 */
export function isLeapYear(year: number): boolean {
  if (year % 400 === 0) {
    return true; // Divisible by 400
  }
  if (year % 100 === 0) {
    return false; // Divisible by 100 but not 400
  }
  if (year % 4 === 0) {
    return true; // Divisible by 4 but not 100
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
  } else return "odd";
}

/**
 * Returns whether a word contains a vowel or not.
 *
 * @param word
 * @returns
 */
export function hasVowel(word: string): boolean {
  const v = ["a", "e", "i", "o", "u"];
  for (let i = 0; i < word.length; i++) {
    if (v.includes(word.charAt(i))) {
      return true;
    }
  }

  return false;
}
