package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TrinitieJacksonProvider extends DataProvider {
  public String getProviderName() {
    return "trinitiejackson";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", String.class,
        "column2", Double.class,
        "column3", Float.class,
        "column4", Integer.class,
        "column5", Long.class,
        "column6", Short.class,
        "column7", Boolean.class);
  }
}
