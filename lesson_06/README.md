# Lesson 06: Statements and Variables ([Slides](https://code-differently.github.io/code-society-25-2/slides/#/lesson_06))

## Pre-work

Please review the following resources before lecture:

* [Typescript for Beginners - Starter Lesson (Video)](https://www.youtube.com/watch?v=MOO5vrtTUTE&list=PL0Zuz27SZ-6NS8GXt5nPrcYpust89zq_b&index=1)

## Homework

- [ ] Complete the [Expression Calculator](#expression-calculator) exercise.
- [ ] Read article entitled [3 Questions That Will Make You A Phenomenal Rubber Duck][article-link]
- [ ] Do pre-work for [lesson 07](/lesson_07/).

### Expression Calculator

For this assignment, you will need to implement the functions and logic required to calculate a mathematical expression. After implementing the `add`, `divide`, and `multiply` functions, you will combine these functions to compute the final result.

1. Update the [.env.test][env-file] file to configure the correct homework version.
```bash
# Example config for students assigned to homework "D".
HW_VERSION=D
```
2. Run the program to determine the expression you must implement.
```bash
npm install
npm run compile
npm start
```
1. Update the code in the [expression_calculator.ts][calculator-file] file.
2. To check your work, you can run the application using the first command below and run the tests using the second one.
```bash
npm start
```
1. As usual, make sure that you format your code and run the check command before creating your pull request.
```bash
npm run check
```
1. You must only submit changes to the `expression_calculator.ts` and `.env.test` files to receive full credit.

### Stretch Assignment

Implement a function that validates whether a given alphabetic abbreviation matches a word using a special encoding system.

In this system, numbers in abbreviations are replaced by their corresponding alphabet letters:
- 'a' represents 1, 'b' represents 2, 'c' represents 3, ..., 'z' represents 26
- The abbreviation follows the same rules as standard abbreviations but uses letters instead of digits
- Letters cannot have leading zeros (e.g., no 'aa' for 01)
- Adjacent abbreviated substrings are not allowed
- Empty substrings cannot be abbreviated

**Function Signature:**
```typescript
function isValidAlphaAbbreviation(word: string, abbr: string): boolean
```

**Examples:**

Example 1:
```
Input: word = "internationalization", abbr = "imzdn"
Output: true
Explanation: 
- "internationalization" can be abbreviated as "i m z d n"
- i + (m=13 chars) + z + (d=4 chars) + n
- i + nternationaliza + z + atio + n = "internationalization"
```

Example 2:
```
Input: word = "substitution", abbr = "sjn"
Output: true  
Explanation:
- s + (j=10 chars) + n
- s + ubstitutio + n = "substitution"
```

**Invalid Examples:**
```
Input: word = "test", abbr = "ab"
Output: false
Explanation: Adjacent letters 'a' and 'b' would represent adjacent abbreviated substrings
```

**Constraints:**
- 1 ≤ word.length ≤ 25
- 1 ≤ abbr.length ≤ 15
- word consists of only lowercase English letters
- abbr consists of only lowercase English letters
- Letters representing numbers follow a=1, b=2, ..., z=26

[article-link]: https://blog.danslimmon.com/2024/01/18/3-questions-that-will-make-you-a-phenomenal-rubber-duck/
[calculator-file]: ./expression/src/expression_calculator.ts
[env-file]: ./expression/.env.test