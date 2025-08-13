def is_prime(num):
    for val in range(2,int(num**(1/2))+1):
        if num % val == 0:
            return False
    return True


if __name__ == "__main__":
    for x in range (1,100):
        print(x, is_prime(x))