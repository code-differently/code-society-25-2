package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class LindaQuinoaProvider extends DataProvider {
  public String getProviderName() {
    return "lindaquinoa";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", String.class,
        "column2", Short.class,
        "column3", Double.class,
        "column4", Boolean.class,
        "column5", Long.class,
        "column6", Integer.class,
        "column7", Float.class);
  }
}
