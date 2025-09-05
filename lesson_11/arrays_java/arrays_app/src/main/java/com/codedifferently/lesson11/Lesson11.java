package com.codedifferently.lesson11;

import java.util.*;
import java.util.List;

public class Lesson11 {

  public int[] getConcatenation(int[] nums) {
    int n = nums.length;
    int[] ans = new int[2 * n];

    for (int i = 0; i < n; i++) {
      ans[i] = nums[i]; // put nums[i] in the first half
      ans[i + n] = nums[i]; // put nums[i] in the second half
    }

    return ans;
  }

  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> ans = new ArrayList<>();

    for (int i = 0; i < words.length; i++) {
      if (words[i].indexOf(x) != -1) { // if word contains x
        ans.add(i); // store the index
      }
    }

    return ans;
  }
}
