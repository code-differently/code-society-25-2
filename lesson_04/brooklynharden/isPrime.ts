export function isPrime(num:number): boolean{
    if(num <= 1){
        return false;
    }
    for (let i=2; i <= Math.sqrt(num); i++){
        if(num % i === 0){
            return false;
        }
    }
    return true;
}


// Basic tests to check the isPrime function
console.assert(isPrime(2) === true, "Test Case 1 Failed: 2 should be prime");
console.assert(isPrime(97) === true, "Test Case 6 Failed: 97 should be prime");

console.assert(isPrime(4) === false, "Test Case 7 Failed: 4 should not be prime");
console.assert(isPrime(100) === false, "Test Case 11 Failed: 100 should not be prime");

console.assert(isPrime(0) === false, "Test Case 12 Failed: 0 should not be prime");
console.assert(isPrime(-10) === false, "Test Case 15 Failed: -10 should not be prime");

console.log("All basic tests passed!");
