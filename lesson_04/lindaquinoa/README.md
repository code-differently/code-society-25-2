## Java implementation

```java
public class PrimeCheck {
    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int number = 29;
        System.out.println(number + " is prime? " + isPrime(number));
    }
}
```

## TypeScript implementation

```TypeScript
function isPrime(n: number): boolean {
    if (n < 2) return false;
    for (let i = 2; i <= Math.sqrt(n); i++) {
        if (n % i === 0) return false;
    }
    return true;
}

const number = 29;
console.log(`${number} is prime? ${isPrime(number)}`);
```

## Explanation

The Java implementation uses a function named 'isPrime' that is defined inside a 'class'. It takes a single integer as input and returns 'true' if the number is prime (i.e., greater than 1 and not divisible by any number other than 1 and itself). Otherwise, it returns 'false'.

The TypeScript implementation also defines a function named 'isPrime' that accepts a number as input. It uses the same mathematical logic as the Java function, returning 'true' when the number is prime and 'false' otherwise.

### Differences

1. **Syntax**:

    -In Java, functions must be defined inside a class, and you must also create a 'main' method to run the program.
    -In TypeScript, functions can be written directly without needing a 'class' or a special entry point.

2. **Types**:
    -Java requires explicit type declarations ('int' for integers, 'boolean' for return values).
    -TypeScript also uses types, but the syntax is lighter (e.g., 'n: number' instead of 'int n').

3. **Structure**:
     -Java enforces more boilerplate code, such as the 'public class' and 'public static' keywords.
    -TypeScript feels closer to JavaScript and allows quicker setup with just the function and a 'console.log' statement.