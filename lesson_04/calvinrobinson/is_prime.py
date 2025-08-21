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


if __name__ == "__main__":
    # simple manual tests
    tests = [0, 1, 2, 3, 4, 17, 18, 7919]
    for t in tests:
        print(f"{t}: {is_prime(t)}")
