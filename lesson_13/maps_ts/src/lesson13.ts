/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
  const permutationArray: number[] = [128]; 
        for (let i = 0; i < s.length; i++) {
            permutationArray[s.charCodeAt(i)] = i;
        }
        let sum = 0;
        for (let i = 0; i < t.length; i++) {
            sum += Math.abs(permutationArray[t.charCodeAt(i)] - i);  
        }
            return sum;
}
