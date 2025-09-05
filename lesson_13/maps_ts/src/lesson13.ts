/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
  // Create a map to store the index of each character in string s
  const indexByChar = new Map<string, number>();

  // Store the index of each character in string s
  for (let i = 0; i < s.length; i++) {
    indexByChar.set(s[i], i);
  }

  let permutationDifference = 0;

  // For each character in string t, find its index in string s
  // and calculate the absolute difference
  for (let i = 0; i < t.length; i++) {
    const currentChar = t[i];
    const currentIndex = indexByChar.get(currentChar);
    if (currentIndex !== undefined) {
      permutationDifference += Math.abs(i - currentIndex);
    }
  }

  return permutationDifference;
}
