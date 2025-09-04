package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    Map<Character, Integer> perm1 = new HashMap<>();
    Map<Character, Integer> perm2 = new HashMap<>();

    // build maps
    for (int i = 0; i < s.length(); i++) {
      perm1.put(s.charAt(i), i);
      perm2.put(t.charAt(i), i);
    }

    int count = 0;
    for (char key : perm1.keySet()) {
      count += Math.abs(perm1.get(key) - perm2.get(key));
    }

    return count;
  }
}
