package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    // Create a map to store character positions in string s
    Map<Character, Integer> sPositions = new HashMap<>();

    // Store the position of each character in string s
    for (int i = 0; i < s.length(); i++) {
      sPositions.put(s.charAt(i), i);
    }

    int totalDifference = 0;

    // For each character in string t, find its position difference
    for (int i = 0; i < t.length(); i++) {
      char currentChar = t.charAt(i);
      int positionInS = sPositions.get(currentChar);
      int positionInT = i;

      // Add the absolute difference to the total
      totalDifference += Math.abs(positionInS - positionInT);
    }

    return totalDifference;
  }
}
