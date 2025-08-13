public class Prime {

    public static void main(String[] args) {
        
    }
    public static boolean isPrime(int num){
        for (int val = 2; val < Math.sqrt(num)+1; val++){
            if (num % val == 0){
                return false;
            }
            
        }
        return true;
    }
}