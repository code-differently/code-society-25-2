package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    int n = s.length();
    Map<Character, Integer> indexOfIntS = new HashMap<>(n);

    for (int i = 0; i < n; i++) indexOfIntS.put(s.charAt(i), i);

    int total = 0;

    for (int i = 0; i < n; i++) total += Math.abs(indexOfIntS.get(t.charAt(i)) - i);

    return total;
  }
}
