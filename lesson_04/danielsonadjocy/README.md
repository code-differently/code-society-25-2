## Python implementation

```python
def is_prime(num):
    if num <= 1:
        return False
    for val in range(2,int(num**(1/2))+1):
        if num % val == 0:
            return False
    return True

if __name__ == "__main__":
    print(4, is_prime(4))
    print(13, is_prime(13))
    for num in range(2, 101):
        print(str(num) +": "+ str(is_prime(num)))
```

## Java implementation

```java
public class Prime {
  public static void main(String[] args) {
    System.out.println(4 + " " + isPrime(4));
    System.out.println(13 + " " + isPrime(13));

    // Checks each number between 2 and 100 to see if they're prime.
    for (int num = 2; num <= 100; num++) {
      System.out.println(num + ": " + isPrime(num));
    }
  }

  /**
   * Checks if num is a prime number.
   *
   * @param num the number to check
   * @return true if num is prime, false otherwise
   */
  public static boolean isPrime(int num) {
    if (num <= 1){
        return false;
    }
    for (int val = 2; val <= Math.sqrt(num); val++) {
      if (num % val == 0) {
        return false;
      }
    }
    return true;
  }
}

```

## Explanation
In the Python file `prime.py`, the function `is_prime` takes the parameter called `num` and returns `True` or `False` depending on whether or not num is a prime number (as in numbers that are only divisble by another number that is not 1 or itself). The function runs a loop through all numbers in the range from 2 to the square root of num inclusively, divides num by each of those numbers, and if the remainder is ever 0, then false is returned immediately. If all numbers are checked without getting 0, true is returned after the loop.

In the Java file `Prime.java`, the function `isPrime` also takes a parameter called `num` and has the same goal as the python iteration. The java version returns `true` when given a prime number and `false` when given a non-prime using the same logic as the python version.

### Differences

1. **Syntax**: 
   - In Python, functions are defined using the `def` keyword. Meanwhile in Java the function is defined with `public static <return type>`
   - Python uses `True` and `False` for boolean values, while Java uses `true` and `false`.
   - For loop in python are written `for x in range(2, num**(1/2)+1).` Since the end point is exclusive, we need to add one to make sure that the square root of the num is included.
   - The java for loop is written like `for (int x = 2; x<=Math.sqrt(num); x++)`. This creates x with a value of 2, loops until it's greater than the root of num and adds one to x for the next iteration.

2. **Type Coercion**:
   - JavaScript has type coercion, which can sometimes lead to unexpected results if the input is not properly handled. In contrast, Python is more strict with types.
   
3. **Function Calls**:
   - Most function calls between Python and Java are done the same. However for printing to the console, Python uses `print()` and Java uses `System.out.println()`.

## Test Cases

Both the Python and Java implementations include automated tests to verify that the `is_prime` function works correctly.

- **Python (`prime_test.py`)**:  
  The test file uses Python’s `unittest` framework. It checks if the function returns `True` for prime numbers and `False` for non-prime numbers.  
  ```python
  self.assertTrue(is_prime(13))  # Checks that 13 is correctly identified as a prime number.
  ```

- **Java (`isPrimeTest.java`)**:  
  The test file uses JUnit. It also checks that the function returns the correct answers.
  ```java
  assertTrue(Prime.isPrime(13)); // Checks that 13 is correctly identified as a prime number.
  ```

### How to run the tests

- **Python:**  
  From the `danielsonadjocy` directory, run:  
  ```bash
  python -m unittest app.src.test.python.prime_test
  ```

- **Java:**  
  Make sure JUnit is available. From the `danielsonadjocy` directory, compile and run:  
  ```bash
  gradle test
  ```