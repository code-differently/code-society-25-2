def is_less_than_or_equal_to_one(n: int) -> bool:
    return n <= 1


def is_two_or_three(n: int) -> bool:
    return n == 2 or n == 3


def is_divisible_by_two_or_three(n: int) -> bool:
    return n % 2 == 0 or n % 3 == 0


def has_other_divisors(n: int) -> bool:
    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return True
        i += 6
    return False


def is_prime(n: int) -> bool:
    if is_less_than_or_equal_to_one(n):
        return False
    if is_two_or_three(n):
        return True
    if is_divisible_by_two_or_three(n):
        return False
    return not has_other_divisors(n)


# Testing the program before JUnit tests
if __name__ == "__main__":
    test_numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 16, 17, 19, 21, 23, 26]
    for num in test_numbers:
        print(f"{num} is prime? {is_prime(num)}")
