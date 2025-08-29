# Bulk Sample File Generator

Generates JSON test files with random data for all DataProvider implementations.

## Usage

```java
BulkSampleFileGenerator generator = new BulkSampleFileGenerator();
Collection<DataProvider> providers = getYourDataProviders();
generator.createBulkTestFiles("output-directory", providers);
```

## What It Does

- Discovers all DataProvider implementations
- Generates 10 sample records per provider
- Creates JSON files named `{ProviderName}.json`
- Supports: Integer, String, Double, Short, Long, Float, Boolean types

## Example Output

For a provider with columns `id` (Integer), `name` (String):

```json
[
  {
    "id": "42",
    "name": "RandomString123"
  },
  {
    "id": "87", 
    "name": "AnotherString456"
  }
]
```

## Console Output

```
Found 2 DataProvider implementations:
- Generating sample file for: UserProvider
  Column specifications for UserProvider:
    - id: Integer
    - name: String
  ✓ Generated: UserProvider.json
Bulk generation completed! Files saved to: output-directory
```

## Notes

- Unsupported types default to `"unknown_type_{TypeName}"`
- Creates output directories automatically
- Continues processing if individual files fail