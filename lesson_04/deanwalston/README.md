# Prime Number Checker — Python & JavaScript

This project contains two implementations of a prime number checking algorithm:
- **Python:** `Dean_W_PrimeNumber.py`
- **JavaScript:** `dean_w_prime_number_check.js`

## Comparison & Differences

  - Both implementations follow the same high-level strategy: test for small non-prime cases first, then look for divisors up to the square root of the target number and return a boolean indicating primality. 
  - The test suites for both languages exercise primes, non-primes, negatives and zero.
  - I added explicit logging to both test suites so test runs print each number and its primality result. 

- Algorithm detail:
  - Python implementation uses `math.isqrt` to compute an integer square root and then iterates odd divisors only (skipping even numbers after handling 2 explicitly).
    - Which reduces loop iterations by about half for large inputs and avoids floating-point rounding issues.
     
  - JavaScript implementation uses `Math.sqrt` (a floating-point result) and checks every integer divisor in a simple loop starting from 2.
    - This is perfectly correct for typical inputs but requires care for very large integers where floating-point precision could affect the loop bound.

- Language & runtime:
  - Python is written as a straightforward module and tested with `unittest` (built-in). The tests use prints plus assertions like `assertTrue`/`assertFalse` to test runs show each checked value.
  - JavaScript uses CommonJS (`module.exports`/`require`) and is tested with Jest. Jest tests now log each tested value as well.
  - JavaScript uses Jest with `expect(...).toBe(...)`

- Module & testing differences:
  - Python module imports are direct; tests are runnable with `python3 -m unittest`. JavaScript tests are run via `npm test` which invokes Jest.

Overall, both implementations are functionally equivalent for normal integer inputs; the Python variant is slightly more robust against edge cases involving very large integers because it avoids float-based bounds and skips even divisors for a modest performance gain.

## Running the Python code and tests (zsh)

- To run the Python script directly (prints a couple of example results):

  cd lesson_04/deanwalston
  python3 Dean_W_PrimeNumber.py

- To run the unit tests (these tests also print each tested number and whether it is prime):

  cd lesson_04/deanwalston
  python3 -m unittest -v Dean_W_PrimeNumberTest.py

Notes:
- Running the tests with `-v` (verbose) will show test names and the printed output from each assertion helper (e.g. `17 -> prime`).

## Running the JavaScript code and tests (zsh)

- Install dependencies and run Jest tests:

  cd lesson_04/deanwalston/package
  npm install
  npm test

The Jest tests print each tested value (e.g. `17 -> prime`) and will fail if any assertion fails.

