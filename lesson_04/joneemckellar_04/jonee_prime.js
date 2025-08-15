/**
 * Determines if a given number is a prime number.
 *
 * A prime number is a natural number greater than 1 that has no positive divisors
 * other than 1 and itself.
 *
 * @param {number} number The number to check.
 * @returns {boolean} True if the number is prime, False otherwise.
 */
function isPrime(number) {
    // In JavaScript, numbers less than or equal to 1 are not prime
    if (number <= 1) {
        return false;
    }
    // 2 is the only even prime number
    if (number === 2) {
        return true;
    }
    // Other even numbers are not prime (using strict equality for comparison)
    if (number % 2 === 0) {
        return false;
    }

    // Check for divisors from 3 up to the square root of the number,
    // incrementing by 2 to only check odd numbers.
    // Math.sqrt() is used for efficiency.
    for (let i = 3; i <= Math.sqrt(number); i += 2) {
        // If a divisor is found, the number is not prime
        if (number % i === 0) {
            return false;
        }
    }
    // If no divisors were found, the number is prime
    return true;
}

// Example usage:
// Use console.log() to print output in JavaScript
console.log(`Is 7 prime? ${isPrime(7)}`);    // Output: true
console.log(`Is 10 prime? ${isPrime(10)}`);  // Output: false
console.log(`Is 2 prime? ${isPrime(2)}`);    // Output: true
console.log(`Is 1 prime? ${isPrime(1)}`);    // Output: false
console.log(`Is 13 prime? ${isPrime(13)}`);  // Output: true
console.log(`Is 49 prime? ${isPrime(49)}`);  // Output: false
