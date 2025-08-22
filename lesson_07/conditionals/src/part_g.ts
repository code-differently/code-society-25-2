/**
 * Finds the largest number in an array.
 *
 * @param numbers
 * @returns
 */
export function findLargestNumber(numbers: number[]): number {
  let largest = numbers[0];

  // loop through numbers using index
  for (let i = 1; i < numbers.length; i++) {
    if (numbers[i] > largest) {
      largest = numbers[i];
    }
  }

  return largest;


/**
 * Determines if a string is a palindrome (reads the same forwards and backwards).
 * Ignore case and spaces.
 *
 * @param text
 * @returns
 */
export function isPalindrome(text: string): boolean {
  //clean up input text and replace all spaces from the string
  // change all the uppercase letters to lowercase
  const clean = text.replace(/\s+/g, "").toLowerCase();
  // clean = amanaplanacanalpanama

  for (let i = 0; i < clean.length / 2; i++) {
    if (clean[i] !== clean[clean.length - 1 - i]) {
      return false;
    }
  }
  return true;
}
}

/**
 * Calculates the number of days until the next birthday.
 * Assume currentMonth and currentDay represent today's date,
 * and birthMonth and birthDay represent the birthday.
 *
 * @param currentMonth (1-12)
 * @param currentDay (1-31)
 * @param birthMonth (1-12)
 * @param birthDay (1-31)
 * @returns
 */
export function daysUntilBirthday(
  currentMonth: number,
  currentDay: number,
  birthMonth: number,
  birthDay: number
): number {
  throw new Error("Not implemented yet");
}
