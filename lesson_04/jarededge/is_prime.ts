function isPrimeTS(n: number): boolean{
    if (n <= 1) return false;
    if (n<= 3 ) return true;
    if (n % 2 === 0 || n % 3 === 0) return false;

    let i = 5;
    while (i * i <=n) {
        if (n % i === 0 || n % (i + 2) === 0){
            return true;

        }
        i += 6;
    }
    return false;
}

//Testing the program before JUNIT tests

const testNumbersTS: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 16, 17, 19, 21, 23, 26];

testNumbersTS.forEach((num) => {
    console.log(`${num} is prime? ${isPrime(num)}`);

});