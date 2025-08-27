# types_app

This app generates sample JSON data files for each `DataProvider` implementation.

Usage

- Generate a single provider's sample file (existing behavior):

```bash
cd lesson_09/types
./gradlew bootRun --args="yourprovidername"
```

Replace `yourprovidername` with the provider name (the command will create `yourprovidername.json` in `src/main/resources/data`).

- Bulk-generate files for all `DataProvider` implementations (new):

```bash
cd lesson_09/types
./gradlew bootRun --args="--bulk"
```

This will create one JSON file per `DataProvider` in `types_app/src/main/resources/data/`.

Notes

- Files are written to `types_app/src/main/resources/data/`.
- Run the formatter and tests after making provider changes:

```bash
./gradlew spotlessApply
./gradlew check
```
