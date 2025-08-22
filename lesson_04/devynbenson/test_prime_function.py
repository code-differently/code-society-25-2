import unittest

def prime_function(n: int) -> bool:
    if n <= 1:
        return False

    if n <= 3:
        return True

    if n % 2 == 0 or n % 3 == 0:
        return False
    
    # Check for divisors from 5 to √n
    i = 5
    while i * i <= n:
        if n % i == 0 or n % (i + 2) == 0:
            return False
        i += 6
    
    return True


class TestPrimeFunction(unittest.TestCase):
    
    def test_edge_cases(self):
        """Test edge cases: numbers <= 1"""
        self.assertFalse(prime_function(1))  # only one positive divisor
        self.assertFalse(prime_function(0))
        self.assertFalse(prime_function(-1))
        self.assertFalse(prime_function(-5))
    
    def test_small_primes(self):
        """Test small prime numbers"""
        self.assertTrue(prime_function(2))   # only even prime
        self.assertTrue(prime_function(3))
        self.assertTrue(prime_function(5))
        self.assertTrue(prime_function(7))
        self.assertTrue(prime_function(11))
        self.assertTrue(prime_function(13))
    
    def test_composite_numbers(self):
        """Test composite numbers"""
        self.assertFalse(prime_function(4))  # 4 = 2×2, composite
        self.assertFalse(prime_function(6))  # 6 = 2×3, composite
        self.assertFalse(prime_function(8))
        self.assertFalse(prime_function(9))  # 9 = 3×3, composite
        self.assertFalse(prime_function(10))
        self.assertFalse(prime_function(12))
        self.assertFalse(prime_function(15))
    
    def test_larger_primes(self):
        """Test larger prime numbers"""
        self.assertTrue(prime_function(17))
        self.assertTrue(prime_function(19))
        self.assertTrue(prime_function(23))
        self.assertTrue(prime_function(29))
        self.assertTrue(prime_function(31))
        self.assertTrue(prime_function(97))
    
    def test_larger_composites(self):
        """Test larger composite numbers"""
        self.assertFalse(prime_function(21))  # 3×7
        self.assertFalse(prime_function(25))  # 5×5
        self.assertFalse(prime_function(49))  # 7×7
        self.assertFalse(prime_function(77))  # 7×11
        self.assertFalse(prime_function(91))  # 7×13


if __name__ == '__main__':
    # Run the tests
    unittest.main()
