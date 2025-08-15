public class PrimeCheck {
    public static void main(String[] args) {
        int number = 29; // Change this number to test other values

        if (isPrime(number)) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is NOT a prime number.");
        }
    }

    // Method to check if a number is prime
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