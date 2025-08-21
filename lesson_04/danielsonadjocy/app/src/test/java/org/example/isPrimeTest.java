import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.junit.jupiter.api.Test;

public class isPrimeTest {

  @Test
  void testPrime() {
    int[] nums = {2, 3, 7, 13, 71, 79, 613};
    for (int num : nums){
        assertTrue(Prime.isPrime(num), num + " is a prime number");
    }
  }

  @Test
  void testnotPrime() {
    int[] nums = {1, 4, 6, 14, 44, 50, 82};
    for (int num : nums){
        assertFalse(Prime.isPrime(num), num + " is not a prime number");
    }
  }
}
