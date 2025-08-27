package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class MarthaOfuaniProvider extends DataProvider {
  public String getProviderName() {
    return "marthaofuani";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", String.class,
        "column2", Long.class,
        "column3", Short.class,
        "column4", Boolean.class,
        "column5", Double.class,
        "column6", Float.class,
        "column7", Integer.class);
  }
}
