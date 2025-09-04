/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
let sum = 0;
    for(let i=0; i<=s.length;i++){
        sum += Math.abs(s.indexOf(s[i])-t.indexOf(s[i]));  
    }
  return sum;
}
