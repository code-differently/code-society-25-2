package com.codedifferently.lesson9;
import com.codedifferently.lesson9.generator.SampleFileGenerator;
import com.codedifferently.lesson9.generator.DevynBensonBulkGenerator;
import java.io.File;
import java.nio.file.Paths;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@SpringBootApplication(scanBasePackages = "com.codedifferently")
public class Lesson9 implements CommandLineRunner {

  @Autowired
  private List<DataProvider> dataProviders;
  public static void main(String[] args) {
    var application = new SpringApplication(Lesson9.class);
    application.run(args);
  }

  public void run(String... args) throws Exception {
    if (args.length == 0) {
      return;
    }

    var providerName = args[0];
    if (providerName == null) {
      throw new IllegalArgumentException("Provider name is required");
    }

    if("-bulk".equals(providerName)) {
      var path = getDataPath();
      var fileGenerator = new DevynBensonBulkGenerator();
      fileGenerator.createTestFileForEveryProvider(path, dataProviders);
      return;
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
