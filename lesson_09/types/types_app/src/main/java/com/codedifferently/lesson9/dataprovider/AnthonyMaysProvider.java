package com.codedifferently.lesson9.dataprovider;

import com.codedifferently.lesson9.model.DataType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class AnthonyMaysProvider implements DataProvider {

  @Override
  public String getProviderName() {
    return "anthonyMaysData";
  }

  @Override
  public Map<String, DataType> getTypes() {
    Map<String, DataType> types = new LinkedHashMap<>();
    types.put("id", DataType.INTEGER);
    types.put("name", DataType.STRING);
    types.put("age", DataType.INTEGER);
    types.put("isActive", DataType.BOOLEAN);
    types.put("salary", DataType.DOUBLE);
    return Collections.unmodifiableMap(types);
  }
}
