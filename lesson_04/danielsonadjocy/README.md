## Python implementation

```python
def is_prime(num):
    for val in range(2,int(num**(1/2))+1):
        if num % val == 0:
            return False
    return True
for x in range (1,100):
    print(x, is_prime(x))
```

## Java implementation

```java
public class Prime {

    public static boolean isPrime(int num){
        for (int val = 2; val < Math.sqrt(num)+1; val++){
            if (num % val == 0){
                return false;
            }
            
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(4 + ": " + isPrime(4));
        System.out.println(13 + ": " + isPrime(13));
        
        for (int num = 2; num <=100; num++){
            System.out.println(num+": "+ isPrime(num));
        }
    }
    
}
```

## Explanation
In the Python file `prime.py`, the function `is_prime` takes the parameter called `num` and returns `True` or `False` depending on whether or not num is a prime number (as in numbers that are only divisble by another number that is not 1 or itself). The function runs a loop through all numbers in the range from 2 to the square root of num inclusively, divides num by each of those numbers, and if the remainder is ever 0, then false is returned immediately. If all numbers are checked without getting 0, true is returned after the loop.

<br>

The Python implementation uses a function named `is_even` that takes a single argument `number`. It returns `True` if the number is even (i.e., when the remainder of the division of the number by 2 is zero), otherwise, it returns `False`.

The JavaScript implementation uses a function named `isEven` that also takes a single argument `number`. It returns `true` if the number is even (using the same logic as the Python function) and `false` otherwise.

<br>

In the Java file `Prime.java`, the function `isPrime` also takes a parameter called `num` and has the same goal as the python iteration. The java version returns `true` when given a prime number and `false` when given a non-prime using the same logic as the python version.

### Differences

1. **Syntax**: 
   - In Python, functions are defined using the `def` keyword. Meanwhile in Java the function is defined with `public static <return type>`
   - Python uses `True` and `False` for boolean values, while Java uses `true` and `false`.
   - For loops in python are written with 

2. **Type Coercion**:
   - JavaScript has type coercion, which can sometimes lead to unexpected results if the input is not properly handled. In contrast, Python is more strict with types.
   
3. **Function Calls**:
   - The syntax for calling functions and printing to the console/output is slightly different. Python uses `print()`, while JavaScript uses `console.log()`.
