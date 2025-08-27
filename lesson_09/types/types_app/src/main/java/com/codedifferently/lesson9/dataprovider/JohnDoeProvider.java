package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class JohnDoeProvider extends DataProvider {
  public String getProviderName() {
    return "johndoe";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", String.class,
        "column2", Double.class,
        "column3", Float.class,
        "column4", Integer.class,
        "column5", Short.class,
        "column6", Boolean.class,
        "column7", Long.class);
  }
}
