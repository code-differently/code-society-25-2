def is_prime(num):
    """
    Checks if num is a prime number.

    Args:
        num (int): The number to check.

    Returns:
        bool: True if num is prime, False otherwise.
    """
    if num <= 1:
        return False
    for val in range(2,int(num**(1/2))+1):
        if num % val == 0:
            return False
    return True

if __name__ == "__main__":
    print(4, is_prime(4))
    print(13, is_prime(13))
    # Checks each number between 2 and 100 to see if they're prime.
    for num in range(2, 101):
        print(str(num) +": "+ str(is_prime(num)))