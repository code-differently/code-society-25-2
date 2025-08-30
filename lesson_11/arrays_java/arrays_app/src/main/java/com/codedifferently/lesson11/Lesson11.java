package com.codedifferently.lesson11;

import java.util.ArrayList;
import java.util.List;

public class Lesson11 {

  /**
   * Provide the solution to LeetCode 1929 here:
   * https://leetcode.com/problems/concatenation-of-array
   */
  public int[] getConcatenation(int[] nums) {
    if (nums.length == 0) {
      return nums;
    }
    int[] ans = new int[nums.length * 2];
    for (var i = 0; i < nums.length; i++) {
      ans[i] = nums[i];
      ans[i + nums.length] = nums[i];
    }
    return ans;
  }

  /**
   * Provide the solution to LeetCode 2942 here:
   * https://leetcode.com/problems/find-words-containing-character/
   */
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> result = new ArrayList();
    if (words.length == 0) {
      return result;
    }
    for (var i = 0; i < words.length; i++) {
      if (words[i].indexOf(x) != -1) {
        result.add(i);
      }
    }
    return result;
  }
}
