/**
 * Provide the solution to LeetCode 3146 here:
 * https://leetcode.com/problems/permutation-difference-between-two-strings
 */
export function findPermutationDifference(s: string, t: string): number {
    let sum = 0;
    const indexofCharS = new Map<string, number>();
    for (let i = 0; i < s.length; i++) {
        indexofCharS.set(s.charAt(i), i);
    }
    for (let i = 0; i < t.length; i++) {
        const ch = t.charAt(i);
        sum += Math.abs((indexofCharS.get(ch) ?? 0) - i);
    }
    return sum;
}

