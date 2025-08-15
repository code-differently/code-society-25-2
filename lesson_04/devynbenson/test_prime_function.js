function primeFunction(n) {
    if (!Number.isInteger(n) || n <= 1) 
        return false;
   
    if (n <= 3) 
        return n >= 2;              
    
    if (n % 2 === 0 || n % 3 === 0) 
        return false;

    // Check for divisors from 5 to √n
    for (let i = 5; i * i <= n; i += 6) {
        if (n % i === 0 || n % (i + 2) === 0) {
            return false;
        }
    }
    
    return true;
}


// Simple test framework
function test(description, testFunction) {
    try {
        testFunction();
        console.log(`PASS: ${description}`);
    } catch (error) {
        console.log(`FAIL: ${description}`);
        console.log(`ERROR: ${error.message}`);
    }
}

function assertEqual(actual, expected, message = "") {
    if (actual !== expected) {
        throw new Error(`Expected ${expected}, but got ${actual}. ${message}`);
    }
}

// Test Suite
console.log("Running JavaScript Prime Function Tests...\n");

// Test edge cases
test("Edge case: 1 (only one positive divisor)", () => {
    assertEqual(primeFunction(1), false);
});

test("Edge case: 0", () => {
    assertEqual(primeFunction(0), false);
});

test("Edge case: negative numbers", () => {
    assertEqual(primeFunction(-1), false);
    assertEqual(primeFunction(-5), false);
});

test("Edge case: non-integer input", () => {
    assertEqual(primeFunction(2.5), false);
    assertEqual(primeFunction(3.14), false);
});

// Test small primes
test("Small prime: 2 (only even prime)", () => {
    assertEqual(primeFunction(2), true);
});

test("Small primes: 3, 5, 7, 11, 13", () => {
    assertEqual(primeFunction(3), true);
    assertEqual(primeFunction(5), true);
    assertEqual(primeFunction(7), true);
    assertEqual(primeFunction(11), true);
    assertEqual(primeFunction(13), true);
});

// Test composite numbers
test("Composite: 4 (4 = 2×2)", () => {
    assertEqual(primeFunction(4), false);
});

test("Composite: 6 (6 = 2×3)", () => {
    assertEqual(primeFunction(6), false);
});

test("Composite: 9 (9 = 3×3)", () => {
    assertEqual(primeFunction(9), false);
});

test("Composite numbers: 8, 10, 12, 15", () => {
    assertEqual(primeFunction(8), false);
    assertEqual(primeFunction(10), false);
    assertEqual(primeFunction(12), false);
    assertEqual(primeFunction(15), false);
});

// Test larger primes
test("Larger primes: 17, 19, 23, 29, 31, 97", () => {
    assertEqual(primeFunction(17), true);
    assertEqual(primeFunction(19), true);
    assertEqual(primeFunction(23), true);
    assertEqual(primeFunction(29), true);
    assertEqual(primeFunction(31), true);
    assertEqual(primeFunction(97), true);
});

// Test larger composites
test("Larger composites: 21, 25, 49, 77, 91", () => {
    assertEqual(primeFunction(21), false); // 3×7
    assertEqual(primeFunction(25), false); // 5×5
    assertEqual(primeFunction(49), false); // 7×7
    assertEqual(primeFunction(77), false); // 7×11
    assertEqual(primeFunction(91), false); // 7×13
});

console.log("\nAll tests completed!");

// Export for potential use with other test frameworks
if (typeof module !== 'undefined' && module.exports) {
    module.exports = primeFunction;
}
