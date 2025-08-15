## Explanation

I know we were instruected to only implement 2 languages but I decided to do 3 to practice writing in these languages as much as I could. Each version of the isPrime function follows the same logical structure, broken down into small helper functions for clarity and maintainability. The logic checks if a number is less than or equal to 1, whether it's 2 or 3, if it's divisible by 2 or 3, and finally uses the 6k ± 1 optimization to check for other factors efficiently.

## Differences for programs

Function Declarations & Syntax:

- Python uses def with colons and indentation.

- JavaScript and TypeScript use function and curly braces.

- TypeScript adds type annotations (e.g., n: number, : boolean).

## Type Safety:

- Python is dynamically typed but can include type hints.

- JavaScript is fully dynamic and prone to runtime type errors.

- TypeScript offers compile-time type checking for more robust code.

##Booleans & Print Statements:

- Python uses True/False and print()

- JS/TS use true/false and console.log() (uses capitol letters)

## Refactoring Practice:

- All three versions refactor logic into smaller single-responsibility functions

- This improves readability and testability

Tooling Differences:

- TypeScript requires a compiler (tsc) to convert code into JavaScript before execution

- Python and JavaScript can run directly via interpreters (e.g., python, node)


## Differences for tests


## Test Frameworks & Syntax

- Python (unittest): Uses the built-in unittest framework

- JavaScript (Node assert): No framework; uses Node’s built-in assert with a manual runTests() function.

- TypeScript (compiled JS): Compiled output JS that uses a custom assertEqual helper and a manual runTestsTS(); not using a TS-aware test runner in this artifact.
  
- All 3 tests used some sort of assert helper


## Output

- Python prints a structured summary (dots/letters, failures)
- JavaScript and TypeScript Prints a message of completion or failure
