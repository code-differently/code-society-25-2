package com.codedifferently.lesson13;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    int permutationDifferences = 0;

    for (int i = 0; i < s.length(); i++) {
      char charFromS = s.charAt(i);
      for (int j = 0; j < t.length(); j++) {
        char charFromT = t.charAt(j);

        if (charFromS == charFromT) {
          int difference = Math.abs(i - j);

          permutationDifferences += difference;
          break;
        }
      }
    }
    return (permutationDifferences);
  }
}
