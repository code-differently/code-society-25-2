package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    // Create a map to store the index of each character in string s
    Map<Character, Integer> indexbyChar = new HashMap<>();

    // Store the index of each character in string s
    for (int i = 0; i < s.length(); i++) {
      indexbyChar.put(s.charAt(i), i);
    }

    int permutationDifference = 0;

    // For each character in string t, find its index in string s
    // and calculate the absolute difference
    for (int i = 0; i < t.length(); i++) {
      char currentChar = t.charAt(i);
      int currentIndex = indexbyChar.get(currentChar);
      permutationDifference += Math.abs(i - currentIndex);
    }

    return permutationDifference;
  }
}
