package com.codedifferently.lesson11;

import java.util.ArrayList;
import java.util.List;

public class Lesson11 {

  public int[] getConcatenation(int[] nums) {
    int n = nums.length;
    int[] ans = new int[2 * n];

    for (int i = 0; i < n; i++) {
      ans[i] = nums[i];
      ans[i + n] = nums[i];
    }
    return ans;
  }

  public List<Integer> findWordsContaining(String[] words, char x) {
    ArrayList<Integer> indices = new ArrayList<>();

    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      if (word.indexOf(x) != -1) {
        indices.add(i);
      }
    }

    int[] result = new int[indices.size()];
    for (int i = 0; i < indices.size(); i++) {
      result[i] = indices.get(i);
    }
    return indices;
  }
}
