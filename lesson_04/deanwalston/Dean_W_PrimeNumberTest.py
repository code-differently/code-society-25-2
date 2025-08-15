from Dean_W_PrimeNumber import is_prime
import unittest
import os
import sys

# Ensure the module directory is on sys.path so tests can be run from repo root or the folder
here = os.path.dirname(__file__)
if here and here not in sys.path:
    sys.path.insert(0, here)


class Dean_W_PrimeNumberTest(unittest.TestCase):

    def _print_and_assert(self, n: int, expected: bool):
        result = is_prime(n)
        # Print the result for clarity in test output
        print(f"{n} -> {'prime' if result else 'not prime'}")
        self.assertEqual(result, expected)

    def test_prime_numbers(self):
        primes = [2, 3, 5, 7, 11, 97]
        for n in primes:
            with self.subTest(n=n):
                self._print_and_assert(n, True)

    def test_non_prime_numbers(self):
        non_primes = [1, 4, 6, 9, 33, 24]
        for n in non_primes:
            with self.subTest(n=n):
                self._print_and_assert(n, False)

    def test_negative_numbers(self):
        negatives = [-5, -7]
        for n in negatives:
            with self.subTest(n=n):
                self._print_and_assert(n, False)

    def test_zero(self):
        self._print_and_assert(0, False)


if __name__ == '__main__':
    unittest.main()
