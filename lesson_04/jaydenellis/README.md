# Prime Number Checker Assignment

## Python Implementation

```python
def is_prime(n):
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False
    
    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True

# Example usage:
print(is_prime(7))   # Output: True
print(is_prime(10))  # Output: False

## JavaScript Implementation

```javascript
function isPrime(n) {
    if (n <= 1) return false;
    if (n <= 3) return true;
    if (n % 2 === 0 || n % 3 === 0) return false;

    let i = 5;
    while (i * i <= n) {
        if (n % i === 0 || n % (i + 2) === 0) {
            return false;
        }
        i += 6;
    }
    return true;
}

// Example usage:
console.log(isPrime(7));   // Output: true
console.log(isPrime(10));  // Output: false


Differences
Syntax

In Python, functions are defined using the def keyword, whereas in JavaScript, the function keyword is used.

Python uses True and False for boolean values, while JavaScript uses lowercase true and false.

Looping Structure

Both use a while loop for efficiency, but Python relies on indentation for scope, while JavaScript uses curly braces {}.

Output

Python uses print() to display results.

JavaScript uses console.log().

Typing

Python enforces stricter type rules.

JavaScript allows implicit type coercion, which can lead to unexpected behavior if inputs aren’t carefully checked.