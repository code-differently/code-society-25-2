package com.codedifferently.lesson9.dataprovider;

import com.codedifferently.lesson9.model.DataType;
import java.util.Map;

public interface DataProvider {
    String getProviderName();
    Map<String, DataType> getTypes();
}
