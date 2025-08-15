function isPrime(number) {
    if (number < 2) {
        return false;
    }
    if (number === 2) {
        return true;
    }
    if (number % 2 === 0) {
        return false;
    }
    
    for (let i = 3; i <= Math.sqrt(number); i += 2) {
        if (number % i === 0) {
            return false;
        }
    }
    
    return true;
}

console.log("Is 2 prime?", isPrime(2));
console.log("Is 4 prime?", isPrime(4));
console.log("Is 7 prime?", isPrime(7));
console.log("Is 15 prime?", isPrime(15));
console.log("Is 17 prime?", isPrime(17));