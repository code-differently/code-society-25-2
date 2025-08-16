## JavaScript Implementation

```javascript
function isPrime(number) {
    if (number <= 1) return false;
    if (number === 2) return true;
    if (number % 2 === 0) return false;

     for (let i = 3; i <= Math.sqrt(number); i += 2) {
        if (number % i === 0) return false;
     }
     return true; 
}

// Example usage:
console.log(isPrime(17)); // Output: true
console.log(isPrime(25)); // Output: false
```

## TypeScript implementation

```typescript
function isPrime(number: number): boolean {
    if (number <= 1) {
        return false;
    }

    if (number <= 3) {
        return true;
    }
    
    if (number % 2 === 0 || number % 3 === 0) {
        return false;
    }
    
    for (let i = 5; i * i <= number; i += 6) {
        if (number % i === 0 || number % (i + 2) === 0) {
            return false;
        }
    }
    
    return true;
}

// Example usage:
console.log(isPrime(17)); // Output: true
console.log(isPrime(25)); // Output: false
```

## Differences Between TypeScript and JavaScript

### TypeScript Features:
- **Type Safety**: Uses `number` and `boolean` type annotations
- **Compile-time Error Checking**: Catches type errors before runtime
- **Better Code Documentation**: Types make the function signature clearer
- **Advanced Algorithm**: Uses 6k±1 optimization for better performance

### JavaScript Features:
- **Dynamic Typing**: No type declarations needed
- **Simpler Syntax**: Less verbose, easier to write quickly
- **Runtime Flexibility**: Types can change during execution
- **Basic Algorithm**: Uses straightforward Math.sqrt() optimization

### Key Differences:
Both solve the same problem but TypeScript provides more safety and documentation through its type system, while JavaScript offers simplicity and flexibility. The TypeScript version also uses a more mathematically advanced algorithm that performs better on larger numbers.

## Similarities Between TypeScript and JavaScript
- **Conditional statements**: Both use identical conditional statements (if, else), loops (for), and operators (%, ===, <=)
- **Function Structure**: Both define functions with parameters and return values
- **Variable Declarations**: Both use let for variable declarations
- **Mathematical Operations**: Both perform the same modulo (%) and comparison operations