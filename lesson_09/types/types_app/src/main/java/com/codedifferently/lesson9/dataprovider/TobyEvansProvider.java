package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TobyEvansProvider extends DataProvider {
  public String getProviderName() {
    return "tobyevans";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Double.class,
        "column2", Float.class,
        "column3", Short.class,
        "column4", Long.class,
        "column5", Boolean.class,
        "column6", Integer.class,
        "column7", String.class);
  }
}
