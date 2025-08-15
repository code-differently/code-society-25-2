import unittest
from prime import is_prime

class TestPrime(unittest.TestCase):
    def test_prime_numbers(self):
        self.assertTrue(is_prime(5))
        self.assertFalse(is_prime(-5))


if __name__ == '__main__':
    unittest.main()