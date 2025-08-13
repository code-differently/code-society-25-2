## Python Implementation

```python
def is_prime(num):
    "If the Prime Number is greater than 1, print False"
    if num <= 1:
        print(False) "Any number less than or equal to one is not a prime number"
        
    else:
    "if the number is greater than one, then we can continue with code"
        is_prime = True
        """This loop will get us the range, 'i' already starts a 2"
        
            Ex. num=9 ,  9**.5=3 ,  3+1=4, so the range is (2,4) not including 4, so meaning you are just checking 2 & 3 againsnt 9

                9%2=0 
                9%3=0 
        """
        for i in range(2, int(num ** .5) + 1):
            "if the num is divisible by i, its not a prime"
            "Ex. 9%3 == 0, its not prime"
            if num % i == 0:
                return False 
        return True

## Example
print(is_prime(9)) #OUTPUT: False
print(is_prime(19)) #OUTPUT: True
```


## Typescript Implementation
 
```typescript
function isPrime(num:number): boolean{
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

//Example
console.log(isPrime(9)) //OUTPUT: false
console.log(isPrime(19)) //OUTPUT: true
```

## Explanation

The Python implementation uses a function named `is_prime` that takes a single argument `num`. It returns `True` if the number is prime (i.e., when a number is greater than 1 and has no positive divisors other than 1 and itself.), otherwise, it returns `False`.

The TypeScript implementation uses a function named `isPrime` that also takes a single argument `num`. It returns `true` if the number is prime (using the same logic as the Python function) and `false` otherwise.
