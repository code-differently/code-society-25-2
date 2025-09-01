package com.codedifferently.lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
      result[i] = nums[i - nums.length];
    }
    return result;
    
    
  }

  /**
   * Provide the solution to LeetCode 2942 here:
   * https://leetcode.com/problems/find-words-containing-character/
   */
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> result= new ArrayList<>();
    return IntStream
    .range(0,words.length)
    .filter(i->words[i].contains(String.valueOf(x)))
    .boxed()
    .collect(Collectors.toList());



  }
}
