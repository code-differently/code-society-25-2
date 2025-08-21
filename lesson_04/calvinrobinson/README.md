# Prime Number Checker - Python and JavaScript

Below are implementations that determine whether a number is prime.

## Python
```python
def is_prime(n: int) -> bool:
    """Return True if n is prime, False otherwise."""
    if n < 2:
        return False
    if n in (2, 3):
        return True
    if n % 2 == 0:
        return False
    i = 3
    while i * i <= n:
        if n % i == 0:
            return False
        i += 2
    return True

# Example usage
if __name__ == '__main__':
    print(is_prime(17))  # True
    print(is_prime(18))  # False
```

## JavaScript
```javascript
function isPrime(n) {
  if (!Number.isInteger(n)) return false;
  if (n < 2) return false;
  if (n === 2 || n === 3) return true;
  if (n % 2 === 0) return false;
  for (let i = 3; i * i <= n; i += 2) {
    if (n % i === 0) return false;
  }
  return true;
}

// Example usage
if (require.main === module) {
  console.log(isPrime(17)); // true
  console.log(isPrime(18)); // false
}

module.exports = { isPrime };
```

## Similarities Between Python and JavaScript

Same Logic: Both use the modulo operator (%) to check remainders
Function Structure: Both define functions that accept parameters and return values
Same Algorithm: Identical approach to determining even numbers
Comments: Both support comments (though different syntax)
Function Calls: Both call functions the same way with parentheses
Return Values: Both return boolean values for this operation

## Key Differences

| Aspect                    | Python                       | JavaScript                     |
| ------------------------- | ---------------------------- | ------------------------------ |
| **Function Definition**   | `def function_name():`       | `function functionName() {}`   |
| **Naming Convention**     | snake_case                   | camelCase                      |
| **Code Blocks**           | Indentation + colon          | Curly braces `{}`              |
| **Boolean Values**        | `True`/`False` (capitalized) | `true`/`false` (lowercase)     |
| **Comparison Operator**   | `==` (or `is` for identity)  | `===` (strict) or `==` (loose) |
| **Output Function**       | `print()`                    | `console.log()`                |
| **Comments**              | `# comment`                  | `// comment`                   |
| **Statement Termination** | No semicolons needed         | Semicolons recommended         |





