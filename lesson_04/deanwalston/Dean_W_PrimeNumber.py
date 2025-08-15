import math

# Prime number checking function


def is_prime(number: int) -> bool:
    """Return True if number is prime, otherwise False.

    Uses simple 2-check and then tests odd divisors up to sqrt(number).
    """
    if number <= 1:
        return False
    if number == 2:
        return True
    if number % 2 == 0:
        return False

    limit = int(math.isqrt(number)) + 1
    for i in range(3, limit, 2):
        if number % i == 0:
            return False
    return True


if __name__ == "__main__":
    print(1, "is prime" if is_prime(15) else "is not prime")
    print(24, "is prime" if is_prime(24) else "is not prime")
    print(55, "is prime" if is_prime(55) else "is not prime")
