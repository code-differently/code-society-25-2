/**
 * Provide the solution to LeetCode 1929 here:
 * https://leetcode.com/problems/concatenation-of-array
 */
export function getConcatenation(nums: number[]): number[] {
  const ans : number[] = nums;
  ans.push(...nums);
  return ans;
}

/**
 * Provide the solution to LeetCode 2942 here:
 * https://leetcode.com/problems/find-words-containing-character/
 */
export function findWordsContaining(words: string[], x: string): number[] {
  const indexes: number[] = [];
  for (let i = 0; i < words.length; i++) {
      if (words[i].indexOf(x) >= 0) {
          indexes.push(i);
      }
  }
  return indexes;
}
