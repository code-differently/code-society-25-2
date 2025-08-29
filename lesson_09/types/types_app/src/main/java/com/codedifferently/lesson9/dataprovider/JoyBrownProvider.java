package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class JoyBrownProvider extends DataProvider {
  public String getProviderName() {
    return "joybrown";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Integer.class,
        "column2", Double.class,
        "column3", Long.class,
        "column4", Boolean.class,
        "column5", Short.class,
        "column6", String.class,
        "column7", Float.class);
  }
}
