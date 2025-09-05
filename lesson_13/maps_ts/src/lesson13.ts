function findPermutationDifference(s: string, t: string): number {
  // Map characters of t to their index
  const tMap: Record<string, number> = {};
  for (let i = 0; i < t.length; i++) {
    tMap[t[i]] = i;
  }

  let total = 0;

  // Compare indexes from s and t
  for (let i = 0; i < s.length; i++) {
    const current = s[i];
    const indexInT = tMap[current];
    const diff = Math.abs(i - indexInT);
    total += diff;
  }

  return total;
}



