






function isLessThanOrEqualToOneTS(n: number): boolean {
    return n <= 1;
}

function isTwoOrThreeTS(n: number): boolean {
    return n === 2 || n === 3;
}

function isDivisibleByTwoOrThreeTS(n: number): boolean {
    return n % 2 === 0 || n % 3 === 0;
}

function hasOtherDivisorsTS(n: number): boolean {
    let i = 5;
    while (i * i <= n) {
        if (n % i === 0 || n % (i + 2) === 0) {
            return true;
        }
        i += 6;
    }
    return false;
}

 export function isPrimeTS(n: number): boolean {
    if (isLessThanOrEqualToOneTS(n)) return false;
    if (isTwoOrThreeTS(n)) return true;
    if (isDivisibleByTwoOrThreeTS(n)) return false;
    return !hasOtherDivisorsTS(n);
}

// Testing before JUnit tests
const testNumbersTS: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 16, 17, 19, 21, 23, 26];

testNumbersTS.forEach((num) => {
    console.log(`${num} is prime? ${isPrimeTS(num)}`);
});

