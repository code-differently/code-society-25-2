plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "lesson2-quiz-app"

// Include the instructional library
includeBuild("../../lib/java/codedifferently-instructional")
