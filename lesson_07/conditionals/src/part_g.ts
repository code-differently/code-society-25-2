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

}
  
/**
 * Determines if a string is a palindrome (reads the same forwards and backwards).
 * Ignore case and spaces.
 * 
 * @param text
 * @returns
 */

export function isPalindrome(text: string): boolean {
  const lower = text.toLowerCase();
  let left = 0;
  let right = lower.length - 1;

  while (left < right) {
    // Skip spaces on the left
    if (lower[left] === " ") {
      left++;
      continue;
    }
    // Skip spaces on the right
    if (lower[right] === " ") {
      right--;
      continue;
    }
    // Compare characters
    if (lower[left] !== lower[right]) {
      return false;
    }
    left++;
    right--;
  }

  return true;
}

export function daysUntilBirthday(
  currentMonth: number,
  currentDay: number,
  birthMonth: number,
  birthDay: number

): number {
  // Step 1: Get today's year
  const todayYear = new Date().getFullYear();

  // Step 2: Create Date objects for "today" and "next birthday"
  const today = new Date(todayYear, currentMonth - 1, currentDay);
  let nextBirthday = new Date(todayYear, birthMonth - 1, birthDay);

  // Step 3: If birthday already passed this year, use next year
  if (nextBirthday < today) {
    nextBirthday = new Date(todayYear + 1, birthMonth - 1, birthDay);
  }

  // Step 4: Calculate difference in milliseconds, convert to days
  const diffMs = nextBirthday.getTime() - today.getTime();
  const diffDays = Math.ceil(diffMs / (1000 * 60 * 60 * 24));

  return diffDays;
}

