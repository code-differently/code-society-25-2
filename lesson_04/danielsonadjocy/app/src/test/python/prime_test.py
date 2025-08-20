import unittest
from app.src.main.python.prime import is_prime


class TestPrime(unittest.TestCase):
    """Unit tests for the prime number checker."""

    def test_is_prime(self):
        for x in [2, 3, 7, 13, 71, 79, 613]:
            self.assertTrue(is_prime(x), str(x) + " should be prime")


    def test_is_not_prime(self):
        for x in [1, 4, 6, 14, 44, 50, 82]:
            self.assertFalse(is_prime(x), str(x) + " is not prime")
        

if __name__ == "__main__":
    unittest.main()