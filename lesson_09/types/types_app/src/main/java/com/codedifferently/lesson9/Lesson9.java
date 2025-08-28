package com.codedifferently.lesson9;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.codedifferently.lesson9.generator.SampleFileGenerator;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication(scanBasePackages = "com.codedifferently")
public class Lesson9 implements CommandLineRunner {

  private final List<DataProvider> dataProviders;

  public Lesson9(List<DataProvider> dataProviders) {
    this.dataProviders = dataProviders;
  }

  public static void main(String[] args) {
    SpringApplication.run(Lesson9.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    if (args == null || args.length == 0) {
      return;
    }

    String providerName = args[0];
    if (providerName == null) {
      throw new IllegalArgumentException("Provider name is required");
    }

    String path = getDataPath();
    SampleFileGenerator fileGenerator = new SampleFileGenerator();

    if ("--bulk".equals(providerName)) {
      if (dataProviders == null || dataProviders.isEmpty()) {
        System.out.println("No DataProvider beans found to generate files for.");
        return;
      }
      for (DataProvider provider : dataProviders) {
        fileGenerator.createTestFile(path, provider.getProviderName());
      }
      return;
    }

    fileGenerator.createTestFile(path, providerName);
  }

  private static String getDataPath() {
    String[] pathParts = {
      Paths.get("").toAbsolutePath().toString(), "src", "main", "resources", "data"
    };
    return String.join(File.separator, pathParts);
  }
}
