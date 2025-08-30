/**
 * Provide the solution to LeetCode 1929 here:
 * https://leetcode.com/problems/concatenation-of-array
 */
export function getConcatenation(nums: number[]): number[] {
  if (nums.length === 0) {
    return nums;
  }
  const ans: number[] = [nums.length * 2];
  for (let i = 0; i < nums.length; i++) {
    ans[i] = nums[i];
    ans[i + nums.length] = nums[i];
  }
  return ans;
}

/**
 * Provide the solution to LeetCode 2942 here:
 * https://leetcode.com/problems/find-words-containing-character/
 */
export function findWordsContaining(words: string[], x: string): number[] {
  if (words.length === 0) {
    return [];
  }

  const result: number[] = [];
  for (let i = 0; i < words.length; i++) {
    if (words[i].indexOf(x) != -1) {
      result.push(i);
    }
  }
  return result;
}
