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
 * Returns an array of the first `n` Fibonacci numbers starting from 1.
 *
 * @param n The first `n` of Fibonacci values to compute.
 * @return An array containing the first `n` Fibonacci values.
 */
export function getFirstNFibonacciNumbers(n: number): number[] {
  if (n <= 0) return [];   // if n is 0 or negative → return empty
  if (n === 1) return [1]; // if n is 1 → just [1]

  const fib: number[] = [1, 1]; // start with 1, 1

  // keep making new numbers until we reach n
  for (let i = 2; i < n; i++) {
    const next = fib[i - 1] + fib[i - 2]; // add the last two numbers
    fib.push(next); // put the new number in the list
  }

  return fib; // give back the whole list
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

