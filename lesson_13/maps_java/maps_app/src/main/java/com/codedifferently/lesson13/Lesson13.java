package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    Map<Character, Integer> sIndexMap = new HashMap<>();
    Map<Character, Integer> tIndexMap = new HashMap<>();

    // Store the index of each character in string s
    for (int i = 0; i < s.length(); i++) {
      sIndexMap.put(s.charAt(i), i);
    }

    // Store the index of each character in string t
    for (int i = 0; i < t.length(); i++) {
      tIndexMap.put(t.charAt(i), i);
    }

    int difference = 0;

    // Calculate the sum of absolute differences between indices
    for (char c : sIndexMap.keySet()) {
      int sIndex = sIndexMap.get(c);
      int tIndex = tIndexMap.get(c);
      difference += Math.abs(sIndex - tIndex);
    }

    return difference;
  }
}
