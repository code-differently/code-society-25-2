public class Prime {
  public static void main(String[] args) {
    System.out.println(4 + " " + isPrime(4));
    System.out.println(13 + " " + isPrime(13));

    // Checks each number between 2 and 100 to see if they're prime.
    for (int num = 2; num <= 100; num++) {
      System.out.println(num + ": " + isPrime(num));
    }
  }

  /**
   * Checks if num is a prime number.
   *
   * @param num the number to check
   * @return true if num is prime, false otherwise
   */
  public static boolean isPrime(int num) {
    if (num <= 1){
        return false;
    }
    for (int val = 2; val <= Math.sqrt(num); val++) {
      if (num % val == 0) {
        return false;
      }
    }
    return true;
  }
}
