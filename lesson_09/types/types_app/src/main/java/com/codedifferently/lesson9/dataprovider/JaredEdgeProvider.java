package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JaredEdgeProvider extends DataProvider {
  public String getProviderName() {
    return "JaredEdgeProvider";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Double.class,
        "column2", Short.class,
        "column3", Integer.class,
        "column4", Boolean.class,
        "column5", Long.class,
        "column6", String.class,
        "column7", Float.class);
  }
}
