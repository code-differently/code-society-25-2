def is_prime(n: int) -> bool:
    if n <= 1:
        return False
    if n <= 3:
        return True
    if n % 2 == 0 or n % 3 == 0:
        return False

    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    return True

    # test before JUnit Tests to make sure program works
if __name__ == "__main__":
    test_numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 16, 17, 19, 21, 23, 26]
    for num in test_numbers:
        print(f"{num} is prime? {is_prime(num)}")
