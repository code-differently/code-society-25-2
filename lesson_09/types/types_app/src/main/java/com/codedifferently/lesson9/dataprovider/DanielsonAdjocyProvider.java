package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Component;

@Component
@Service
public class DanielsonAdjocyProvider extends DataProvider {
  public String getProviderName() {
    return "danielsonadjocy";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Integer.class,
        "column2", Short.class,
        "column3", Long.class,
        "column4", Float.class,
        "column5", Boolean.class,
        "column6", String.class,
        "column7", Double.class);
  }
}
