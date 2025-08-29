# JSON File Generator

This project extends the Lesson 09 Data Types exercise by adding **bulk sample generation**.  
Instead of generating a single JSON file for one provider, the app can generate sample files for **all DataProvider implementations** at once.

## Features
- Reads all registered `DataProvider` classes automatically
- Maps Java types (`Integer`, `String`, `Boolean`, etc.) to corresponding value generators
- Produces JSON files that match each provider’s schema
- Bulk generation mode via CLI flag (`all`)

## Technologies Used
- Java 21
- Spring Boot
- Gradle
- JSON

## Getting Started

### Prerequisites
- Java 21+
- Gradle (or use the included wrapper)
 
### Installation
Clone the repository and navigate to the project:
```bash
cd lesson_09/types
```
### Usage
```
./gradlew bootRun --args="all"
```
generated files will be located in 
- types_app/src/main/resources/data/
#### Example output
```
{
    "column1": "false",
    "column2": "807994649",
    "column3": "3.6620309558304355E307",
    "column4": "2044",
    "column5": "5072670541652295680",
    "column6": "0zpmb9i5",
    "column7": "2.192224E38"
  }
```