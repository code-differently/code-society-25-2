package com.codedifferently.lesson9.dataprovider;

import java.util.Map;

public class EthanHillmanProvider extends DataProvider {
  public String getProviderName() {
    return "ethanhillman";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", String.class,
        "column2", Boolean.class,
        "column3", Integer.class,
        "column4", Float.class,
        "column5", Long.class,
        "column6", Short.class,
        "column7", Double.class);
  }
}
