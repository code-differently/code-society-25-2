plugins {
    application
    java
}

group = "com.codedifferently"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.codedifferently.instructional:instructional-lib")
    implementation("org.yaml:snakeyaml:2.0")
    implementation("at.favre.lib:bcrypt:0.9.0")
    
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    mainClass.set("com.codedifferently.lesson02.Lesson2")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
