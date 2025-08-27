# Types App - Data Types Exercise

This application demonstrates data type identification and sample data generation for Lesson 09.

## Overview

The Types App provides two main functionalities:
1. Generate sample data files for individual providers
2. Bulk generate sample data files for all registered `DataProvider` implementations

## Prerequisites

- Java 17 or higher
- Gradle (included via Gradle Wrapper)

## Running the Application

### Standard Mode - Single Provider

Generate a sample data file for a specific provider:

```bash
cd lesson_09/types
./gradlew bootRun --args="yourprovidername"
```

This will create a JSON file in `src/main/resources/data/yourprovidername.json` with 10 rows of sample data.

**Example:**
```bash
./gradlew bootRun --args="devynbenson"
```

### Stretch Assignment - Bulk Generator Mode

Generate sample data files for **all** registered `DataProvider` implementations:

```bash
cd lesson_09/types
./gradlew bootRun --args="-bulk"
```

This bulk mode will:
- Automatically discover all registered `DataProvider` implementations
- Generate a unique JSON file for each provider
- Create files in `src/main/resources/data/` directory
- Each file contains 10 rows of sample data with appropriate data types

**What happens in bulk mode:**
1. The app scans for all `DataProvider` beans registered in the Spring context
2. For each provider, it generates sample data based on the provider's column type definitions
3. Creates individual JSON files named after each provider (e.g., `devynbenson.json`, `anthonymays.json`, etc.)

## Output Format

Generated files are in JSON format with the following structure:

```json
[
  {
    "column1": "sample_value_1",
    "column2": "sample_value_2",
    "column3": "sample_value_3",
    ...
  },
  // ... 9 more rows
]
```

## Data Types Supported

The bulk generator supports the following Java data types:
- `Integer` - Whole numbers
- `Double` - Decimal numbers  
- `String` - Text values
- `Boolean` - True/false values
- `Short` - Small integers
- `Long` - Large integers
- `Float` - Single-precision decimals

## Implementation Details

### Bulk Generator Class

The bulk functionality is implemented in `DevynBensonBulkGenerator.java`, which:
- Uses Spring's dependency injection to access all `DataProvider` beans
- Maps each provider's column types to appropriate value generators
- Generates realistic sample data for each column type
- Outputs properly formatted JSON files

### Usage in Main Application

The main `Lesson9.java` class detects the `-bulk` flag and switches to bulk generation mode:

```java
if("-bulk".equals(providerName)) {
    var path = getDataPath();
    var fileGenerator = new DevynBensonBulkGenerator();
    fileGenerator.createTestFileForEveryProvider(path, dataProviders);
    return;
}
```

## Testing and Validation

After running the application, validate your implementation:

```bash
# Format code
./gradlew spotlessApply

# Run tests
./gradlew check
```

## File Locations

- **Generated Data Files:** `src/main/resources/data/`
- **DataProvider Implementations:** `src/main/java/com/codedifferently/lesson9/dataprovider/`
- **Bulk Generator:** `src/main/java/com/codedifferently/lesson9/generator/DevynBensonBulkGenerator.java`

## Example Workflow

1. Create your `DataProvider` implementation
2. Run bulk generator to see sample data for all providers:
   ```bash
   ./gradlew bootRun --args="-bulk"
   ```
3. Examine the generated JSON file for your provider
4. Verify the data types match your expectations
5. Run tests to validate implementation
