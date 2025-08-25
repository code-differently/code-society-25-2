package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class DanielBoyceProvider extends DataProvider {

  public String getProviderName() {
    return "danielboyce";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Boolean.class,
        "column2", Integer.class,
        "column3", Double.class,
        "column4", Short.class,
        "column5", Long.class,
        "column6", String.class,
        "column7", Float.class);
  }
}
