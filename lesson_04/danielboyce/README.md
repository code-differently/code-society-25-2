## Java Implementation
```
public class IsPrime {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }

    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
```

## python implementation
```
import unittest
def is_prime(n):
    """Check if a number is prime."""
    if n <= 1:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

```

## Unit testing


```
class TestIsPrime(unittest.TestCase):
    def test_is_prime(self):
        self.assertFalse(is_prime(12))
        
    def test_is_prime_negative(self):
        self.assertFalse(is_prime(-5))
    def test_is_prime_zero(self):
        self.assertFalse(is_prime(0))
    def test_is_prime_two(self):
        self.assertTrue(is_prime(2))
        
       
if __name__ == '__main__':
    unittest.main()
```


## Explanation
The Java implementation uses a function called `IsPrime` that takes in  a single int parameter `n` and then uses a for loop to iterate from every number from 2 to the square root of n then checks any  numbers are divisible by n if the number is then it returns false and if it finished the loop then we return true. Using the square root of n as end condition for the loop allows us to avoid looking at every number from 1-n

The python uses a function called `is_prime` with the same logic as the java implementation