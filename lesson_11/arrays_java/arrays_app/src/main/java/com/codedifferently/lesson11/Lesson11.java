package com.codedifferently.lesson11;

import java.util.ArrayList;
import java.util.List;

public class Lesson11 {

  /**
   * Provide the solution to LeetCode 1929 here:
   * https://leetcode.com/problems/concatenation-of-array
   */
  public int[] getConcatenation(int[] nums) {
    // Create a new array with twice the length of the original array
    int[] result = new int[nums.length * 2];

    // Copy the original array twice
    for (int i = 0; i < nums.length; i++) {
      result[i] = nums[i]; // First copy
      result[i + nums.length] = nums[i]; // Second copy
    }

    return result;
  }

  /**
   * Provide the solution to LeetCode 2942 here:
   * https://leetcode.com/problems/find-words-containing-character/
   */
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> result = new ArrayList<>();

    // Iterate through each word and check if it contains the character
    for (int i = 0; i < words.length; i++) {
      if (words[i].indexOf(x) != -1) {
        result.add(i);
      }
    }

    return result;
  }
}
