#!/usr/bin/env node
/**
 * Simple test runner for prime_checker.js
 * Tests the JavaScript implementation without external dependencies
 */

// Import the functions directly from the module
const { isPrime } = require('./prime_checker.js');

// Simple test assertion function
function assert(condition, message) {
    if (!condition) {
        console.error(`❌ FAIL: ${message}`);
        return false;
    } else {
        console.log(`✅ PASS: ${message}`);
        return true;
    }
}

function arrayEquals(a, b) {
    return Array.isArray(a) && Array.isArray(b) && 
           a.length === b.length && 
           a.every((val, index) => val === b[index]);
}

// Test suite
let passed = 0;
let total = 0;

function test(description, testFn) {
    console.log(`\n📋 Testing: ${description}`);
    total++;
    try {
        const result = testFn();
        if (result !== false) {
            passed++;
        }
    } catch (error) {
        console.error(`❌ ERROR: ${error.message}`);
    }
}

// Run tests
console.log("🧪 Running JavaScript Prime Checker Tests");
console.log("=".repeat(45));

test("Numbers less than 2 should not be prime", () => {
    return assert(isPrime(-5) === false, "-5 should not be prime") &&
           assert(isPrime(-1) === false, "-1 should not be prime") &&
           assert(isPrime(0) === false, "0 should not be prime") &&
           assert(isPrime(1) === false, "1 should not be prime");
});

test("Number 2 should be prime", () => {
    return assert(isPrime(2) === true, "2 should be prime");
});

test("Even numbers greater than 2 should not be prime", () => {
    const evenNumbers = [4, 6, 8, 100, 1000];
    return evenNumbers.every(num => {
        const result = isPrime(num) === false;
        assert(result, `${num} should not be prime`);
        return result;
    });
});

test("Small known primes should be identified correctly", () => {
    const smallPrimes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29];
    return smallPrimes.every(prime => {
        const result = isPrime(prime) === true;
        assert(result, `${prime} should be prime`);
        return result;
    });
});

test("Small known composites should be identified correctly", () => {
    const composites = [4, 6, 8, 9, 10, 12, 15, 16, 18, 20, 25];
    return composites.every(composite => {
        const result = isPrime(composite) === false;
        assert(result, `${composite} should not be prime`);
        return result;
    });
});


test("Larger primes should be handled correctly", () => {
    const largerPrimes = [97, 101, 103, 107, 109];
    return largerPrimes.every(prime => {
        const result = isPrime(prime) === true;
        assert(result, `${prime} should be prime`);
        return result;
    });
});

test("Performance test with larger numbers", () => {
    const start = performance.now();
    const result1 = isPrime(997);
    const result2 = isPrime(1009);
    const end = performance.now();
    
    const timeElapsed = end - start;
    return assert(result1 === true, "997 should be prime") &&
           assert(result2 === true, "1009 should be prime") &&
           assert(timeElapsed < 100, `Should complete quickly (${timeElapsed.toFixed(2)}ms)`);
});

// Print summary
console.log("\n" + "=".repeat(45));
console.log(`📊 Test Results: ${passed}/${total} tests passed`);
console.log(`Success rate: ${((passed/total) * 100).toFixed(1)}%`);

if (passed === total) {
    console.log("🎉 All tests passed!");
    process.exit(0);
} else {
    console.log("💥 Some tests failed!");
    process.exit(1);
}
