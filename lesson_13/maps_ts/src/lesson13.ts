export function findPermutationDifference(s: string, t: string): number {

  const map = new Map<string, number>();

  for (let i = 0; i < t.length; i++) {
    map.set(t[i], i);
  }

  let sum = 0;

  for (let i = 0; i < s.length; i++) {
    const currentChar = s[i];
    const indexInT = map.get(currentChar);

    if (indexInT === undefined) {
        throw new Error('Invalid');
    } else {
        const diff = Math.abs(i - indexInT);
        sum += diff;
    }
  }
  return sum;
}
