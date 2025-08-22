const isPrime = require('./isPrime');

describe('isPrime function tests', () => {
    
    test('should return false for numbers less than or equal to 1', () => {
        expect(isPrime(-5)).toBe(false);
        expect(isPrime(0)).toBe(false);
        expect(isPrime(1)).toBe(false);
    });

    test('should return true for prime number 2', () => {
        expect(isPrime(2)).toBe(true);
    });

    test('should return true for small prime numbers', () => {
        expect(isPrime(3)).toBe(true);
        expect(isPrime(5)).toBe(true);
        expect(isPrime(7)).toBe(true);
        expect(isPrime(11)).toBe(true);
        expect(isPrime(13)).toBe(true);
    });

    test('should return false for small composite numbers', () => {
        expect(isPrime(4)).toBe(false);
        expect(isPrime(6)).toBe(false);
        expect(isPrime(8)).toBe(false);
        expect(isPrime(9)).toBe(false);
        expect(isPrime(10)).toBe(false);
        expect(isPrime(12)).toBe(false);
    });

    test('should return true for larger prime numbers', () => {
        expect(isPrime(17)).toBe(true);
        expect(isPrime(19)).toBe(true);
        expect(isPrime(23)).toBe(true);
        expect(isPrime(29)).toBe(true);
        expect(isPrime(97)).toBe(true);
    });

    test('should return false for larger composite numbers', () => {
        expect(isPrime(15)).toBe(false);
        expect(isPrime(21)).toBe(false);
        expect(isPrime(25)).toBe(false);
        expect(isPrime(49)).toBe(false);
        expect(isPrime(100)).toBe(false);
    });

    test('should handle edge cases correctly', () => {
        expect(isPrime(2)).toBe(true);   // Smallest prime
        expect(isPrime(4)).toBe(false);  // Smallest composite > 1
    });

    test('should work with larger numbers', () => {
        expect(isPrime(101)).toBe(true);   // Prime
        expect(isPrime(121)).toBe(false);  // 11^2
        expect(isPrime(997)).toBe(true);   // Large prime
        expect(isPrime(1000)).toBe(false); // Large composite
    });
});
