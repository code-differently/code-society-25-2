## Python implementation

```python
def is_prime(n):
    return n > 1 and all(n % i for i in range(2, int(n**0.5) + 1))

# Example usage:
print(is_prime(-5))  # False
print(is_prime(17))  # True
```

## TypeScript implementation

```typescript
function isPrime(n: number): boolean {
    return n > 1 && Array.from({ length: Math.floor(Math.sqrt(n)) - 1 }, (_, i) => i + 2)
     .every(i => n % i !== 0);
}

// Example usage:
console.log(isPrime(17)); // true
console.log(isPrime(18)); // false
```

## Similarities vs Differences



### Similarities
- They both use return `n > 1`, which helps to ensure that numbers less than or equal to 1 are not prime.
- Both languages use `i` as the variable for testing divisors of `n`.
- While the methods are different, they both have a way of creating a loop or a range of numbers.


### Differences
- The comment style in both languages is different. Python uses hashtags while TypeScript uses slashes.
- Python uses `n**0.5`, while TypeScript uses `Math.sqrt(n)` to call the sqaure root.
- Python has range , which produces a sequence of integers. However, while TypeScript does not have `range` built in, it does have `Array`, which you can use to generate the sequence.

## Quiz Instructions

### Python Quiz

1. Open the project in **VS Code** 
2. Navigate to the [prime_python] directory.
```bash
cd lesson_04/trinitiejackson/prime_python
```
3. Check to make sure you have Python 3 installed
```bash
python3 --version
```
If not, do:
```bash
brew install python3
```
3. Open the [prime_test] file.
4. Update the code to determine whether a number is prime.
5. When ready to test, run the following command in the [prime_python] directory using the terminal:
```bash
python3 prime_test.py
```
### TypeScript Quiz

1. Make sure to sync your fork to pull in the latest changes.
2. Open the project in **VS Code**.
3. Navigate to the [prime_typescript] directory and install the required dependencies.
```bash
cd lesson_04/trinitiejackson/prime_typescript
npm install
```
4. Open the [prime_test.ts] file located in the [prime_typescript/src/] directory.
5. Update the code to determine whether a number is prime.
6. When ready to test, run the following command in the terminal:
```bash
npm test
```