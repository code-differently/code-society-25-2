package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class EvanderBlueProvider extends DataProvider {
  public String getProviderName() {
    return "evanderblue";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", String.class,
        "column2", Long.class,
        "column3", Integer.class,
        "column4", Short.class,
        "column5", Double.class,
        "column6", Float.class,
        "column7", Boolean.class);
  }
}
