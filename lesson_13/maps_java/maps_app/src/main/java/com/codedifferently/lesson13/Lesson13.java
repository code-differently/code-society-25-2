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
    Map<Character, Integer> charPositions = new HashMap<>();

    // Store the position of each character in string s
    for (int i = 0; i < s.length(); i++) {
      charPositions.put(s.charAt(i), i);
    }

    int permutationDifference = 0;

    // For each character in string t, find its position in s and calculate difference
    for (int i = 0; i < t.length(); i++) {
      char currentChar = t.charAt(i);
      int positionInS = charPositions.get(currentChar);
      int positionInT = i;

      // Add the absolute difference to the total
      permutationDifference += Math.abs(positionInS - positionInT);
    }

    return permutationDifference;
  }
}
