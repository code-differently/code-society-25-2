package com.codedifferently.lesson9;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.codedifferently.lesson9.generator.SampleFileGenerator;

@Configuration
@SpringBootApplication(scanBasePackages = "com.codedifferently")
public class Lesson9 implements CommandLineRunner {

  public static void main(String[] args) {
    var application = new SpringApplication(Lesson9.class);
    application.run(args);
  }

  @Autowired
  private List<DataProvider> dataProviders;

  @Override
  public void run(String... args) throws Exception {
    if (args.length == 0) {
      return;
    }

    String path = getDataPath();
    var fileGenerator = new SampleFileGenerator();

    if (args[0].equals("--bulk")) {
      // Bulk generation mode - generate for all providers
      for (DataProvider provider : dataProviders) {
        fileGenerator.createTestFile(path, provider.getProviderName());
        System.out.println("Generated file for provider: " + provider.getProviderName());
      }
    } else {
      // Single provider mode
      var providerName = args[0];
      if (providerName == null) {
        throw new IllegalArgumentException("Provider name is required");
      }
      fileGenerator.createTestFile(path, providerName);
    }
  }

  private static String getDataPath() {
    String[] pathParts = {
      Paths.get("").toAbsolutePath().toString(), "src", "main", "resources", "data"
    };
    return String.join(File.separator, pathParts);
  }
}
