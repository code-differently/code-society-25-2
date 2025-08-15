#!/usr/bin/env python3
"""
Prime Number Checker - Python Implementation
Author: Kerry Ferguson
Date: August 14, 2025

This module provides functionality to determine whether a given number is prime.
A prime number is a natural number greater than 1 that has no positive divisors
other than 1 and itself.
"""

import math


def is_prime(n):
    """
    Determines whether a given number is prime.

    Args:
        n (int): The number to check for primality

    Returns:
        bool: True if the number is prime, False otherwise
    """
    # Handle edge cases
    if n < 2:
        return False
    if n == 2:
        return True
    if n % 2 == 0:
        return False

    # Check odd divisors up to sqrt(n)
    sqrt_n = int(math.sqrt(n))
    for i in range(3, sqrt_n + 1, 2):
        if n % i == 0:
            return False

    return True


def main():
    """
    Main function to demonstrate prime number checking functionality.
    """
    print("Prime Number Checker - Python Implementation")
    print("=" * 45)

    # Test individual numbers
    test_numbers = [1, 2, 3, 4, 5, 17, 25, 29, 97, 100]

    print("\nTesting individual numbers:")
    for num in test_numbers:
        result = is_prime(num)
        print(f"{num:3d} -> {'Prime' if result else 'Not Prime'}")

    # Interactive mode
    print("\nInteractive mode (enter 0 to exit):")
    while True:
        try:
            user_input = int(input("Enter a number to check: "))
            if user_input == 0:
                break
            result = is_prime(user_input)
            print(f"{user_input} is {'prime' if result else 'not prime'}")
        except ValueError:
            print("Please enter a valid integer.")
        except KeyboardInterrupt:
            print("\nGoodbye!")
            break


if __name__ == "__main__":
    main()
