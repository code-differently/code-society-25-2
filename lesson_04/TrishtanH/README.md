## Java implementation

```java
public class PrimeCheck {
    public static void main(String[] args) {
        int number = 29; // Change this number to test other values

        if (isPrime(number)) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is NOT a prime number.");
        }
    }

    // Method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false; // 0 and 1 are not prime
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // Found a divisor, so not prime
            }
        }
        return true; // No divisors found, so prime
    }
}
```

## JavaScript implementation

```javascript
function isPrime(number) {
    // Numbers less than or equal to 1 are not prime
    if (number <= 1) {
        return false;
    }
    
    // Check for divisors up to square root of number
    for (let i = 2; i <= Math.sqrt(number); i++) {
        if (number % i === 0) {
            return false; // Found a divisor, so not prime
        }
    }
    
    return true; // No divisors found, so prime
}

// Test prime numbers
console.log("Is 7 prime?", isPrime(7));   // Output: true
console.log("Is 4 prime?", isPrime(4));   // Output: false
console.log("Is 13 prime?", isPrime(13)); // Output: true
console.log("Is 29 prime?", isPrime(29)); // Output: true
```

## Explanation

Both implementations use the same mathematical approach to determine if a number is prime: they check if the number has any divisors between 2 and the square root of the number. The Java implementation is structured as a class with a main method that demonstrates the prime checking functionality, while the JavaScript implementation uses a standalone function with multiple test cases.

### Similarities

1. **Core Algorithm**: Both implementations use the same efficient prime-checking algorithm that only tests divisors up to the square root of the input number.
2. **Edge Case Handling**: Both correctly handle the edge case where numbers less than or equal to 1 are not considered prime.
3. **Logic Flow**: The control flow is identical - check edge cases first, then loop through potential divisors, returning false if any divisor is found.
4. **Performance**: Both use the optimized approach of only checking up to Math.sqrt(number), making them equally efficient.

### Differences

1. **Language Structure**: 
   - **Java** requires a class definition and uses a static main method as the entry point for execution.
   - **JavaScript** runs functions and code directly without requiring a class structure.

2. **Type System**:
   - **Java** uses static typing with explicit type declarations (`int num`, `boolean isPrime`).
   - **JavaScript** uses dynamic typing where variables can hold any type without declaration.

3. **Compilation vs Interpretation**:
   - **Java** is a compiled language that must be compiled to bytecode before execution.
   - **JavaScript** is interpreted at runtime by the JavaScript engine.

4. **Testing Approach**:
   - **Java** implementation tests a single number (29) and prints a descriptive message.
   - **JavaScript** implementation tests multiple numbers (7, 4, 13, 29) with concise output showing the function's return values.

5. **Syntax Differences**:
   - **Java** uses `System.out.println()` for output while **JavaScript** uses `console.log()`.
   - **Java** uses `Math.sqrt()` within a class context while **JavaScript** accesses it directly.
   - Variable declarations differ: `int i` in Java vs `let i` in JavaScript.
