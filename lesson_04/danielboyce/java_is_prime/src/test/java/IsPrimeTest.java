
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.primenumbers.IsPrime;

public class IsPrimeTest {
    

    @Test
    public void testIsPrime() {
        assertTrue(IsPrime.isPrime(7));
    }


    @Test
    public void testIsNotPrime() {
        assertFalse(IsPrime.isPrime(4));
    }


    @Test
    public void testIsPrimeEdgeCases() {
        assertTrue(IsPrime.isPrime(2));
        assertTrue(IsPrime.isPrime(1));
        assertFalse(IsPrime.isPrime(0));
    }
    
    @Test
    public void testNegativeNumbers() {
        assertFalse(IsPrime.isPrime(-5));
        assertFalse(IsPrime.isPrime(-1));
    }

    
}
