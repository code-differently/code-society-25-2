import unittest

def is_prime(number):
    if number < 2:
        return False
    for i in range(2, int(number**0.5) + 1):
        if number % i == 0:
            return False
    return True

class TestPrimeFunction(unittest.TestCase):
    
    def test_range_1_to_30(self):
        #Test numbers from 1 to 30 and display results
        print("\nTesting range 1 to 30:")
        for num in range(1, 31):
            result = is_prime(num)
            print(f"is_prime({num}) = {result}")
            
            self.assertIsInstance(result, bool)  # Should return True or False

if __name__ == '__main__':
    unittest.main()