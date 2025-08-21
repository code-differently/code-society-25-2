"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var is_primeTS_1 = require("./is_primeTS");
function assertEqual(actual, expected, label) {
    if (actual !== expected) {
        throw new Error("".concat(label, ") Test failed. Expected ").concat(expected, ", got ").concat(actual));
    }
}
function runTestsTS() {
    assertEqual((0, is_primeTS_1.isPrimeTS)(2), true, "prime");
    assertEqual((0, is_primeTS_1.isPrimeTS)(3), true, "prime");
    assertEqual((0, is_primeTS_1.isPrimeTS)(17), true, "prime");
    assertEqual((0, is_primeTS_1.isPrimeTS)(23), true, "prime");
    assertEqual((0, is_primeTS_1.isPrimeTS)(1), false, "prime");
    assertEqual((0, is_primeTS_1.isPrimeTS)(4), false, "prime");
    assertEqual((0, is_primeTS_1.isPrimeTS)(9), false, "prime");
    assertEqual((0, is_primeTS_1.isPrimeTS)(16), false, "prime");
    console.log("All the TS test passed");
}
runTestsTS();
