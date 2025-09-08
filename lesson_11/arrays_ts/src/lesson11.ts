/**
 * Provide the solution to LeetCode 1929 here:
 * https://leetcode.com/problems/concatenation-of-array
 */
export function getConcatenation(nums: number[]): number[] {
  const n = nums.length;
  const myNewArr = new Array(n * 2);

  for (let i = 0; i < n; i++) {
    myNewArr[i] = nums[i];
    myNewArr[i + n] = nums[i]; // second copy offset by n
  }
  return myNewArr;
}

/**
 * Provide the solution to LeetCode 2942 here:
 * https://leetcode.com/problems/find-words-containing-character/
 */
export function findWordsContaining(words: string[], x: string): number[] {
  const myArray: number[] = [];

  for (let i = 0; i < words.length; i++) {
    if (words[i].indexOf(x) >= 0) {
      myArray.push(i);
    }
  }
  return myArray;
}
