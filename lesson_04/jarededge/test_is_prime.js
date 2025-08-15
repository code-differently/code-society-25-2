const assert = require('assert');
const { isPrime } = require('./is_prime');

function runTests(){
    assert.strictEqual(isPrime(2), true);
    assert.strictEqual(isPrime(3), true);
    assert.strictEqual(isPrime(17), true);
    assert.strictEqual(isPrime(23), true);
    assert.strictEqual(isPrime(1), false);
    assert.strictEqual(isPrime(4), false);
    assert.strictEqual(isPrime(9), false);
    assert.strictEqual(isPrime(16), false);

    console.log("All the test cases passed");


}

runTests();