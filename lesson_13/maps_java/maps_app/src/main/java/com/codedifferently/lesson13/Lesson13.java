package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {
  public int findPermutationDifference(String s, String t) {
    Map<Character, Integer> map = new HashMap<>();

    for (int i = 0; i < t.length(); i++) {
      map.put(t.charAt(i), i);
    }
    int sum = 0;

    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      int indexInT = map.get(currentChar);
      int diff = Math.abs(i - indexInT);
      sum += diff;
    }
    return sum;
  }
}
