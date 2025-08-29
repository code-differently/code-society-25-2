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
  private final List<DataProvider> providers;

  public Lesson9(List<DataProvider> providers) {
    this.providers = providers;
  }

  public static void main(String[] args) {
    var application = new SpringApplication(Lesson9.class);
    application.run(args);
  }

  @Override
  public void run(String... args) throws Exception {
    if (args.length == 0) {
      return;
    }

    var option = args[0];
    if (option == null) {
      throw new IllegalArgumentException("Provider name is required");
    }

    // If the option is "--bulk", generate data files for all providers.
    if (option.equals("--bulk")) {
      for (DataProvider provider : providers) {
        String providerName = provider.getProviderName();
        String path = getDataPath();
        var fileGenerator = new SampleFileGenerator();
        fileGenerator.createProviderFile(path, providerName, provider);
      }
      return;
    }

    String path = getDataPath();
    var fileGenerator = new SampleFileGenerator();
    fileGenerator.createTestFile(path, option);
  }

  private static String getDataPath() {
    String[] pathParts = {
      Paths.get("").toAbsolutePath().toString(), "src", "main", "resources", "data"
    };
    return String.join(File.separator, pathParts);
  }
}
