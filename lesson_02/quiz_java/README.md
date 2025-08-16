# Lesson 02 Quiz - Java Port

This is a Java port of the Lesson 02 quiz application, originally written in TypeScript. It demonstrates the same quiz functionality using the `codedifferently-instructional` Java library.

## Features

- 11 multiple choice questions covering:
  - Version control and Git concepts
  - IDE and development environment topics
  - Terminal/command line basics
  - Mac-specific shortcuts

## Prerequisites

- Java 21 or later
- Gradle (included via wrapper)

## Building and Running

### Build the project
```bash
./gradlew build
```

### Run the quiz
```bash
./gradlew run
```

### Run tests
```bash
./gradlew test
```

## Project Structure

- `app/src/main/java/quiz_java/Lesson2.java` - Main quiz implementation
- `app/src/main/java/quiz_java/App.java` - Application entry point
- `app/src/test/java/quiz_java/Lesson2Test.java` - Unit tests
- `app/build.gradle.kts` - Build configuration
- `settings.gradle.kts` - Multi-project settings

## Dependencies

This project uses the `codedifferently-instructional` library located at `../../lib/java/codedifferently-instructional/instructional-lib` which provides:

- `AnswerChoice` enum for multiple choice options
- `MultipleChoiceQuizQuestion` class for question representation
- `QuizPrinter` class for displaying the quiz
- `QuizQuestion` base class

## Quiz Questions

The Java implementation contains the same 11 questions as the TypeScript version:

1. **Question 0**: Version control purpose → Answer: B (To keep a record of changes over time)
2. **Question 1**: Git fork definition → Answer: A (A duplicate copy of a repository that you own and modify)
3. **Question 2**: Non-Git workflow activity → Answer: D (Write code directly in GitHub)
4. **Question 3**: Branch merging command → Answer: B (git merge)
5. **Question 4**: Class IDE → Answer: D (VS Code)
6. **Question 5**: VS Code environment feature → Answer: C (Dev Containers)
7. **Question 6**: Non-IDE purpose → Answer: C (Playing music)
8. **Question 7**: Directory listing command → Answer: B (ls)
9. **Question 8**: Directory change command → Answer: C (cd)
10. **Question 9**: chmod command purpose → Answer: A (Change file or directory permissions)
11. **Question 10**: Mac terminal shortcut → Answer: B (⌘ + Spacebar, then type "terminal")

## Notes

This Java port maintains the same structure and answers as the original TypeScript quiz while leveraging the Java ecosystem and demonstrating cross-language compatibility.
