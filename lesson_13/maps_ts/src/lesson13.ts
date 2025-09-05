/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
  let total = 0;
  const mapS = new Map<string, number>();
  for (let i = 0; i < s.length; i++) {
    mapS.set(s.charAt(i), i);
  }

  for (let i = 0; i < t.length; i++) {
    const curChar = t.charAt(i);
    const curIndex = mapS.get(curChar);
    if (curIndex === undefined) {
      throw new Error('Invalid');
    } else {
      const diff = Math.abs(curIndex - i);
      total += diff;
    }
  }
  return total;
}
