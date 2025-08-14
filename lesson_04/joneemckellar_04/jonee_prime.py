import math


def is_prime(number):
    """
    Determines if a given number is a prime number.

    A prime number is a natural number greater than 1 that has no positive divisors
    other than 1 and itself.

    Args:
        number (int): The integer to check.

    Returns:
        bool: True if the number is prime, False otherwise.
    """
    if number <= 1:
        return False  # Numbers less than or equal to 1 are not prime
    if number == 2:
        return True   # 2 is the only even prime number
    if number % 2 == 0:
        return False  # Other even numbers are not prime

    # Check for divisors from 3 up to the square root of the number,
    # incrementing by 2 to only check odd numbers.
    for i in range(3, int(math.sqrt(number)) + 1, 2):
        if number % i == 0:
            return False  # Found a divisor, so it's not prime
    return True  # No divisors found, it's prime

# Example usage:
# You can uncomment these lines and run the file to see the output
# print(f"Is 7 prime? {is_prime(7)}")
# print(f"Is 10 prime? {is_prime(10)}")
# print(f"Is 2 prime? {is_prime(2)}")
# print(f"Is 1 prime? {is_prime(1)}")
# print(f"Is 13 prime? {is_prime(13)}")
# print(f"Is 49 prime? {is_prime(49)}")
