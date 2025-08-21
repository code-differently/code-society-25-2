# Prime Function Tests

This directory contains unit tests for both Python and JavaScript prime function implementations.

## Running Python Tests

```bash
# Run the Python unit tests
python test_prime_function.py

# Or using unittest module
python -m unittest test_prime_function.py
```

Expected output should show all tests passing:
```
......
----------------------------------------------------------------------
Ran 6 tests in 0.001s

OK
```

## Running JavaScript Tests

```bash
# Run the JavaScript tests
node test_prime_function.js
```

Expected output should show all tests passing with ✅ markers.

## Test Coverage

Both test suites include:
- Edge cases (negative numbers, 0, 1, non-integers)
- Small prime numbers (2, 3, 5, 7, 11, 13)
- Small composite numbers (4, 6, 8, 9, 10, 12, 15)
- Larger prime numbers (17, 19, 23, 29, 31, 97)
- Larger composite numbers (21, 25, 49, 77, 91)

The tests verify the specific examples mentioned in the README:
- 1 returns false (only one positive divisor)
- 2 returns true (only even prime)  
- 4 returns false (4 = 2×2, composite)
- 6 returns false (6 = 2×3, composite)
- 9 returns false (9 = 3×3, composite)
