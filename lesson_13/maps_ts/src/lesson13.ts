/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
  const mapS= new Map<string, number>();
  let result = 0;
  for(let i =0;i<s.length;i++) {
    mapS.set(s[i],i);
  }

  for(let i = 0;i<t.length;i++) {
    const value: number|undefined = mapS.get(t[i]);
    if (value !== undefined) {
      result+= Math.abs(i-value);
    }
    
  }

  return result;
}
