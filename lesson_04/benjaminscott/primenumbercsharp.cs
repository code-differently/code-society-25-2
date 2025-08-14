using System;

public class PrimeChecker
{
    public static bool IsPrime(int number)
    {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;

        int sqrt = (int)Math.Sqrt(number);
        for (int i = 3; i <= sqrt; i += 2)
        {
            if (number % i == 0)
                return false;
        }
        return true;
    }

      public static void Main(string[] args)
    {
        Console.WriteLine($"Is 7 prime? {IsPrime(7)}"); 
        Console.WriteLine($"Is 15 prime? {IsPrime(15)}"); 
        Console.WriteLine($"Is 23 prime? {IsPrime(23)}");
        Console.WriteLine($"Is 1 prime? {IsPrime(1)}");
    }
}

