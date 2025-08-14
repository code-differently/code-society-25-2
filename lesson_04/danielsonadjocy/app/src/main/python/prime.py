import unittest

def is_prime(num):
    for val in range(2,int(num**(1/2))+1):
        if num % val == 0:
            return False
    return True

class TestPrime(unittest.TestCase):
    
    def tester(self):
        self.assertFalse(is_prime(14))
        self.assertTrue(is_prime(1))
        self.assertTrue(is_prime(2))
        self.assertFalse(is_prime(4))
        self.assertTrue(is_prime(79))
        self.assertFalse(is_prime(82))
        self.assertFalse(is_prime(6))
        self.assertTrue(is_prime(71))
        self.assertFalse(is_prime(50))
        self.assertFalse(is_prime(44))
        self.assertFalse(is_prime(39))
        self.assertTrue(is_prime(613))


if __name__ == "__main__":
    unittest.main()