package com.codedifferently.lesson9.dataprovider;

import com.codedifferently.lesson9.Model.DataType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JoneeDataFileProvider implements DataProvider {

  @Override
  public String getProviderName() {
    return "joneeData";
  }

  @Override
  public Map<String, DataType> getTypes() {
    Map<String, DataType> types = new LinkedHashMap<>();
    types.put("itemCode", DataType.STRING);
    types.put("quantity", DataType.INTEGER);
    types.put("price", DataType.DOUBLE);
    types.put("isInStock", DataType.BOOLEAN);
    return Collections.unmodifiableMap(types);
  }
}
