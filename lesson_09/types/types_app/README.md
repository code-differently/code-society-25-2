# Types App - Bulk Generator Mode

This application generates sample JSON data files for DataProvider implementations with a special **bulk generation mode** that creates files for all providers at once.

## 🚀 Quick Start - Bulk Generation

Generate sample files for **all** DataProvider implementations in one command:

```bash
cd /workspaces/code-society-25-2/lesson_09/types
./gradlew :types_app:bootRun --args="--bulk"
```

## 📊 What Bulk Mode Does

- **Automatically discovers** all `@Service` annotated DataProvider implementations
- **Generates a JSON file** for each provider using their `getProviderName()` value
- **Creates type-accurate data** that matches each provider's `getColumnTypeByName()` specifications
- **Ensures test compatibility** - generated files will pass all DataProvider tests

## 💻 Example Output

```
==============================
       Running Bulk Mode      
==============================

Generated file for provider: anthonymays
Generated file for provider: kerryferguson

============ Done =============
```

## 📁 Generated Files

Files are created in: `src/main/resources/data/`

- `anthonymays.json` - Data matching AnthonyMaysProvider types
- `kerryferguson.json` - Data matching KerryFergusonProvider types
- *(Additional files for any new DataProvider implementations)*

## ✅ Benefits of Bulk Mode

- **Saves Time**: Generate all files with one command instead of running individual commands
- **Ensures Accuracy**: Data types automatically match each provider's requirements
- **Test Ready**: Generated files pass all DataProvider validation tests
- **Scalable**: Automatically includes new DataProvider implementations

## 🔧 Alternative: Single Provider Mode

To generate a file for just one provider:

```bash
./gradlew :types_app:bootRun --args="providername"
```

Example:
```bash
./gradlew :types_app:bootRun --args="kerryferguson"
```

## 🎯 Perfect For

- **Initial Setup**: Generate all sample files when setting up the project
- **Testing**: Quickly regenerate test data for all providers
- **Development**: Refresh data files after adding new DataProvider implementations
- **CI/CD**: Automated data generation in build pipelines

---

*Need help? Check the full documentation in the parent directory or run the tests with `./gradlew :types_app:test`*

## 🧪 Testing

### Running Tests

Execute all tests for the types application:

```bash
cd /workspaces/code-society-25-2/lesson_09/types
./gradlew test
```

### Test Suites

#### Core Functionality Tests (`Lesson9Test.java`)
- **DataProvider Configuration**: Validates that each provider is properly configured
- **Data Parsing**: Tests type conversion and data processing
- **File Loading**: Ensures JSON files can be loaded and parsed correctly
- **Provider Uniqueness**: Verifies that provider names are unique

#### Bulk Mode Tests (`BulkModeTest.java`)
- **Multi-Provider Processing**: Tests bulk generation with multiple DataProviders
- **Case-Insensitive Arguments**: Verifies `--bulk`, `--BULK`, `--BuLk` all work
- **Edge Case Handling**: Tests with empty provider lists and no arguments
- **File Generation Integration**: Tests actual file creation using temporary directories
- **Mode Separation**: Ensures bulk and single-provider modes work independently

### Test Coverage Details

The test suite covers:
- ✅ **Argument Parsing**: All command-line argument combinations
- ✅ **File Generation**: Both bulk and single-provider file creation
- ✅ **Error Handling**: Graceful handling of edge cases
- ✅ **Output Verification**: Console messages and user feedback
- ✅ **Integration**: End-to-end workflow testing
- ✅ **Data Type Accuracy**: Generated data matches provider specifications

### Quality Assurance

Run complete quality checks:
```bash
./gradlew check          # Tests + formatting + linting
./gradlew spotlessApply  # Apply code formatting
./gradlew spotlessCheck  # Verify formatting compliance
```

---

*Need help? Check the full documentation in the parent directory or run the tests with `./gradlew :types_app:test`*
