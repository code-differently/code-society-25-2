import isPrime from './isPrime';

describe('isPrime TypeScript function tests', () => {
    
    test('should return false for numbers less than or equal to 1', () => {
        expect(isPrime(-5)).toBe(false);
        expect(isPrime(0)).toBe(false);
        expect(isPrime(1)).toBe(false);
    });

    test('should return true for prime number 2', () => {
        expect(isPrime(2)).toBe(true);
    });

    test('should return true for prime number 3', () => {
        expect(isPrime(3)).toBe(true);
    });

    test('should return true for small prime numbers', () => {
        expect(isPrime(5)).toBe(true);
        expect(isPrime(7)).toBe(true);
        expect(isPrime(11)).toBe(true);
        expect(isPrime(13)).toBe(true);
        expect(isPrime(17)).toBe(true);
        expect(isPrime(19)).toBe(true);
    });

    test('should return false for small composite numbers', () => {
        expect(isPrime(4)).toBe(false);
        expect(isPrime(6)).toBe(false);
        expect(isPrime(8)).toBe(false);
        expect(isPrime(9)).toBe(false);
        expect(isPrime(10)).toBe(false);
        expect(isPrime(12)).toBe(false);
        expect(isPrime(14)).toBe(false);
        expect(isPrime(15)).toBe(false);
        expect(isPrime(16)).toBe(false);
        expect(isPrime(18)).toBe(false);
    });

    test('should return true for larger prime numbers', () => {
        expect(isPrime(23)).toBe(true);
        expect(isPrime(29)).toBe(true);
        expect(isPrime(31)).toBe(true);
        expect(isPrime(37)).toBe(true);
        expect(isPrime(97)).toBe(true);
        expect(isPrime(101)).toBe(true);
    });

    test('should return false for larger composite numbers', () => {
        expect(isPrime(21)).toBe(false);
        expect(isPrime(25)).toBe(false);
        expect(isPrime(49)).toBe(false);
        expect(isPrime(100)).toBe(false);
        expect(isPrime(121)).toBe(false); // 11^2
        expect(isPrime(169)).toBe(false); // 13^2
    });

    test('should handle perfect squares correctly', () => {
        expect(isPrime(4)).toBe(false);   // 2^2
        expect(isPrime(9)).toBe(false);   // 3^2
        expect(isPrime(25)).toBe(false);  // 5^2
        expect(isPrime(49)).toBe(false);  // 7^2
    });

    test('should work efficiently with larger numbers', () => {
        expect(isPrime(997)).toBe(true);    // Large prime
        expect(isPrime(1009)).toBe(true);   // Large prime
        expect(isPrime(1000)).toBe(false);  // Large composite
        expect(isPrime(1001)).toBe(false);  // 7 × 11 × 13
    });

    test('should handle numbers divisible by 2 or 3', () => {
        expect(isPrime(6)).toBe(false);   // divisible by both 2 and 3
        expect(isPrime(12)).toBe(false);  // divisible by both 2 and 3
        expect(isPrime(18)).toBe(false);  // divisible by both 2 and 3
    });
});
