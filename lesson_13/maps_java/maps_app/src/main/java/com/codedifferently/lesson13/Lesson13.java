package com.codedifferently.lesson13;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    int answer = 0;

    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);

      for (int j = 0; j < t.length(); j++) {
        if (t.charAt(j) == ch) {
          answer+= math.abs(i - j);
          break;
        }
      }
    }
    return answer;
  } 
}
