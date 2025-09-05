package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    // Create a map to store the index of each character in string t
    Map<Character, Integer> indexMap = new HashMap<>();

    // Fill the map with characters and their indices from string t
    for (int i = 0; i < t.length(); i++) {
      indexMap.put(t.charAt(i), i);
    }

    int totalDifference = 0;

    // For each character in string s, find its index in t and calculate the difference
    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      int indexInT = indexMap.get(currentChar);
      totalDifference += Math.abs(i - indexInT);
    }

    return totalDifference;
  }
}
