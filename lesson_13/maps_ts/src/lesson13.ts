/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
  const perm1: Record<string, number> = {};
  const perm2: Record<string, number> = {};

  // build maps
  for (let i = 0; i < s.length; i++) {
    perm1[s[i]] = i;
    perm2[t[i]] = i;
  }

  let count = 0;
  for (const key in perm1) {
    count += Math.abs(perm1[key] - perm2[key]);
  }

  return count;
}
