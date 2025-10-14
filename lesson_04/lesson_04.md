## Java implementation
```java
public class EvenCheck {
    public static void main(String[] args) {
        System.out.println(isEven(4)); // true
        System.out.println(isEven(7)); // false
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
```
## Typescript implementation
```typescript
    function isEven(number: number): boolean {
    return number % 2 === 0;
}

// Example usage:
console.log(isEven(4)); // Output: true
console.log(isEven(7)); // Output: false
```
## Explanation

The Java implementation is a compiled language. .java files are compiled into .class files
TypeScript implementation is a transpiled language. .ts files get transpiled into JavaScript.

## Differences

1. **Syntax**: 
- **Java** needs a `class` and a `main` method as the entry point.  
- **TypeScript** just runs functions and code top-to-bottom, no class required.

2. **Type Coercion**:
- **Java**: `int number = 29;` → types are mandatory and fixed.  
- **TypeScript**: `let number: number = 29;` → types are optional but recommended.

3. **Function Calls**:
- **Java**: Everything lives inside a class, classed based.
- **Typescript**: No need for a class or main method unless you want one, function based.







