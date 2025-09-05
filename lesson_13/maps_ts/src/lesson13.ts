/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
  // Map each character to its index in s and t
  const sIndex = new Map<string, number>();
  const tIndex = new Map<string, number>();
  
  for (let i = 0; i < s.length; i++) {
    sIndex.set(s[i], i);
    tIndex.set(t[i], i);
  }
  
  let diff = 0;
  for (let i = 0; i < s.length; i++) {
    const c = s[i];
    diff += Math.abs(sIndex.get(c)! - tIndex.get(c)!);
  }
  
  return diff;
}
