# Types App

This Spring Boot application is used to generate sample data files for data type exercises.

## Usage

### Single Provider Mode

To generate a sample data file for a single provider:

```bash
./gradlew bootRun --args="yourprovidername"
```

This will create a JSON file in `src/main/resources/data/` with the name `yourprovidername.json`.

### Bulk Generation Mode

To generate sample data files for all available `DataProvider` implementations at once:

```bash
./gradlew bootRun --args="--bulk"
```

This will:
1. Find all `DataProvider` implementations in the codebase (all classes extending `DataProvider` and annotated with `@Service`)
2. Generate a sample JSON file for each provider in `src/main/resources/data/`
3. Name each file according to the provider's name (e.g., `anthonymays.json`, `benjaminscottprovider.json`, etc.)

## Generated Files

The generated JSON files contain randomly generated data that matches the data types specified in each provider's `getColumnTypeByName()` implementation. The files are placed in:

```
src/main/resources/data/
```

Each JSON file contains 10 rows of data with 7 columns, where each column's data matches the specified data type.