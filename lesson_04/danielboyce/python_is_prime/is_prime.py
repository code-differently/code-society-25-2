import unittest
def is_prime(n):
    """Check if a number is prime."""
    if n <= 1:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True


class TestIsPrime(unittest.TestCase):
    def test_is_prime(self):
        self.assertFalse(is_prime(12))
        
    def test_is_prime_negative(self):
        self.assertFalse(is_prime(-5))
    def test_is_prime_zero(self):
        self.assertFalse(is_prime(0))
    def test_is_prime_two(self):
        self.assertTrue(is_prime(2))
        
       
if __name__ == '__main__':
    unittest.main()
