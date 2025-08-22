#!/usr/bin/env python3
"""
Test suite for prime_checker.py

This module contains comprehensive unit tests for the Python prime number checker
implementation, including edge cases, boundary conditions, and performance tests.
"""

import unittest
import sys
import io
from contextlib import redirect_stdout
from unittest.mock import patch
from prime_checker import is_prime, main


class TestIsPrime(unittest.TestCase):
    """Test cases for the is_prime function."""

    def test_numbers_less_than_two(self):
        """Test that numbers less than 2 are not prime."""
        self.assertFalse(is_prime(-5))
        self.assertFalse(is_prime(-1))
        self.assertFalse(is_prime(0))
        self.assertFalse(is_prime(1))

    def test_number_two(self):
        """Test that 2 is correctly identified as prime."""
        self.assertTrue(is_prime(2))

    def test_even_numbers(self):
        """Test that even numbers greater than 2 are not prime."""
        even_numbers = [4, 6, 8, 10, 12, 100, 1000]
        for num in even_numbers:
            with self.subTest(num=num):
                self.assertFalse(is_prime(num))

    def test_small_primes(self):
        """Test small known prime numbers."""
        small_primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47]
        for prime in small_primes:
            with self.subTest(prime=prime):
                self.assertTrue(is_prime(prime))

    def test_small_composites(self):
        """Test small known composite numbers."""
        composites = [4, 6, 8, 9, 10, 12, 14, 15,
                      16, 18, 20, 21, 22, 24, 25, 26, 27, 28]
        for composite in composites:
            with self.subTest(composite=composite):
                self.assertFalse(is_prime(composite))

    def test_larger_primes(self):
        """Test larger known prime numbers."""
        larger_primes = [53, 59, 61, 67, 71, 73,
                         79, 83, 89, 97, 101, 103, 107, 109, 113]
        for prime in larger_primes:
            with self.subTest(prime=prime):
                self.assertTrue(is_prime(prime))

    def test_perfect_squares(self):
        """Test that perfect squares (except 1) are not prime."""
        perfect_squares = [9, 25, 49, 121, 169,
                           289]  # 3², 5², 7², 11², 13², 17²
        for square in perfect_squares:
            with self.subTest(square=square):
                self.assertFalse(is_prime(square))

    def test_edge_cases(self):
        """Test tricky numbers that might fool naive algorithms."""
        # These are composite numbers that might be mistakenly identified as prime
        tricky_composites = [91, 93, 95, 121, 143, 169, 187, 209]
        for num in tricky_composites:
            with self.subTest(num=num):
                self.assertFalse(is_prime(num))

        # Test some larger primes
        larger_primes = [127, 131, 137, 139, 149]
        for prime in larger_primes:
            with self.subTest(prime=prime):
                self.assertTrue(is_prime(prime))


class TestMainFunction(unittest.TestCase):
    """Test cases for the main interactive function."""

    def test_main_output_contains_expected_content(self):
        """Test that main function produces expected output."""
        with redirect_stdout(io.StringIO()) as captured_output:
            # Mock the interactive input to exit immediately
            with patch('builtins.input', side_effect=['0']):
                try:
                    main()
                except (SystemExit, EOFError):
                    pass

        output = captured_output.getvalue()
        # Check that key elements are in the output
        self.assertIn("Prime Number Checker", output)
        self.assertIn("Testing individual numbers", output)

    def test_main_interactive_prime_check(self):
        """Test interactive prime checking functionality."""
        with redirect_stdout(io.StringIO()) as captured_output:
            # Mock input: check 17 (prime), then exit
            with patch('builtins.input', side_effect=['17', '0']):
                try:
                    main()
                except (SystemExit, EOFError):
                    pass

        output = captured_output.getvalue()
        self.assertIn("17 is prime", output)

    def test_main_interactive_composite_check(self):
        """Test interactive composite checking functionality."""
        with redirect_stdout(io.StringIO()) as captured_output:
            # Mock input: check 15 (composite), then exit
            with patch('builtins.input', side_effect=['15', '0']):
                try:
                    main()
                except (SystemExit, EOFError):
                    pass

        output = captured_output.getvalue()
        self.assertIn("15 is not prime", output)

    def test_main_invalid_input_handling(self):
        """Test handling of invalid input."""
        with redirect_stdout(io.StringIO()) as captured_output:
            # Mock input: invalid string, then exit
            with patch('builtins.input', side_effect=['abc', '0']):
                try:
                    main()
                except (SystemExit, EOFError):
                    pass

        output = captured_output.getvalue()
        self.assertIn("valid integer", output)

    def test_main_keyboard_interrupt_handling(self):
        """Test handling of keyboard interrupt."""
        with redirect_stdout(io.StringIO()) as captured_output:
            # Mock input: KeyboardInterrupt should trigger the except block
            with patch('builtins.input', side_effect=KeyboardInterrupt()):
                main()  # Let the function handle the KeyboardInterrupt internally

        output = captured_output.getvalue()
        self.assertIn("Goodbye", output)


class TestPerformance(unittest.TestCase):
    """Performance and boundary tests."""

    def test_larger_primes(self):
        """Test that larger primes are handled efficiently."""
        larger_primes = [997, 1009, 1013, 1019, 1021]
        for prime in larger_primes:
            with self.subTest(prime=prime):
                self.assertTrue(is_prime(prime))

    def test_larger_composites(self):
        """Test larger composite numbers."""
        larger_composites = [1000, 1001, 1002, 1004, 1006]
        for composite in larger_composites:
            with self.subTest(composite=composite):
                self.assertFalse(is_prime(composite))


if __name__ == "__main__":
    # Create a test suite
    test_suite = unittest.TestSuite()

    # Add all test classes
    test_classes = [TestIsPrime,
                    TestMainFunction, TestPerformance]

    for test_class in test_classes:
        tests = unittest.TestLoader().loadTestsFromTestCase(test_class)
        test_suite.addTests(tests)

    # Run the tests with detailed output
    runner = unittest.TextTestRunner(verbosity=2)
    result = runner.run(test_suite)

    # Print summary
    print(f"\nTest Summary:")
    print(f"Tests run: {result.testsRun}")
    print(f"Failures: {len(result.failures)}")
    print(f"Errors: {len(result.errors)}")
    if result.testsRun > 0:
        print(
            f"Success rate: {((result.testsRun - len(result.failures) - len(result.errors)) / result.testsRun * 100):.1f}%")

    # Exit with appropriate code
    sys.exit(0 if result.wasSuccessful() else 1)
