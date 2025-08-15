import { isPrimeTS } from "./is_primeTS";

function assertEqual(actual: boolean, expected: boolean, label: string){
    if (actual !== expected){
        throw new Error (`${label}) Test failed. Expected ${expected}, got ${actual}`);

    }
}

function runTestsTS(){
    assertEqual(isPrimeTS(2), true, "prime");
    assertEqual(isPrimeTS(3), true, "prime");
    assertEqual(isPrimeTS(17), true, "prime");
    assertEqual(isPrimeTS(23), true, "prime");
    assertEqual(isPrimeTS(1), false, "prime");
    assertEqual(isPrimeTS(4), false, "prime");
    assertEqual(isPrimeTS(9), false, "prime");
    assertEqual(isPrimeTS(16), false, "prime");
console.log("All the TS test passed");

}

runTestsTS();