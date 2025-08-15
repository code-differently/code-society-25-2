#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

// Function to check if a number is prime
bool isPrime(int num) {
    if (num <= 1) return false;
    if (num == 2) return true;
    if (num % 2 == 0) return false;

    int sqrtNum = sqrt(num);
    for (int i = 3; i <= sqrtNum; i += 2) {
        if (num % i == 0) return false;
    }
    return true;
}

// Function to generate prime numbers up to a limit
vector<int> generatePrimes(int limit) {
    vector<int> primes;
    for (int i = 2; i <= limit; i++) {
        if (isPrime(i)) {
            primes.push_back(i);
        }
    }
    return primes;
}

int main() {
    int choice;
    cout << "Prime Number Tool\n";
    cout << "1. Check if a number is prime\n";
    cout << "2. Generate prime numbers up to a limit\n";
    cout << "Enter your choice: ";
    cin >> choice;

    if (choice == 1) {
        int num;
        cout << "Enter a number: ";
        cin >> num;
        if (isPrime(num))
            cout << num << " is a prime number.\n";
        else
            cout << num << " is NOT a prime number.\n";
    }
    else if (choice == 2) {
        int limit;
        cout << "Generate primes up to: ";
        cin >> limit;
        vector<int> primes = generatePrimes(limit);
        cout << "Prime numbers up to " << limit << ": ";
        for (int p : primes) {
            cout << p << " ";
        }
        cout << "\n";
    }
    else {
        cout << "Invalid choice.\n";
    }

    return 0;
}