package com.codedifferently.lesson13;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    int totalDifference = 0;
    for (int i = 0; i < s.length(); i++) {
      char currentChar = s.charAt(i);
      int positionInT = t.indexOf(currentChar);
      if (positionInT == -1) {
        return -1;
      }

      totalDifference += Math.abs(i - positionInT);
    }
    return totalDifference;
  }
}
