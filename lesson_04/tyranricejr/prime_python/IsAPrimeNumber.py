
"""
Title: Lesson 4 IsAPrimeNumber Module

Description: This module contains a class that checks if a number is prime.

Created By: Tyran Rice Jr.
Date: 2023-10-01

"""


class IsAPrimeNumber():

    """
    Title: IsAPrimeNumber 

    Description: This function checks if a number is prime.

    Parameters:
    - num (int): The number to check.

    Returns:
    - bool: True if the number is prime, False otherwise.
    """
    def IsAPrimeNumber(num):
        if num <= 1:
            return False
        for i in range(2, int(num**0.5) + 1):
            if num % i == 0:
                return False
        return True
