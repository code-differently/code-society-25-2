/**
 * Finds the largest number in an array.
 *
 * @param numbers
 * @returns
 */
export function findLargestNumber(numbers: number[]): number {
  if (numbers.length === 0) {
    return 0;
  }

  let largest = numbers[0];
  for (let i = 1; i < numbers.length; i++) {
    if (numbers[i] > largest) {
      largest = numbers[i];
    }
  }
  return largest;
}

/**
 * Determines if a string is a palindrome (reads the same forwards and backwards).
 * Ignore case and spaces.
 *
 * @param text
 * @returns
 */
export function isPalindrome(text: string): boolean {
  const cleaned = text.replace(/[^a-zA-Z0-9]/g, "").toLowerCase();

  return cleaned === cleaned.split("").reverse().join("");

  return false;
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
  birthDay: number,
): number {
  const today = new Date();
  const currentYear = today.getFullYear();

  // Create a date for the birthday this year
  let nextBirthday = new Date(currentYear, birthMonth - 1, birthDay);

  // If the birthday has already passed this year, set it for next year
  if (
    birthMonth < currentMonth ||
    (birthMonth === currentMonth && birthDay < currentDay)
  ) {
    nextBirthday = new Date(currentYear + 1, birthMonth - 1, birthDay);
  }

  const currentDate = new Date(currentYear, currentMonth - 1, currentDay);

  // Calculate the difference in milliseconds and convert to days
  const diffTime = nextBirthday.getTime() - currentDate.getTime();
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

  return diffDays;
}
  
