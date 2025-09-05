/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
  const sIndexMap: Record<string, number> = {};
  const tIndexMap: Record<string, number> = {};

  for (let i = 0; i < s.length; i++) {
    sIndexMap[s[i]] = i;
  }

  for (let i = 0; i < t.length; i++) {
    tIndexMap[t[i]] = i;
  }

  // Sum of absolute differences between indices
  let total = 0;
  for (const char in sIndexMap) {
    const sIndex = sIndexMap[char];
    const tIndex = tIndexMap[char];
    const diff = sIndex - tIndex;
    total += diff >= 0 ? diff : -diff; 
  }

  return total;
}
