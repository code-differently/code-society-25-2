#!/usr/bin/env node

/**
 * Prime Number Checker - JavaScript Implementation
 * Author: Kerry Ferguson
 * Date: August 14, 2025
 * 
 * This module provides functionality to determine whether a given number is prime.
 * A prime number is a natural number greater than 1 that has no positive divisors
 * other than 1 and itself.
 */

/**
 * Determines whether a given number is prime.
 * @param {number} n - The number to check for primality
 * @returns {boolean} True if the number is prime, false otherwise
 */
function isPrime(n) {
    // Handle edge cases
    if (n < 2) {
        return false;
    }
    if (n === 2) {
        return true;
    }
    if (n % 2 === 0) {
        return false;
    }

    // Check odd divisors up to sqrt(n)
    const sqrtN = Math.floor(Math.sqrt(n));
    for (let i = 3; i <= sqrtN; i += 2) {
        if (n % i === 0) {
            return false;
        }
    }

    return true;
}


/**
 * Main function to demonstrate prime number checking functionality.
 */
function main() {
    console.log("Prime Number Checker - JavaScript Implementation");
    console.log("===============================================");

    // Test individual numbers
    const testNumbers = [1, 2, 3, 4, 5, 17, 25, 29, 97, 100];

    console.log("\nTesting individual numbers:");
    testNumbers.forEach(num => {
        const result = isPrime(num);
        console.log(`${num.toString().padStart(3)} -> ${result ? 'Prime' : 'Not Prime'}`);
    });

 

    // Interactive mode
    console.log("\nInteractive mode (press Ctrl+C to exit):");
    console.log("Enter a number to check (or 'exit' to quit):");
    
    const readline = require('readline');
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout,
        prompt: 'Enter a number to check: '
    });

    rl.prompt();

    rl.on('line', (input) => {
        const trimmed = input.trim().toLowerCase();
        
        if (trimmed === 'exit' || trimmed === 'quit') {
            console.log("Goodbye!");
            rl.close();
            return;
        }

        const num = parseInt(trimmed, 10);
        
        if (isNaN(num)) {
            console.log("Please enter a valid integer or 'exit' to quit.");
        } else {
            const result = isPrime(num);
            console.log(`${num} is ${result ? 'prime' : 'not prime'}`);
        }
        
        rl.prompt();
    });

    rl.on('close', () => {
        console.log("\nGoodbye!");
        process.exit(0);
    });

    // Handle Ctrl+C gracefully
    rl.on('SIGINT', () => {
        console.log("\nGoodbye!");
        process.exit(0);
    });
}

// Export functions for testing
if (typeof module !== 'undefined' && module.exports) {
    module.exports = {
        isPrime,
        main
    };
}

// Run main function if this file is executed directly
if (require.main === module) {
    main();
}
