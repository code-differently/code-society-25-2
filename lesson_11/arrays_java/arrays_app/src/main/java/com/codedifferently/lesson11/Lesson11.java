package com.codedifferently.lesson11;

import java.util.ArrayList;
import java.util.List;

public class Lesson11 {

  /**
   * Provide the solution to LeetCode 1929 here:
   * https://leetcode.com/problems/concatenation-of-array
   */
  public int[] getConcatenation(int[] nums) {
    int[] result = new int[nums.length * 2];
    for (int i = 0; i < nums.length; i++) {
      result[i] = nums[i];
    }
    for (int i = nums.length; i < result.length; i++) {
      result[i] = nums[i];
    }
    return result;
  }

  /**
   * Provide the solution to LeetCode 2942 here:
   * https://leetcode.com/problems/find-words-containing-character/
   */
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> result= new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      if(words[i].contains(String.valueOf(x))) {
        result.add(i);
      }
    }
    return result;
  }
}
