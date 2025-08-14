import unittest
import main.prime as prime


class TestPrime(unittest.TestCase):

    def tester(self):
        self.assertFalse(prime.is_prime(14))
        self.assertTrue(prime.is_prime(1))
        self.assertTrue(prime.is_prime(2))
        self.assertFalse(prime.is_prime(4))
        self.assertTrue(prime.is_prime(79))
        self.assertFalse(prime.is_prime(82))
        self.assertFalse(prime.is_prime(6))
        self.assertTrue(prime.is_prime(71))
        self.assertFalse(prime.is_prime(50))
        self.assertFalse(prime.is_prime(44))
        self.assertFalse(prime.is_prime(39))
        self.assertTrue(prime.is_prime(613), "613 should be prime")


if __name__ == "__main__":
    unittest.main()