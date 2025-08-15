"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.isPrimeTS = isPrimeTS;
function isLessThanOrEqualToOneTS(n) {
    return n <= 1;
}
function isTwoOrThreeTS(n) {
    return n === 2 || n === 3;
}
function isDivisibleByTwoOrThreeTS(n) {
    return n % 2 === 0 || n % 3 === 0;
}
function hasOtherDivisorsTS(n) {
    var i = 5;
    while (i * i <= n) {
        if (n % i === 0 || n % (i + 2) === 0) {
            return true;
        }
        i += 6;
    }
    return false;
}
function isPrimeTS(n) {
    if (isLessThanOrEqualToOneTS(n))
        return false;
    if (isTwoOrThreeTS(n))
        return true;
    if (isDivisibleByTwoOrThreeTS(n))
        return false;
    return !hasOtherDivisorsTS(n);
}
// Testing before JUnit tests
var testNumbersTS = [1, 2, 3, 4, 5, 6, 7, 8, 9, 16, 17, 19, 21, 23, 26];
testNumbersTS.forEach(function (num) {
    console.log("".concat(num, " is prime? ").concat(isPrimeTS(num)));
});
