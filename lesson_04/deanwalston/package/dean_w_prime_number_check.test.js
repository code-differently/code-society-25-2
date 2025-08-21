// is Prime Number Check in JavaScript 
const { isPrime } = require('./dean_w_prime_number_check');

describe('Dean_W_PrimeNumberTest', () => {
    test('Prime numbers return true', () => {
        const primes = [2, 3, 5, 7, 11, 97];
        primes.forEach(n => {
            const res = isPrime(n);
            console.log(`${n} -> ${res ? 'prime' : 'not prime'}`);
            expect(res).toBe(true);
        });
    });

    test('Non-prime numbers return false', () => {
        const nonPrimes = [1, 4, 6, 9, 10, 24];
        nonPrimes.forEach(n => {
            const res = isPrime(n);
            console.log(`${n} -> ${res ? 'prime' : 'not prime'}`);
            expect(res).toBe(false);
        });
    });

    test('Negative numbers return false', () => {
        const negatives = [-2, -5];
        negatives.forEach(n => {
            const res = isPrime(n);
            console.log(`${n} -> ${res ? 'prime' : 'not prime'}`);
            expect(res).toBe(false);
        });
    });

    test('Zero returns false', () => {
        const res = isPrime(0);
        console.log(`0 -> ${res ? 'prime' : 'not prime'}`);
        expect(res).toBe(false);
    });
});
