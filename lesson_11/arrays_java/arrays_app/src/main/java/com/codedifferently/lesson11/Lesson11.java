package com.codedifferently.lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lesson11 {

  /**
   * Provide the solution to LeetCode 1929 here:
   * https://leetcode.com/problems/concatenation-of-array
   */
  public int[] getConcatenation(int[] nums) {
    int n = nums.length;
    int[] ans = new int[2 * n];
    for (int i = 0; i < n; i++) {
      ans[i] = nums[i];
      ans[i + n] = nums[i];
    }
    return ans;
  }

  /**
   * Provide the solution to LeetCode 2942 here:
   * https://leetcode.com/problems/find-words-containing-character/
   */
  public static void main(String[] args) {
    Lesson11 hw = new Lesson11();
    int[] result = hw.getConcatenation(new int[] {1, 2, 1});
    System.out.println(Arrays.toString(result));

    List<Integer> found = hw.findWordsContaining(new String[] {"leet", "code"}, "e".charAt(0));
    System.out.println(found);

    List<Integer> found2 =
        hw.findWordsContaining(new String[] {"abc", "bcd", "aaaa", "cbc"}, "a".charAt(0));
    System.out.println(found2);
  }

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
