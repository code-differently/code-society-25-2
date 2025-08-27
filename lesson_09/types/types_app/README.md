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
