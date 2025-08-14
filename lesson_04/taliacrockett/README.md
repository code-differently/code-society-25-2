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

## TypeScript implementation

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


