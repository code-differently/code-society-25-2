package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class DanielsonAdjocyProvider extends DataProvider {
  public String getProviderName() {
    return "danielsonadjocy";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Integer.class,
        "column2", Long.class,
        "column3", Double.class,
        "column4", Float.class,
        "column5", Short.class,
        "column6", String.class,
        "column7", Boolean.class);
  }
}
