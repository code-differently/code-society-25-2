function isPrime(n) {
    if ( n <= 1) return false;
    if(n<=3) return true;
    if(n % 2 === 0 || n % 3 === 0) return false;

    let i = 5;
    while(i * i <= n) {
        if (n % i === 0 || n % (i + 2) === 0) {
            return false;

        
    }
i += 6;
}
return true;
}

//Test the program before JUnit Tests
const testNumbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 16, 17, 19, 21, 23, 26];
testNumbers.forEach((num) => {
    console.log(`${num} is prime? ${isPrime(num)}`);

});