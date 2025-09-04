package com.codedifferently.lesson13;

import java.util.HashMap;
import java.util.Map;

public class Lesson13 {

  /**
   * LeetCode 3146: Permutation Difference Between Two Strings
   * Sum over all characters c of |index_in_s(c) - index_in_t(c)|.
   */
  public int findPermutationDifference(String s, String t) {
    // 1) Map each character in s to its index
    Map<Character, Integer> pos = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      pos.put(s.charAt(i), i);
    }

    // 2) Walk through t and sum absolute index differences
    int sum = 0;
    for (int j = 0; j < t.length(); j++) {
      char c = t.charAt(j);
      int i = pos.get(c);                 // index of c in s
      sum += Math.abs(j - i);             // distance between positions
    }

    // 3) Return the total
    return sum;
  }
}
