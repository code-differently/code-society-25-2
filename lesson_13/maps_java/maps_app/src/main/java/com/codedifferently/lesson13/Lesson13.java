package com.codedifferently.lesson13;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    int[] pos = new int[128]; // store positions of chars in s
    for (int i = 0; i < s.length(); i++) {
      pos[s.charAt(i)] = i;
    }

    int sum = 0;
    for (int i = 0; i < t.length(); i++) {
      sum += Math.abs(pos[t.charAt(i)] - i);
    }

    return sum;
  }
}
