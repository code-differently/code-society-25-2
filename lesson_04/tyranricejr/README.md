# IsPrimeNumber Functionality Between Python & Java

This markdown file compares the implementation of a function that checks if a number is prime in both Python and Java. It includes code snippets for each language, test cases to verify correctness, and an explanation of the logic used. The document also highlights key differences between the two languages in terms of syntax, type handling, and function usage.

## Python Implementation

### IsPrimeNumber.py Snippet
```python
    def IsAPrimeNumber(num):
        if num <= 1:
            return False
        for i in range(2, int(num**0.5) + 1):
            if num % i == 0:
                return False
        return True
```

### IsPrimeNumberTest.py Snippet
```python
    def IsAPrimeNumberTest(num):
        self.assertTrue(IsAPrimeNumber.IsAPrimeNumber(11))
        self.assertFalse(IsAPrimeNumber.IsAPrimeNumber(9))
        self.assertFalse(IsAPrimeNumber.IsAPrimeNumber(0))
```    

## JavaScript Implementation

### IsPrimeNumber.java
```java
    public boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
```

### IsPrimeNumberTest.java
```java
    @BeforeEach
    public void setUp() {
        primeChecker = new IsAPrimeNumber();
    }

    public boolean isPrimeTest(int number) {
        assertTrue(primeChecker.isPrime(11));
        assertFalse(primeChecker.isPrime(9));
        assertFalse(primeChecker.isPrime(-3));
    }
```

## Explanation

I define a function that determines whether a given integer is a prime number. The function returns `True` if the number is prime, and `False` otherwise. The logic checks if the number is less than or equal to 1 (not prime), then iterates from 2 up to the square root of the number to see if any divisor exists. If a divisor is found, the function returns false; otherwise, it returns true.

Test cases are also provided in both languages to verify the correctness of the function, ensuring that known prime and non-prime numbers are handled as expected.

The following sections provide code snippets and further explanation for each implementation.


## Differences

### 1. Syntax:  
- Python uses indentation to define code blocks, while Java uses curly braces `{}`.
- Python function definitions use `def`, whereas Java uses explicit type declarations (`public boolean`).
- Python uses `True`/`False` (capitalized), Java uses `true`/`false` (lowercase).

### 2. Type Coercion:  
- Python is dynamically typed, so variable types are inferred at runtime.
- Java is statically typed, requiring explicit type declarations for variables and function return types.

### 3. Function Calls:  
- In Python, functions can be called directly without creating an object if defined at the module level.
- In Java, non-static methods require creating an instance of the class before calling the method (e.g., `primeChecker.isPrime(11)`).
