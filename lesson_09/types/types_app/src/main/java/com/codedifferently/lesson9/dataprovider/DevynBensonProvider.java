package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class DevynBensonProvider extends DataProvider {
  public String getProviderName() {
    return "devynbenson";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Boolean.class, // true/false values
        "column2", Float.class, // Scientific notation floats like 1.3605703E38
        "column3", String.class, // Text strings like "vqjy6zgi32lr"
        "column4", Long.class, // Very large integers like 649023162885324032
        "column5", Short.class, // Small integers like 4581 (fits in Short range)
        "column6", Integer.class, // Medium integers like 383252171
        "column7", Double.class); // Very large scientific notation like 1.7759E307
  }
}
