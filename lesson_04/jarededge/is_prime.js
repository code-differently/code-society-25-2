function isLessThanOrEqualToOne(n) {
    return n <= 1;
}

function isTwoOrThree(n) {
    return n === 2 || n === 3;
}

function isDivisibleByTwoOrThree(n) {
    return n % 2 === 0 || n % 3 === 0;
}

function hasOtherDivisors(n) {
    let i = 5;
    while (i * i <= n) {
        if (n % i === 0 || n % (i + 2) === 0) {
            return true;
        }
        i += 6;
    }
    return false;
}

function isPrime(n) {
    if (isLessThanOrEqualToOne(n)) return false;
    if (isTwoOrThree(n)) return true;
    if (isDivisibleByTwoOrThree(n)) return false;
    return !hasOtherDivisors(n);
}

module.exports = {isPrime};

// Testing before JUnit tests
const testNumbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 16, 17, 19, 21, 23, 26];
testNumbers.forEach((num) => {
    console.log(`${num} is prime? ${isPrime(num)}`);
});
