export function isPrime(n: number): boolean {
    return n > 1 && Array.from({ length: Math.floor(Math.sqrt(n)) - 1 }, (_, i) => i + 2)
     .every(i => n % i !== 0);
}