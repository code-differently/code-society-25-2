public class PrimeCheckTest {
    private static int testsPassed = 0;
    private static int totalTests = 0;

    public static void main(String[] args) {
        System.out.println("Running Java Prime Number Tests...\n");
        
        // Test edge cases
        testPrimeCheck("0 should not be prime", 0, false);
        testPrimeCheck("1 should not be prime", 1, false);
        testPrimeCheck("-5 should not be prime", -5, false);
        
        // Test small prime numbers
        testPrimeCheck("2 should be prime", 2, true);
        testPrimeCheck("3 should be prime", 3, true);
        testPrimeCheck("5 should be prime", 5, true);
        testPrimeCheck("7 should be prime", 7, true);
        
        // Test small composite numbers
        testPrimeCheck("4 should not be prime", 4, false);
        testPrimeCheck("6 should not be prime", 6, false);
        testPrimeCheck("8 should not be prime", 8, false);
        testPrimeCheck("9 should not be prime", 9, false);
        testPrimeCheck("10 should not be prime", 10, false);
        
        // Test larger prime numbers
        testPrimeCheck("11 should be prime", 11, true);
        testPrimeCheck("13 should be prime", 13, true);
        testPrimeCheck("17 should be prime", 17, true);
        testPrimeCheck("19 should be prime", 19, true);
        testPrimeCheck("23 should be prime", 23, true);
        testPrimeCheck("29 should be prime", 29, true);
        
        // Test larger composite numbers
        testPrimeCheck("12 should not be prime", 12, false);
        testPrimeCheck("15 should not be prime", 15, false);
        testPrimeCheck("21 should not be prime", 21, false);
        testPrimeCheck("25 should not be prime", 25, false);
        testPrimeCheck("30 should not be prime", 30, false);
        
        // Test perfect squares
        testPrimeCheck("16 (4²) should not be prime", 16, false);
        testPrimeCheck("25 (5²) should not be prime", 25, false);
        testPrimeCheck("49 (7²) should not be prime", 49, false);
        
        // Print summary
        System.out.println("\nTest Results:");
        System.out.println("Tests passed: " + testsPassed + "/" + totalTests);
        System.out.println("Success rate: " + (testsPassed * 100 / totalTests) + "%");
        
        if (testsPassed == totalTests) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("❌ Some tests failed.");
        }
    }
    
    private static void testPrimeCheck(String testName, int number, boolean expected) {
        totalTests++;
        boolean actual = PrimeCheck.isPrime(number);
        
        if (actual == expected) {
            System.out.println("✅ PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("❌ FAIL: " + testName + 
                             " - Expected: " + expected + ", Got: " + actual);
        }
    }
    
    // Copy of the isPrime method from PrimeCheck class for testing
    // In a real scenario, we'd import this from the PrimeCheck class
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false; // 0 and 1 are not prime
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // Found a divisor, so not prime
            }
        }
        return true; // No divisors found, so prime
    }
}
