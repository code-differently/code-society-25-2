import unittest
from primenumberpython import is_prime


class TestPrimeNumber(unittest.TestCase):
    def test_negative_numbers(self):
        self.assertFalse(is_prime(-1))
        self.assertFalse(is_prime(-5))
        self.assertFalse(is_prime(-10))

    def test_zero_and_one(self):
        self.assertFalse(is_prime(0))
        self.assertFalse(is_prime(1))

    def test_prime_numbers(self):
        prime_numbers = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31]
        for num in prime_numbers:
            self.assertTrue(is_prime(num), f"{num} should be prime")

    def test_non_prime_numbers(self):
        non_prime_numbers = [4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20]
        for num in non_prime_numbers:
            self.assertFalse(is_prime(num), f"{num} should not be prime")


if __name__ == '__main__':
    unittest.main()
