## Python Implementation
```
def is_prime(number):
    if number <= 1:
        return False
    for i in range(2, int(number ** 0.5) + 1):
        if number % i == 0:
            return False
    return True

if __name__ == "__main__":
    test_numbers = [1, 2, 3, 4, 5, 7, 9, 11, 13, 15, 17, 19, 21]
    for num in test_numbers:
        print(f"Is {num} prime? {is_prime(num)}")
```
## C# Implementation
```
using System;

public class PrimeChecker
{
    public static bool IsPrime(int number)
    {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        int sqrt = (int)Math.Sqrt(number);
        for (int i = 3; i <= sqrt; i += 2)
        {
            if (number % i == 0)
                return false;
        }
        return true;
    }

public static void Main(string[] args)
    {
         Console.WriteLine($"Is 7 prime? {IsPrime(7)}");
         Console.WriteLine($"Is 15 prime? {IsPrime(15)}");
         Console.WriteLine($"Is 23 prime? {IsPrime(23)}");
         Console.WriteLine($"Is 1 prime? {IsPrime(1)}");
    }
}
```
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
