# Lesson 11: Data Structures: Strings, Array, ArrayList ([Slides](https://code-differently.github.io/code-society-25-2/slides/#/lesson_11))

## Pre-work

Please review the following resources before lecture:

* [An Overview of Arrays and Memory (Data Structures & Algorithms #2) (Video)](https://www.youtube.com/watch?v=pmN9ExDf3yQ)
* [Java Data Structures Tutorial (Video)](https://www.youtube.com/watch?v=8MmMm2-kJV8&t=206s) - **NOTE**: Just the chapter on Arrays, between 3:26 and 17:37

## Homework

- [ ] Complete the [Arrays and String Exercise](#arrays-and-string-exercise).
- [ ] Do pre-work for [lesson 12](/lesson_12/).

### Arrays and String Exercise

For this exercise, you will implement two algorithms that work with arrays and strings.

#### Java Version

Navigate to the Java project and implement the required methods:

```bash
cd lesson_11/arrays_java
./gradlew test
```

Implement the following methods in [Lesson11.java](./arrays_java/arrays_app/src/main/java/com/codedifferently/lesson11/Lesson11.java):

1. **getConcatenation** - [LeetCode 1929: Concatenation of Array](https://leetcode.com/problems/concatenation-of-array)
   - Given an integer array `nums`, return a new array that concatenates `nums` with itself.

2. **findWordsContaining** - [LeetCode 2942: Find Words Containing Character](https://leetcode.com/problems/find-words-containing-character/)
   - Given an array of strings `words` and a character `x`, return an array of indices representing the words that contain the character `x`.

#### TypeScript Version (Extra Credit)

Navigate to the TypeScript project and implement the same methods:

```bash
cd lesson_11/arrays_ts
npm install
npm test
```

Implement the following functions in [lesson11.ts](./arrays_ts/src/lesson11.ts):

1. **getConcatenation** - Same as Java version but in TypeScript
2. **findWordsContaining** - Same as Java version but in TypeScript

Before submitting your work, make sure all tests pass and your code follows the coding standards:

```bash
# For Java
./gradlew check
./gradlew spotlessApply

# For TypeScript
npm run check
```
