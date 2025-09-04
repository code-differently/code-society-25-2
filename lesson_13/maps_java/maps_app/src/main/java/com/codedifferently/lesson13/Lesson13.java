package com.codedifferently.lesson13;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    int sum = 0;
    for (int i = 0; i < s.length(); i++) {
      sum += Math.abs(s.indexOf(s.charAt(i)) - t.indexOf(s.charAt(i)));
    }
    return sum;
  }
}
