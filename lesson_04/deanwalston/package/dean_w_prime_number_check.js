// Here is the JavaScript code that checks if a number is prime:

function isPrime(number) {
    if (number <= 1) return false;
    for (let i = 2; i <= Math.sqrt(number); i++) {
        if (number % i === 0) return false;
    }
    return true;
}

// Run main code only if executed directly
if (require.main === module) {
    console.log(17, isPrime(17) ? "is prime" : "is not prime");
    console.log(24, isPrime(24) ? "is prime" : "is not prime");
}

module.exports = { isPrime };
