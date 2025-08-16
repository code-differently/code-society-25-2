function isprime(num) {
    if (num <= 1) return false;
    for (let i = 2; i < num; i++) {
        if (num % i === 0) return false;
    }
    return true;
}

// Unit Test Function
function testIsPrime() {
    const testCases = [
        { input: 2, expected: true, description: "2 is prime" },
        { input: 3, expected: true, description: "3 is prime" },
        { input: 4, expected: false, description: "4 is not prime" },
        { input: 5, expected: true, description: "5 is prime" },
        { input: 11, expected: true, description: "11 is prime" },
        { input: 15, expected: false, description: "15 is not prime" },
        { input: 17, expected: true, description: "17 is prime" },
        { input: 1, expected: false, description: "1 is not prime" },
        { input: 0, expected: false, description: "0 is not prime" },
        { input: -5, expected: false, description: "negative numbers are not prime" }
    ];

    let passed = 0;
    let failed = 0;

    console.log("Running Unit Tests for isprime()...\n");

    testCases.forEach(test => {
        const result = isprime(test.input);
        if (result === test.expected) {
            console.log(`✓ PASS: ${test.description} - isprime(${test.input}) = ${result}`);
            passed++;
        } else {
            console.log(`✗ FAIL: ${test.description} - isprime(${test.input}) = ${result}, expected ${test.expected}`);
            failed++;
        }
    });

    console.log(`\nTest Results: ${passed} passed, ${failed} failed\n`);
}

// Run the unit test
testIsPrime();


