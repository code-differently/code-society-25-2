package com.codedifferently.lesson9;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.codedifferently.lesson9.generator.SampleFileGenerator;

@Configuration
@SpringBootApplication(scanBasePackages = "com.codedifferently")
public class Lesson9 implements CommandLineRunner {

  private final List<DataProvider> dataProviders;

  // Spring injects all beans of type DataProvider here
  @Autowired
  public Lesson9(List<DataProvider> dataProviders) {
    this.dataProviders = dataProviders;
  }

  public static void main(String[] args) {
    var application = new SpringApplication(Lesson9.class);
    // This Turns off the Spring Boot startup logs to make the output cleaner
    application.setBannerMode(Banner.Mode.OFF);
    application.run(args);
  }

  /**
 * Main execution method for the application.
 *
 * @param args - Command-line arguments.
 *    - none → exits quietly
 *    - "--bulk" → generates JSON files for all providers in bulk mode
 *    - "<providerName>" → generates a JSON file for the specified provider
 * @throws IllegalArgumentException If no matching provider is found.
 */
  public void run(String... args) throws Exception {
    if (args.length == 0) {
      return;
    }

    if (args[0].equalsIgnoreCase("--bulk")) {
      System.out.println("\n==============================");
      System.out.println("       Running Bulk Mode      ");
      System.out.println("==============================\n");

      for (DataProvider provider : dataProviders) {
        generateFileFor(provider);
      }

      System.out.println("\n============ Done =============");
      return;
    }

    var providerName = args[0];
    if (providerName == null) {
      throw new IllegalArgumentException("Provider name is required");
    }

    String path = getDataPath();
    var fileGenerator = new SampleFileGenerator();
    DataProvider provider =
        dataProviders.stream()
            .filter(p -> p.getProviderName().equalsIgnoreCase(providerName))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException("No provider found with name: " + providerName));
    fileGenerator.createTestFile(path, provider, "single");
  }

  private static String getDataPath() {
    String[] pathParts = {
      Paths.get("").toAbsolutePath().toString(), "src", "main", "resources", "data"
    };
    return String.join(File.separator, pathParts);
  }

  /**
 * Generates a JSON file for a single provider in bulk mode.
 *
 * @param provider - The DataProvider to generate data for.
 * @returns void
 */
  private void generateFileFor(DataProvider provider) throws Exception {
    String path = getDataPath();
    var fileGenerator = new SampleFileGenerator();

    String providerName = provider.getClass().getSimpleName().replace("Provider", "");
    fileGenerator.createTestFile(path, provider, "bulk");

    System.out.println("Generated file for provider: " + providerName);
  }
}
