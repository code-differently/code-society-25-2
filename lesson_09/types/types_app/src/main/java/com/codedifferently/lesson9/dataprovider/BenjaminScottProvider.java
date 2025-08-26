package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BenjaminScottProvider extends DataProvider {
  public String getProviderName() {
    return "benjaminscottprovider";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", String.class,
        "column2", Short.class,
        "column3", Integer.class,
        "column4", Boolean.class,
        "column5", Float.class,
        "column6", Double.class,
        "column7", Long.class);
  }
}
