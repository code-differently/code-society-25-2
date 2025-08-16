## Python implementation

```python
def is_prime(number):
    # Numbers less than 2 are not prime
    if number < 2:
        return False
    
    # Check for divisibility from 2 to square root of number
    for i in range(2, int(number ** 0.5) + 1):
        if number % i == 0:
            return False
    
    return True

# Example usage:
print(is_prime(4))  # Output: False
print(is_prime(7))  # Output: True
print(is_prime(13)) # Output: True
```

## C++ implementation
```C++
bool isPrime(int number) {
    // Numbers less than 2 are not prime
    if (number < 2) {
        return false;
    }

    // Check for divisibility from 2 to square root of number
    for (int i = 2; i <= sqrt(number); i++) {
        if (number % i == 0) {
            return false;
        }
    }
    
    return true;
}

// Example usage:
// #include <iostream>
// #include <cmath>
int main() {
    std::cout << std::boolalpha;
    std::cout << isPrime(4) << std::endl;  // Output: false
    std::cout << isPrime(7) << std::endl;  // Output: true
    std::cout << isPrime(13) << std::endl; // Output: true
    return 0;
}
```

## Explanation
In Python, a function called `is_prime` takes a parameter called number. It returns False if the number is less than 2. The second way it would return False is by dividing the number by every single number starting from two until its square root, and if at any time, the number has a remainder that is equal to zero, it shall return False. It will return True if there are no remainders equal to 0. Then it prints out whether the number is True or False.

In C++ the function called `isPrime` will return false if the parameter called number is less than 2 or if number is divided by any number from 2 to its square root have a remainder that is equal to zero. It will return true if there are no remainders equal to 0. It will then output if the result is true or false.

## Differences
1. **Syntax**: 
   - In C++, when you define a function, you must declare what is being returned in the function (`bool`) and you have to declare what type the parameter variable is (`int`) (example: `int number`). In Python, all you need to do is give the function a name and don't have to declare the parameter for `number`.
   - Python uses indentations to define code blocks while C++ uses braces `{}`. 
   - Python uses `True` and `False` with the first letters capitalized while C++ uses `true` and `false` with the entire words in lowercase.

2. **Function calls**:
   - Python can call a function and print it using the print function `print(is_prime(4))` and this will print the result. 
   - C++ uses `std::cout << std::boolalpha;` to display boolean values as words. Normally it would be 1 for true and 0 for false but this will display true or false instead.