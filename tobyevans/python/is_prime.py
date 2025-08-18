import math

def is_prime(n: int) -> bool:
    """Return True if n is a prime number, else False."""
    if not isinstance(n, int):
        raise TypeError("n must be an integer")
    if n < 2:
        return False
    if n % 2 == 0:
        return n == 2
    if n % 3 == 0:
        return n == 3
    limit = math.isqrt(n)
    i = 5
    step = 2
    while i <= limit:
        if n % i == 0:
            return False
        i += step
        step = 6 - step  # check 6k±1
    return True

if __name__ == "__main__":
    tests = [0, 1, 2, 3, 4, 5, 17, 18, 19, 20, 7919, 7920]
    for t in tests:
        print(f"{t}: {is_prime(t)}")
