/**
 * Determines if a number is prime
 * @param {number} number - The number to check
 * @returns {boolean} - True if the number is prime, false otherwise
 */
function isPrime(number) {
    if (number <= 1) return false;
    if (number === 2) return true;
    if (number % 2 === 0) return false;

    for (let i = 3; i <= Math.sqrt(number); i += 2) {
        if (number % i === 0) return false;
    }
    return true; 
}

// Export for testing (Node.js style)
if (typeof module !== 'undefined' && module.exports) {
    module.exports = isPrime;
}

// Example usage:
console.log(isPrime(17)); // Output: true
console.log(isPrime(25)); // Output: false
