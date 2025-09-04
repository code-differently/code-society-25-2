package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JaydenEllisProvider extends DataProvider {
  public String getProviderName() {
    return "jaydenellis";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Long.class,
        "column2", Short.class,
        "column3", Boolean.class,
        "column4", String.class,
        "column5", Float.class,
        "column6", Integer.class,
        "column7", Double.class);
  }
}
