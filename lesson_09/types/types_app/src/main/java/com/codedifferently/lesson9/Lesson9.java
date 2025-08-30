package com.codedifferently.lesson9;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.codedifferently.lesson9.generator.SampleFileGenerator;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

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
    // This removes the Spring Boot startup logs to make the output cleaner
    application.setBannerMode(Banner.Mode.OFF);
    application.run(args);
  }

  public void run(String... args) throws Exception {
    if (args.length == 0) {
      return;
    }
    if (args[0].equalsIgnoreCase("--bulk")) {
      System.out.println("\n==============================");
      System.out.println("       Running Bulk Mode      ");
      System.out.println("==============================\n");

      String path = getDataPath();
      var fileGenerator = new SampleFileGenerator();

      for (DataProvider provider : dataProviders) {
        fileGenerator.generateSampleFileForProvider(path, provider);
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
    fileGenerator.createTestFile(path, providerName);
  }

  private static String getDataPath() {
    String[] pathParts = {
      Paths.get("").toAbsolutePath().toString(), "src", "main", "resources", "data"
    };
    return String.join(File.separator, pathParts);
  }
}
