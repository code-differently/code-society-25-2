# Lesson 9 - Spring Boot Data Generator

This project is a **Spring Boot application** that demonstrates how to use dependency injection to work with multiple `DataProvider` implementations and generate JSON test files.  
It uses a `SampleFileGenerator` to output files into the `src/main/resources/data` directory.

## 📌 Features

- **Spring Boot with Dependency Injection**:  
  All beans that implement `DataProvider` are automatically injected into the application.  

- **Bulk and Single File Generation**:  
  - Run in bulk mode to generate files for all available providers.  
  - Run with a single provider argument to generate just that file.  

- **Clean Startup**:  
  Spring Boot banner is disabled for a cleaner CLI output.  

## ▶️ Running the Application

Make sure you are inside the project root and build the project with:

```bash
./gradlew bootRun --args="--bulk"
```

## 📜 Example Output

```bash
==============================
       Running Bulk Mode      
==============================

Generated file for provider: TyranRiceJr
Generated file for provider: ExampleProvider

============ Done =============
```