using System;
using Microsoft.VisualStudio.TestTools.UnitTesting;

[TestClass]
public class PrimeCheckerTests
{
    [TestMethod]
    public void TestNegativeNumbers()
    {
        Assert.IsFalse(PrimeChecker.IsPrime(-1));
        Assert.IsFalse(PrimeChecker.IsPrime(-5));
        Assert.IsFalse(PrimeChecker.IsPrime(-10));
    }

    [TestMethod]
    public void TestZeroAndOne()
    {
        Assert.IsFalse(PrimeChecker.IsPrime(0));
        Assert.IsFalse(PrimeChecker.IsPrime(1));
    }

    [TestMethod]
    public void TestPrimeNumbers()
    {
        int[] primeNumbers = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31 };
        foreach (int num in primeNumbers)
        {
            Assert.IsTrue(PrimeChecker.IsPrime(num), $"{num} should be prime");
        }
    }

    [TestMethod]
    public void TestNonPrimeNumbers()
    {
        int[] nonPrimeNumbers = { 4, 6, 8, 9, 10, 12, 14, 15, 16, 18, 20 };
        foreach (int num in nonPrimeNumbers)
        {
            Assert.IsFalse(PrimeChecker.IsPrime(num), $"{num} should not be prime");
        }
    }
}
