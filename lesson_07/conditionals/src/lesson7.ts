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
  if (distance < 0) {
    return -1;
  } else if (distance > 0) {
    return 1;
  } 
  return 0;

}

/**
 * Computes the factorial of the given value of `n`.
 *
 * @param n The value for which to compute the factorial.
 * @return The factorial of n.
 */
export function computeFactorial(n: number): number {
  if (n < 0) {
    return 0;
  }
  let ans:number = 1;
  for (let i = 1; i <=n; i++) {
    ans*=i
  }
  
  return ans;
}

/**
 * Returns an array of the first `n` Fibonacci numbers starting from 1.
 *
 * @param n The first `n` of Fibonacci values to compute.
 * @return An array containing the first `n` Fibonacci values.
 */
export function getFirstNFibonacciNumbers(n: number): number[] {
  const result = [1,1];

  // intial check to handle edge cases
  if (n <= 0) {
    return [];
  } else if (n === 1) {
    return [1];
  } else if (n === 2) {
    return result;
  }

  // Generate Fibonacci numbers starting from the third number
  for (let i = 2; i < n; i++) {
    result.push(result[i - 1] + result[i - 2]);
  }


  return result;
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
  // If values[pivotIndex] is equal to value then return `pivotIndex`.
  if (values[pivotIndex] === value) {
    return pivotIndex;
    // Else if values[pivotIndex] is greater than the value, then
  } else if (values[pivotIndex] > value) {
    // call `binarySearch(values, start, pivotIndex - 1, value)` and return its value;
    return binarySearch(values, start, pivotIndex - 1, value);
    // Else call `binarySearch(values, pivotIndex + 1, end, value)` and return its value.
  } else {
    return binarySearch(values, pivotIndex + 1, end, value);
  }

  console.log("Value not found in the array.");
  return -1;
}
