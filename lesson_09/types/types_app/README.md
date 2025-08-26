# types_app

This app helps you learn about different data types and how to use data providers.

## How to Make Sample Data Files

You can run this app in a special way to **create sample JSON files automatically** for each `DataProvider` in the project. This is handy for quickly making test data based on the kinds of data you've set up.

### How to Run the Creator

To make sample files, go to the `lesson_09/types` folder in your terminal. Then, type this command and press Enter:

```bash
./gradlew bootRun --args='--generate-samples'
```

### What Happens?

1.  The app will start.

2.  It will find all the parts of the code that act as `DataProvider`s (like `JoneeDataFileProvider.java`).

3.  For each `DataProvider`, it will make 5 example records.

4.  Each record will be a simple JSON item, with column names and random values that match the data type set in that `DataProvider`.

5.  The new sample files (for example, `joneeData.json` if your provider's name is "joneeData") will be saved in the `types_app/src/main/resources/data/generated_samples/` folder.
