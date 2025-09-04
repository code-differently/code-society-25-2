/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
  // Create a map to store character positions in string s
  const sPositions = new Map<string, number>();

  // Store the position of each character in string s
  for (let i = 0; i < s.length; i++) {
    sPositions.set(s[i], i);
  }

  let totalDifference = 0;

  // For each character in string t, find its position difference
  for (let i = 0; i < t.length; i++) {
    const currentChar = t[i];
    const positionInS = sPositions.get(currentChar) ?? 0; // Default to 0 if not found (though it should always be found)
    const positionInT = i;

    // Add the absolute difference to the total
    totalDifference += Math.abs(positionInS - positionInT);
  }

  return totalDifference;
}
