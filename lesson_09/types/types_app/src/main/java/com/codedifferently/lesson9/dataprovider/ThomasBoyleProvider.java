/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson9.dataprovider;

import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ThomasBoyleProvider extends DataProvider {
  public String getProviderName() {
    return "thomasboyle";
  }

  public Map<String, Class> getColumnTypeByName() {
    return Map.of(
        "column1", Long.class, /*long*/
        "column2", Double.class, /*double*/
        "column3", Integer.class, /*integer*/
        "column4", Float.class, /*float*/
        "column5", Boolean.class, /*boolean*/
        "column6", String.class, /*string*/
        "column7", Short.class); /*short*/
  }
}
