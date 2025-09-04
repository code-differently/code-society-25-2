package com.codedifferently.lesson11;

import java.util.ArrayList;
import java.util.List;

public class Lesson11  {
public static void main(String[] args) {
  
}
  /**
   * Provide the solution to LeetCode 1929 here:
   * https://leetcode.com/problems/concatenation-of-array
   */
  public int[] getConcatenation(int[] nums) {
    int n = nums.length;
    int[] my_new_arr = new int[n * 2];

    for (int i = 0; i < n; i++) {
      my_new_arr[i] = nums[i];
      my_new_arr[i + n] = nums[i]; // second copy offset by n
    }
    return my_new_arr;
  }

  /**
   * Provide the solution to LeetCode 2942 here:
   * https://leetcode.com/problems/find-words-containing-character/
   */
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> my_array = new ArrayList<>();

    for (int i = 0; i < words.length; i++) {

      if (words[i].indexOf(x) >= 0) {
        my_array.add(i);
      }
    }
    return my_array;
  }
}
