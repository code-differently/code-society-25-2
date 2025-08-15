function isPrime(n) {
    if (n < 2) {
        return false;
    }

    for (let divisor = 2; divisor <= 9; divisor++) {
        if (divisor === n) continue;
        if (n % divisor === 0) {
            return false; // divisible by one of these, so composite
        }
    }

    return true; // no divisors found, prime
}

// Test examples
for (let i = 2; i < 10000; i++) {

 console.log(i + " is prime? " + isPrime(i));
}
