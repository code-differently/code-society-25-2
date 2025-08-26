package com.codedifferently.lesson9.dataprovider;

import java.util.Map; // Import the new DataType enum

import com.codedifferently.lesson9.model.DataType;

public interface DataProvider {
    String getProviderName();
    Map<String, DataType> getTypes(); // This method needs to be present
}


