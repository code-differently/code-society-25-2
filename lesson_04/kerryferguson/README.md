# Assignment Summary - Lesson 04: Prime Number Checker

## Overview
This assignment implements a prime number checker in two different programming languages: Python and JavaScript. Both implementations provide the same core functionality but demonstrate the unique characteristics and syntax of each language.

```
Python
def is_prime(n):
    """
    Determines whether a given number is prime.

    Args:
        n (int): The number to check for primality

    Returns:
        bool: True if the number is prime, False otherwise
    """
    # Handle edge cases
    if n < 2:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False

    # Check odd divisors up to sqrt(n)
    sqrt_n = int(math.sqrt(n))
    for i in range(3, sqrt_n + 1, 2):
        if n % i == 0:
            return False

    return True
```

```
JavaScript
function isPrime(n) {
    // Handle edge cases
    if (n < 2) {
        return false;
    }
    if (n === 2) {
        return true;
    }
    if (n % 2 === 0) {
        return false;
    }

    // Check odd divisors up to sqrt(n)
    const sqrtN = Math.floor(Math.sqrt(n));
    for (let i = 3; i <= sqrtN; i += 2) {
        if (n % i === 0) {
            return false;
        }
    }

    return true;
}
```



## Program Comparison

### Similarities

Both implementations share the following characteristics:

   - Check divisibility only up to the square root of the number
   - Handle edge cases (numbers < 2, the number 2, even numbers)
   - Test only odd divisors for efficiency

   - Both have a main function that checks if a number is prime
   - Both include a demonstration mode with predefined test numbers
   - Both include error handling for invalid input


### Differences

The implementations differ in several language-specific ways:


**Python:**
- Uses snake_case naming convention (`is_prime`, `test_numbers`)
- Indentation-based code blocks
- List comprehension and iteration with `for num in test_numbers`

**JavaScript:**
- Uses camelCase naming convention (`isPrime`, `testNumbers`)
- Brace-delimited code blocks
- Array methods like `forEach()`

### Testing Approach

**Python:**
- Comprehensive unit test suite (`test_prime_checker.py`)
- Uses `unittest` framework
- Includes performance tests and edge case validation
- Mocks user input for automated testing

**JavaScript:**
- Basic test runner (`test_runner.js`)
- Custom testing implementation
- Focused on core functionality verification