// Import the isPrime function (assuming it's exported)
// For this simple test, we'll include the function directly

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

// Simple test framework
function test(description, testFunction) {
    try {
        testFunction();
        console.log(`✅ PASS: ${description}`);
    } catch (error) {
        console.log(`❌ FAIL: ${description} - ${error.message}`);
    }
}

function assertEqual(actual, expected, message) {
    if (actual !== expected) {
        throw new Error(message || `Expected ${expected}, but got ${actual}`);
    }
}

// Unit Tests
console.log('Running JavaScript Prime Number Tests...\n');

test('should return false for numbers less than 2', () => {
    assertEqual(isPrime(0), false, '0 should not be prime');
    assertEqual(isPrime(1), false, '1 should not be prime');
    assertEqual(isPrime(-5), false, 'negative numbers should not be prime');
});

test('should return true for small prime numbers', () => {
    assertEqual(isPrime(2), true, '2 should be prime');
    assertEqual(isPrime(3), true, '3 should be prime');
    assertEqual(isPrime(5), true, '5 should be prime');
    assertEqual(isPrime(7), true, '7 should be prime');
});

test('should return false for small composite numbers', () => {
    assertEqual(isPrime(4), false, '4 should not be prime');
    assertEqual(isPrime(6), false, '6 should not be prime');
    assertEqual(isPrime(8), false, '8 should not be prime');
    assertEqual(isPrime(9), false, '9 should not be prime');
    assertEqual(isPrime(10), false, '10 should not be prime');
});

test('should return true for larger prime numbers', () => {
    assertEqual(isPrime(11), true, '11 should be prime');
    assertEqual(isPrime(13), true, '13 should be prime');
    assertEqual(isPrime(17), true, '17 should be prime');
    assertEqual(isPrime(19), true, '19 should be prime');
    assertEqual(isPrime(23), true, '23 should be prime');
    assertEqual(isPrime(29), true, '29 should be prime');
});

test('should return false for larger composite numbers', () => {
    assertEqual(isPrime(12), false, '12 should not be prime');
    assertEqual(isPrime(15), false, '15 should not be prime');
    assertEqual(isPrime(21), false, '21 should not be prime');
    assertEqual(isPrime(25), false, '25 should not be prime');
    assertEqual(isPrime(30), false, '30 should not be prime');
});

test('should handle perfect squares correctly', () => {
    assertEqual(isPrime(16), false, '16 (4²) should not be prime');
    assertEqual(isPrime(25), false, '25 (5²) should not be prime');
    assertEqual(isPrime(49), false, '49 (7²) should not be prime');
});

console.log('\nJavaScript tests completed!');
