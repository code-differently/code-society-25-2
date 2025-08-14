def is_prime(num):
    if num <= 1:
        return False
    for val in range(2,int(num**(1/2))+1):
        if num % val == 0:
            return False
    return True

if __name__ == "__main__":
    print(4, is_prime(4))
    print(13, is_prime(13))
    for num in range(2, 101):
        print(str(num) +": "+ str(is_prime(num)))