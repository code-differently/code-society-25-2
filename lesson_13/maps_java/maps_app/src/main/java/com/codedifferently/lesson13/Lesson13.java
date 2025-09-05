package com.codedifferently.lesson13;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    int[] permutationArray = new int[128];
    for (int i = 0; i < s.length(); i++) {
      permutationArray[s.charAt(i)] = i;
    }
    int sum = 0;
    for (int i = 0; i < t.length(); i++) {
      sum += Math.abs(permutationArray[t.charAt(i)] - i);
    }
    return sum;
  }
}
