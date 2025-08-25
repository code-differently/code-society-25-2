package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class KerryFergusonProvider extends DataProvider {
  @Override
  public String getProviderName() {
    return "kerryferguson";
  }

  @Override
  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Float.class, // Scientific notation floats like "2.079595E38"
        "column2", Boolean.class, // true/false values
        "column3", Double.class, // Very large numbers like "1.526281089961279E308"
        "column4", Integer.class, // Integer values like "1688305663"
        "column5", Short.class, // Small integers like "2563"
        "column6", String.class, // Text values like "tzlnmogqu"
        "column7", Long.class); // Large integers like "3328163602399324160"
  }
}
