package com.codedifferently.lesson13;

import java.util.HashMap;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    int total = 0;
    HashMap<Character, Integer> mapS = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      mapS.put(s.charAt(i), i);
    }

    for (int i = 0; i < t.length(); i++) {
      char curChar = t.charAt(i);
      int curIndex = mapS.get(curChar);
      int diff = Math.abs(curIndex - i);
      total += diff;
    }
    return total;
  }
}
