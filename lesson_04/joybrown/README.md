## Python implementation

```python
def is_prime(number):
    if number <= 1:
        return False
    for i in range(2, int(number ** 0.5) + 1):
        if number % i == 0:
            return False
    return True

# Example usage:
print(is_prime(17))  # Output: True
print(is_prime(4))   # Output: False

## Java implentation

```javascript
function isPrime(number) {
    if (number <= 1) {
        return false;
    }
    for (let i = 2; i <= Math.sqrt(number); i++) {
        if (number % i === 0) {
            return false;
        }
    }
    return true;
}

// Example usage:
console.log(isPrime(17)); // Output: true
console.log(isPrime(4));  // Output: false

## Explanation 

The Python and JavaScript implementations above both determine if a number is prime. A prime number is greater than 1 and divisible only by 1 and itself. Both functions first rule out numbers less than or equal to 1. Then they loop from 2 to the square root of the number, checking for divisors. If a divisor is found, the function returns False/false. If none are found, the number is prime. For example, both implementations correctly identify 17 as prime and 4 as not prime.

### Differences

1. **Syntax**:

Python defines functions using the def keyword and indentation for blocks. Exponentiation uses ** (e.g., number ** 0.5 for the square root). Booleans are True and False.

JavaScript defines functions with the function keyword and uses curly braces {} for blocks. Square roots use Math.sqrt(number). Booleans are true and false.

2. **Looping Range**

Python uses the range() function to loop from 2 to the integer value of the square root plus 1.

JavaScript uses a standard for loop, starting at 2 and running until it exceeds the square root.

3. **Function Calls and Output**

Python displays results with print().

JavaScript displays results with console.log().

4. **Language Environment**

Python runs in a Python interpreter.

JavaScript runs in a browser or in Node.js.

Despite these differences, the core logic is identical, showing that algorithms can be translated between languages with minimal changes to structure and flow.