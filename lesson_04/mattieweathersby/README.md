## Typescript Implementation
```typescript
function checkPrime(num: number): boolean{
    if (num < 2) {
        return false;
    }
    for (let i = 2; i < num; i++) {
        if (num % i === 0) {
            return false;
        }
    }
    return true;
```

## Javascript Implementation
```javescript
function checkPrime(num) {
    if (num < 2) {
        return false;
    }
    for (let i = 2; num; i++) {
        if (num % i === 0) {
            return false;
        }
    }
    return true;
}
```

## Explanation
Typescript implementation uses num that takes a single argument `number`. It returns true if the number is even (i.e., when the remainder of the division of the number by 2 is zero), otherwise, it returns `false`. Uses console.log to call functions and printing to console/output.

Example Usage:

console.log(checkPrime(2)); // true

console.log(checkPrime(4)); // false

Javascript implementation uses the same function `num` that takes a single argument `number`. It returns true using the same logic as typescript and `false` otherwise. ses console.log to call functions and printing to console/output.

Example Usage:

console.log(checkPrime(2)); // true

console.log(checkPrime(4)); // false

### Differences
1. **Syntax**:
While typescript requires annotations, javascript is a bit different because you don't need to use `:number` or `:boolean`