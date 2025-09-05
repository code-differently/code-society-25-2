export function findPermutationDifference(s: string, t: string): number {
  const idx = new Map<string, number>();
  for (let i = 0; i < s.length; i++) idx.set(s[i], i);
  let sum = 0;
  for (let i = 0; i < t.length; i++) sum += Math.abs(i - (idx.get(t[i])!));
  return sum;
}
