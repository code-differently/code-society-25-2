# Introduction
For this assignment, I chose to make my prime number functions in Python and C#. I chose these languages becaue they are two languages that I have the most experience with. After making them, I've noticed many similatries and differences.
## Similarities
 - Both implementations use for loops to check if the number was a prime.
 - Both functions use "number" as the input of the function.
 - Both handle edge cases by checking if number ≤ 1.
 - Both return a boolean value.
 - Both return false if number % i and is equal to 0.
## Differences
 - C# uses more optimized approach by explicitly handling even numbers and only checking odd divisors.
 - Python checks all numbers up to the square root. 
 - C# requires more explicit syntax with type declarations and a class structure, while Python's implementation is simpler. 
 - C# uses Math.Sqrt() for calculating the square root while Python uses the power operator (**). 
 - C# version includes additional optimizations like checking for even numbers separately and only testing odd divisors.

 ## Test Cases
Both implementations were tested with the following test cases:

1. Negative Numbers:
   - Test inputs: -1, -5, -10
   - Expected result: All should return false (not prime)

2. Edge Cases (0 and 1):
   - Test inputs: 0, 1
   - Expected result: Both should return false (not prime)

3. Prime Numbers:
   - Test inputs: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31
   - Expected result: All should return true (prime)

4. Non-Prime Numbers:
   - Test inputs: 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20
   - Expected result: All should return false (not prime)

Both Python and C# unit tests verify these cases using their respective testing frameworks (unittest for Python and MSTest for C#).
