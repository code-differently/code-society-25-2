import unittest
from IsAPrimeNumber import IsAPrimeNumber

"""
Title: IsAPrimeNumberTest

Description: This class contains unit tests for the IsAPrimeNumber class.

Created By: Tyran Rice Jr.
Date: 2023-10-01
"""


class IsAPrimeNumberTest(unittest.TestCase):

    """
    Title: testIsAPrimeNumber

    Description: This function tests the IsAPrimeNumber to test if a number is prime.
    """

    def testIsPrime(self):
        print("\n")
        self.assertTrue(IsAPrimeNumber.IsAPrimeNumber(11))
        self.assertTrue(IsAPrimeNumber.IsAPrimeNumber(2))
        self.assertTrue(IsAPrimeNumber.IsAPrimeNumber(3))
        print("IsAPrimeNumberTest: testIsPrime passed")

    """
    Title: testIsNotPrime
    
    Description: This function tests the IsAPrimeNumber to test if a number is not prime.
    """

    def testIsNotPrime(self):
        print("\n")
        self.assertFalse(IsAPrimeNumber.IsAPrimeNumber(4))
        self.assertFalse(IsAPrimeNumber.IsAPrimeNumber(9))
        self.assertFalse(IsAPrimeNumber.IsAPrimeNumber(10))
        print("IsAPrimeNumberTest: testIsNotPrime passed")

    """
    Title: testEdgeCases
    
    Description: This function tests edge cases for the IsAPrimeNumber.
    """

    def testEdgeCases(self):
        print("\n")
        self.assertFalse(IsAPrimeNumber.IsAPrimeNumber(1))
        self.assertFalse(IsAPrimeNumber.IsAPrimeNumber(0))
        self.assertFalse(IsAPrimeNumber.IsAPrimeNumber(-3))
        print("IsAPrimeNumberTest: testEdgeCases passed")


if __name__ == "__main__":
    unittest.main()
