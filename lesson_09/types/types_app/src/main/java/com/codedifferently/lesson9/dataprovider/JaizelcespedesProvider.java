package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JaizelcespedesProvider extends DataProvider {
  public String getProviderName() {
    return "Jaizelcespedes";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Long.class,
        "column2", Short.class,
        "column3", Double.class,
        "column4", Boolean.class,
        "column5", Integer.class,
        "column6", String.class,
        "column7", Float.class);
  }
}
