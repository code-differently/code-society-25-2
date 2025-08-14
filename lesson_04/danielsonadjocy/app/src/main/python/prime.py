import unittest

def is_prime(num):
    if num <= 1:
        return False
    for val in range(2,int(num**(1/2))+1):
        if num % val == 0:
            return False
    return True