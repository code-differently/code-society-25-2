package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class TyranRiceJrProvider extends DataProvider {
  public String getProviderName() {
    return "tyranricejr";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Long.class,
        "column2", Float.class,
        "column3", Short.class,
        "column4", Double.class,
        "column5", String.class,
        "column6", Integer.class,
        "column7", Boolean.class);
  }
}

// IntegerStringBooleanFloatDoubleLongShort
