# Lesson 09 Stretch
## Running the code
To run and test the code for `--bulk` here are the following terminal commands:

```bash
cd lesson_09/types
./gradlew bootRun --args="--bulk" #Creates a json file for every non abstract Provider java class
./gradlew spotlessApply
./gradlew check #Runs tests