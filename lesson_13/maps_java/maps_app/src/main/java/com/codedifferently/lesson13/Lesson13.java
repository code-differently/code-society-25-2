package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    Map<Character, Integer> map = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
        map.put(s.charAt(i), i);
    }
    int diff = 0;
    for (int i = 0; i < t.length(); i++) {
        char currChar = t.charAt(i);
        int currIdx = map.get(currChar);
        diff += Math.abs(i - currIdx);
    }
    return diff;
  }
}
