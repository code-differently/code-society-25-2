/**
 * Provide the solution to LeetCode 1929 here:
 * https://leetcode.com/problems/concatenation-of-array
 */
export function getConcatenation(nums: number[]): number[] {
  const result = [];

  for (let i = 0; i < nums.length; i++) {
    result[i] = nums[i];
    result[i + nums.length] = nums[i];
  }

  return result;
}

/**
 * Provide the solution to LeetCode 2942 here:
 * https://leetcode.com/problems/find-words-containing-character/
 */
export function findWordsContaining(words: string[], x: string): number[] {
  const result = [];
  for (let i = 0; i < words.length; i++) {
    if (words[i].indexOf(x) != -1) {
      result.push(i);
    }
  }
  return result;
}
