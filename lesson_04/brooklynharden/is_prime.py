import unittest

def is_prime(num):
    if num <= 1:
        return False
    
    for i in range(2, int(num ** .5) + 1):
        if num % i == 0:
            return False 
    return True

class TestIsPrime(unittest.TestCase):

    def test_prime_numbers(self):
        """Test for prime numbers."""
        self.assertTrue(is_prime(2))
        self.assertTrue(is_prime(3))
        self.assertTrue(is_prime(5))
        self.assertTrue(is_prime(7))
        self.assertTrue(is_prime(11))
        self.assertTrue(is_prime(97))

    def test_non_prime_numbers(self):
        """Test for non-prime numbers."""
        self.assertFalse(is_prime(4))
        self.assertFalse(is_prime(6))
        self.assertFalse(is_prime(8))
        self.assertFalse(is_prime(9))
        self.assertFalse(is_prime(100))

    def test_edge_cases(self):
        """Test for edge cases."""
        self.assertFalse(is_prime(0))
        self.assertFalse(is_prime(1))
        self.assertFalse(is_prime(-1))
        self.assertFalse(is_prime(-10))

if __name__ == '__main__':
    unittest.main()
