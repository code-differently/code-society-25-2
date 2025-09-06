/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
  const pos: number[] = new Array(128);
  let sum = 0;
  for (let i = 0; i < s.length; i++) {
    pos[s.charCodeAt(i)] = i;
  }

  for (let i = 0; i < t.length; i++) {
    sum += Math.abs(pos[t.charCodeAt(i)] - i);
  }

    return sum;
}
