export function isEven(num: number): boolean {
  return num % 2 === 0;
}

export function maxOfTwo(a: number, b: number): number {
  return a > b ? a : b;
}

export function sumArray(nums: number[]): number {
  let sum = 0;
  for (const n of nums) sum += n;
  return sum;
}

export function addNumbers(nums: number[] = []): number {
  return nums.reduce((sum, n) => sum + n, 0);
}

export function canVote(age: number): boolean {
  return age >= 18;
}

export function computeFactorial(n: number): number {
  if (n < 0) throw new Error("Negative numbers not allowed");
  if (n === 0 || n === 1) return 1;
  let result = 1;
  for (let i = 2; i <= n; i++) result *= i;
  return result;
}
