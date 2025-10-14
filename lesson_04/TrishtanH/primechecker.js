function isPrime(number) {
    // Numbers less than or equal to 1 are not prime
    if (number <= 1) {
        return false;
    }
    
    // Check for divisors up to square root of number
    for (let i = 2; i <= Math.sqrt(number); i++) {
        if (number % i === 0) {
            return false; // Found a divisor, so not prime
        }
    }
    
    return true; // No divisors found, so prime
}

// Test prime numbers
console.log("Is 7 prime?", isPrime(7));   // Output: true
console.log("Is 4 prime?", isPrime(4));   // Output: false
console.log("Is 13 prime?", isPrime(13)); // Output: true
console.log("Is 29 prime?", isPrime(29)); // Output: true