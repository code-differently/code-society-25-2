package com.codedifferently.lesson9.dataprovider;

import com.codedifferently.lesson9.model.DataType;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.Map;
import java.util.LinkedHashMap;

@Component
public class JoneeDataFileProvider implements DataProvider {

    @Override
    public String getProviderName() {
        return "joneeData";
    }

    @Override
    public Map<String, DataType> getTypes() {
        Map<String, DataType> columnTypes = new LinkedHashMap<>();
        columnTypes.put("id", DataType.INTEGER);
        columnTypes.put("firstName", DataType.STRING);
        columnTypes.put("lastName", DataType.STRING);
        columnTypes.put("age", DataType.INTEGER);
        columnTypes.put("isActive", DataType.BOOLEAN);
        return Collections.unmodifiableMap(columnTypes);
    }
}
