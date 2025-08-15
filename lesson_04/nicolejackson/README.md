## Python implementation
```def is_prime(n):

    if n < 2:
        return False

    for divisor in range(2,n):
        if divisor == n:
            continue
        if n % divisor == 0:
            return False

    return True

for i in range(2,10000):
    print(i, "is prime?", is_prime(i))

```

## JavaScript implementation
```function isPrime(n) {
    if (n < 2) {
        return false;
    }

    for (let divisor = 2; divisor <= 9; divisor++) {
        if (divisor === n) continue;
        if (n % divisor === 0) {
            return false; // divisible by one of these, so composite
        }
    }

    return true; // no divisors found, prime
}

// Test examples
for (let i = 2; i <= 20; i++) {
    console.log(i + " is prime? " + isPrime(i));
}


```

## Explanation

The Python implementation uses a function named is_prime that takes a single argument number. It returns False if the input is less than 2 or when the remainder of the division of the input and a number between the range of 2 and one less than the input is 0. 

The JavaScript implementation uses a function named isPrime that also takes a single argument number.  It returns false if the input is less than 2 or when the remainder of the division of the input and a number between the range of 2 and one less than the input
is 0.


## Differences
 In Python, functions are defined using the def keyword, whereas in JavaScript, the function keyword is used. Python uses True and False for boolean values, while JavaScript uses true and false.

## Similarities
In both languages, the input value is compared to 2. 
Followed by another check to see what if any numbers between 2 and one less than the input are divisible by the input.
 


