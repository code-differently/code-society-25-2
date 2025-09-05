package com.codedifferently.lesson13;

import java.util.HashMap;

public class Lesson13 {

  public static void main(String[] args) {}

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    int sum = 0;
    HashMap<Character, Integer> indexofCharS = new HashMap<Character, Integer>();
    for (int i = 0; i < s.length(); i++) {
      indexofCharS.put(s.charAt(i), i);
    }
    for (int i = 0; i < t.length(); i++) {
      char ch = t.charAt(i);
      sum += Math.abs(indexofCharS.get(ch) - i);
    }
    return sum;
  }
}
