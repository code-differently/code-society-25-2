package com.codedifferently.lesson13;

import java.util.HashMap;

public class Lesson13 {

  /**
   * Provide the solution to LeetCode 3146 here:
   * https://leetcode.com/problems/permutation-difference-between-two-strings
   */
  public int findPermutationDifference(String s, String t) {
    HashMap<Character, Integer> positionMap = new HashMap<>();

    for (int i = 0; i < s.length(); i++) {
        positionMap.put(s.charAt(i), i);
    }

    int totalDifference = 0;
    for (int i = 0; i < t.length(); i++) {
        char currentChar = t.charAt(i);
        int positionInS = positionMap.get(currentChar);
        int positionInT = i;
        
        int difference = Math.abs(positionInS - positionInT);
        totalDifference += difference;
    }

    return totalDifference;
  }
}