package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    // get a map of characters and their indices and then compare the idicies of a character from
    // the map with the indices of the same character from the array
    var result = 0;
    Map<Character, Integer> mapS = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      // creates map of indices and characters in a string
      mapS.put(s.charAt(i), i);
    }
    for (int i = 0; i < t.length(); i++) {
      result += Math.abs(i - mapS.get(t.charAt(i)));
    }

    return result;
  }
}
