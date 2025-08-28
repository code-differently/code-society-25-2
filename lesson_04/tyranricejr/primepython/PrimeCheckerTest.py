import unittest
from PrimeChecker import PrimeChecker

"""
Title: PrimeCheckerTest

Description: This class contains unit tests for the PrimeChecker class.

Created By: Tyran Rice Jr.
Date: 2023-10-01
"""


class PrimeCheckerTest(unittest.TestCase):

    """
    Title: testPrimeChecker

    Description: This function tests the PrimeChecker to test if a number is prime.
    """

    def testIsPrime(self):
        print("\n")
        self.assertTrue(PrimeChecker.IsAPrimeNumber(11))
        self.assertTrue(PrimeChecker.IsAPrimeNumber(2))
        self.assertTrue(PrimeChecker.IsAPrimeNumber(3))
        print("PrimeCheckerTest: testIsPrime passed")

    """
    Title: testIsNotPrime
    
    Description: This function tests the PrimeChecker to test if a number is not prime.
    """

    def testIsNotPrime(self):
        print("\n")
        self.assertFalse(PrimeChecker.IsAPrimeNumber(4))
        self.assertFalse(PrimeChecker.IsAPrimeNumber(9))
        self.assertFalse(PrimeChecker.IsAPrimeNumber(10))
        print("PrimeCheckerTest: testIsNotPrime passed")

    """
    Title: testEdgeCases
    
    Description: This function tests edge cases for the PrimeChecker.
    """

    def testEdgeCases(self):
        print("\n")
        self.assertFalse(PrimeChecker.IsAPrimeNumber(1))
        self.assertFalse(PrimeChecker.IsAPrimeNumber(0))
        self.assertFalse(PrimeChecker.IsAPrimeNumber(-3))
        print("PrimeCheckerTest: testEdgeCases passed")


if __name__ == "__main__":
    unittest.main()
