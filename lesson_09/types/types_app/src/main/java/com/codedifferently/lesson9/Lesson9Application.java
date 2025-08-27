package com.codedifferently.lesson9;

import com.codedifferently.lesson9.Model.DataType;
import com.codedifferently.lesson9.dataprovider.DataProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lesson9Application {

  private final ApplicationContext applicationContext;

  public Lesson9Application(ApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  public static void main(String[] args) {
    SpringApplication.run(Lesson9Application.class, args);
  }

  @Bean
  public CommandLineRunner run(ApplicationContext ctx) {
    return args -> {
      boolean generateSamples = false;
      for (String arg : args) {
        if ("--generate-samples".equals(arg)) {
          generateSamples = true;
          break;
        }
      }

      if (generateSamples) {
        System.out.println("Generating sample data files...");
        generateSampleDataFiles();
      } else {
        System.out.println(
            "Application started. To generate sample files, run with --generate-samples flag.");
      }
    };
  }

  private void generateSampleDataFiles() {
    // Define the output directory relative to the current working directory or resource path
    // For development within VS Code, this path typically works
    Path outputPath = Paths.get("src", "main", "resources", "data", "generated_samples");

    try {
      // Ensure the directory exists
      Files.createDirectories(outputPath);
      System.out.println("Output directory created: " + outputPath.toAbsolutePath());

      // Get all beans that are instances of DataProvider
      Map<String, DataProvider> dataProviders =
          applicationContext.getBeansOfType(DataProvider.class);

      if (dataProviders.isEmpty()) {
        System.out.println("No DataProvider implementations found to generate samples.");
        return;
      }

      for (DataProvider provider : dataProviders.values()) {
        String providerName = provider.getProviderName();
        Map<String, DataType> dataTypes = provider.getTypes();
        List<Map<String, Object>> sampleDataList = new ArrayList<>();
        Random random = new Random();

        // Generate 5 sample records for each provider
        for (int i = 0; i < 5; i++) {
          sampleDataList.add(generateSampleRecord(dataTypes, random));
        }

        File outputFile = outputPath.resolve(providerName + "Data.json").toFile();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(outputFile, sampleDataList);
        System.out.println(
            "Generated sample file for '" + providerName + "': " + outputFile.getAbsolutePath());
      }
    } catch (IOException e) {
      System.err.println("Error generating sample data files: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private Map<String, Object> generateSampleRecord(Map<String, DataType> dataTypes, Random random) {
    return dataTypes.entrySet().stream()
        .collect(
            java.util.LinkedHashMap::new,
            (map, entry) -> {
              String columnName = entry.getKey();
              DataType dataType = entry.getValue();
              Object sampleValue;

              switch (dataType) {
                case STRING:
                  sampleValue = "sample_text_" + random.nextInt(1000);
                  break;
                case INTEGER:
                  sampleValue = random.nextInt(10000);
                  break;
                case BOOLEAN:
                  sampleValue = random.nextBoolean();
                  break;
                case FLOAT:
                  sampleValue = random.nextFloat() * 1000;
                  break;
                case DOUBLE:
                  sampleValue = random.nextDouble() * 100000;
                  break;
                case LONG:
                  sampleValue = random.nextLong();
                  break;
                case SHORT:
                  sampleValue = (short) random.nextInt(Short.MAX_VALUE + 1);
                  break;
                  // Add other DataType cases if needed
                default:
                  sampleValue = "unknown_type_sample";
              }
              map.put(columnName, sampleValue);
            },
            Map::putAll);
  }
}
