package com.codedifferently.lesson9.dataprovider;

import com.codedifferently.lesson9.Model.DataType;
import java.util.Map;

public interface DataProvider {
  String getProviderName();

  Map<String, DataType> getTypes();
}
