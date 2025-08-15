function checkIfPrime(n) {
    if (n < 2) {
        return false;
    } 
    if (n === 2) {
        return true;
    } 
    if (n % 2 === 0) {
        return false;
    }
    for (let i=3; i <=Math.sqrt(n) + 1; i = i + 2) {
        if (n % i === 0) {
            return false;
        }
    }
    return true;
}
