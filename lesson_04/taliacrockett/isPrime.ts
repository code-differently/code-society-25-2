/**
 * Determines if a number is prime using optimized algorithm
 * @param number - The number to check
 * @returns True if the number is prime, false otherwise
 */
function isPrime(number: number): boolean {
    if (number <= 1) {
        return false;
    }

    if (number <= 3) {
        return true;
    }
    
    if (number % 2 === 0 || number % 3 === 0) {
        return false;
    }
    
    for (let i = 5; i * i <= number; i += 6) {
        if (number % i === 0 || number % (i + 2) === 0) {
            return false;
        }
    }
    
    return true;
}

// Export for testing
export default isPrime;

// Example usage:
console.log(isPrime(17)); // Output: true
console.log(isPrime(25)); // Output: false
