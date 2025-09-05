package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    // Map each character to its index in s and t
    Map<Character, Integer> sIndex = new HashMap<>();
    Map<Character, Integer> tIndex = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      sIndex.put(s.charAt(i), i);
      tIndex.put(t.charAt(i), i);
    }
    int diff = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      diff += Math.abs(sIndex.get(c) - tIndex.get(c));
    }
    return diff;
  }
}
