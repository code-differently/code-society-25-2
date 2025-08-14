public class Prime {
    public static void main(String[] args) {
        System.out.println(4 + " " + isPrime(4));
        System.out.println(13 + " " + isPrime(13));

        for (int num = 2; num <=100; num++){
            System.out.println(num+": "+ isPrime(num));
        }
    }
    public static boolean isPrime(int num){
        for (int val = 2; val <= Math.sqrt(num); val++){
            if (num % val == 0){
                return false;
            }
            
        }
        return true;
    }
}