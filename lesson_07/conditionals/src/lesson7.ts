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
    return -1

  }
  if (distance > 0) {
    return 1
  }

  return 0
}

/**
 * Computes the factorial of the given value of `n`.
 *
 * @param n The value for which to compute the factorial.
 * @return The factorial of n.
 */
export function computeFactorial(n: number): number {
  // Per test spec: return 0 for negatives
  if (n < 0) return 0;

  // 0! should be 1 (this already works with result = 1 and loop starting at 2)
  let result = 1;
  for (let i = 2; i <= n; i++) {
    result *= i;
  }
  return result;
}




/**
 * Returns an array of the first `n` Fibonacci numbers starting from 1.
 *
 * @param n The number of Fibonacci values to compute.
 * @return An array containing the first `n` Fibonacci values.
 */
export function getFirstNFibonacciNumbers(n: number): number[] {
  if (n <= 0) return [];       // if n is 0 or negative → return empty
  if (n === 1) return [1];     // if n is 1 → just [1]

  const fib: number[] = [1, 1]; // start with the first two numbers: 1, 1

  // build the sequence until we have n numbers
  for (let i = 2; i < n; i++) {
    const next = fib[i - 1] + fib[i - 2]; // sum of the last two
    fib.push(next);                       // add it to the list
  }

  return fib; // return the whole sequence
}


/**
 * Finds a value in an array of values using binary search.
 *
 * @param values The values to search (must be sorted).
 * @param start The left-most index to search.
 * @param end The right-most index to search.
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

  const pivotIndex = Math.floor((start + end) / 2); // middle index

  if (values[pivotIndex] === value) {
    return pivotIndex; // ✅ found it
  } else if (values[pivotIndex] > value) {
    // search left half
    return binarySearch(values, start, pivotIndex - 1, value);
  } else {
    // search right half
    return binarySearch(values, pivotIndex + 1, end, value);
  }
}
