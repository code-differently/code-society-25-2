import { computeLexicographicDistance } from "./util.js";

/**
 * Compares two strings lexicographically.
 *
 * @param a The first `string` to compare.
 * @param b The second `string` to compare.
 * @return -1 if a is less than b, 1 if a is greater than b, and 0 otherwise.
 */
export function compareStrings(a: string, b: string): number {
  // The distance will be a number less than 0 if string `a` is lexicographically less than `b`, 1
  // if it is greater, and 0 if the strings are equal.
  const distance = computeLexicographicDistance(a, b);

  // TODO(you): Finish this method.
  if (distance < 0) {
    return -1;
  } else if (distance > 0) {
    return 1;
  } else {
  return 0;
  }
}

/**
 * Computes the factorial of the given value of `n`.
 *
 * @param n The value for which to compute the factorial.
 * @return The factorial of n.
 */
export function computeFactorial(n: number): number {
if (n === 0) {
  return 1; // The factorial of 0 is 1.
}

  if (n < 0) {
  return 0; // Factorial is not defined for negative numbers.
}

  let result = 1;
  for (let i = 1; i <= n; i++) {
    result *= i; // Multiply result by i for each i from 1 to n.
  }
  return result;
}

/**
 * Returns an array of the first `n` Fibonacci numbers starting from 1.
 *
 * @param n The first `n` of Fibonacci values to compute.
 * @return An array containing the first `n` Fibonacci values.
 */
export function getFirstNFibonacciNumbers(n: number): number[] {

  
  if (n === 0) {
    return []; // If n is 0, return an empty array.
  }

const fibonacciNumbers: number[] = [1]; // Initialize the array with the first Fibonacci number.


   if (n === 1) {
     return fibonacciNumbers; // If n is 1, return the array with the first Fibonacci number.
   }
   
fibonacciNumbers.push(1); // Add the second Fibonacci number (1) to the array.


  for (let i = 2; i < n; i++) {
    const nextFibonacciNumber =
      fibonacciNumbers[fibonacciNumbers.length - 1] + fibonacciNumbers[fibonacciNumbers.length - 2]; // The next Fibonacci number is the sum of the last two.
    fibonacciNumbers.push(nextFibonacciNumber); // Add the next Fibonacci number
  } 
  return fibonacciNumbers; // Return the array of Fibonacci numbers.
}

/**
 * Finds a value in an array of values.
 *
 * @param values The values to search.
 * @param start The left most index to search.
 * @param end The right most index to search.
 * @param value The value to look for.
 * @return The index of the value if found in the array and -1 otherwise.
 */
export function binarySearch(
  values: number[],
  start: number,
  end: number,
  value: number,
): number {
  if (end < start) {
    // The range is not valid so just return -1.
    return -1;
  }

  const pivotIndex = Math.floor((start + end) / 2); // The index in the middle of the array.

  // TODO(you): Finish implementing this algorithm
if (values[pivotIndex] === value) {
    // If the value at pivotIndex is equal to the value we are looking for, return pivotIndex.
    return pivotIndex;
  }
  else if (values[pivotIndex] > value) {
    // If the value at pivotIndex is greater than the value we are looking for,
    // search the left half of the array.
    return binarySearch(values, start, pivotIndex - 1, value);
  }
  else {
    // If the value at pivotIndex is less than the value we are looking for,
    // search the right half of the array.
    return binarySearch(values, pivotIndex + 1, end, value);
  }



  // If values[pivotIndex] is equal to value then return `pivotIndex`.
  // Else if values[pivotIndex] is greater than the value, then
  // call `binarySearch(values, start, pivotIndex - 1, value)` and return its value;
  // Else call `binarySearch(values, pivotIndex + 1, end, value)` and return its value.
  
return -1;
}
