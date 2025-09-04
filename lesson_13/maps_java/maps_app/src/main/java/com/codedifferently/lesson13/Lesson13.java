package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {

    Map<Character, Integer> tMap = new HashMap<>();
    for (int i = 0; i < t.length(); i++) {
      tMap.put(t.charAt(i), i);
    }
    int total = 0;

    for (int i = 0; i < s.length(); i++) {
      char current = s.charAt(i);

      int indexInT = tMap.get(current);

      int diff = Math.abs(i - indexInT);
      total += diff;
    }

    return total;
  }
}
