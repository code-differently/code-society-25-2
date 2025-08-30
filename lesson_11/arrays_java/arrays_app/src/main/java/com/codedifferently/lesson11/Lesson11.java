package com.codedifferently.lesson11;

import java.util.ArrayList;
import java.util.List;

public class Lesson11 {

  /**
   * LeetCode 1929: Concatenation of Array Make a new array that has the input twice, back-to-back.
   * Example: [1,2,1] -> [1,2,1, 1,2,1]
   */
  public int[] getConcatenation(int[] nums) {
    int n = nums.length;
    int[] ans = new int[2 * n];
    for (int i = 0; i < n; i++) {
      ans[i] = nums[i]; // first copy
      ans[i + n] = nums[i]; // second copy
    }
    return ans;
  }

  /**
   * LeetCode 2942: Find Words Containing Character Return the list of indices where words[i]
   * contains the character x. Example: ["leet","code"], x='e' -> [0,1]
   */
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> result = new ArrayList<>();
    for (int i = 0; i < words.length; i++) {
      if (words[i].indexOf(x) != -1) {
        result.add(i);
      }
    }
    return result;
  }
}
