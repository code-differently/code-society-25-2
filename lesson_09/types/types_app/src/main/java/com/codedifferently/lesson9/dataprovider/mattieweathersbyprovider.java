/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson9.dataprovider;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class mattieweathersbyprovider extends DataProvider {
  public String getProviderName() {
    return "mattieweathersby";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Short.class, 
        "column2", Integer.class, 
        "column3", Double.class, 
        "column4", String.class, 
        "column5", Boolean.class, 
        "column6", Long.class, 
        "column7", Float.class); 
  }
}
