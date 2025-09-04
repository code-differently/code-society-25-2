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
  // Test method to verify our solution works
public static void main(String[] args) {
    Lesson13 lesson = new Lesson13();
    
    // Test case 1: "abc" and "bac" should return 2
    String s1 = "abc";
    String t1 = "bac";
    int result1 = lesson.findPermutationDifference(s1, t1);
    System.out.println("Test 1: s = \"" + s1 + "\", t = \"" + t1 + "\"");
    System.out.println("Expected: 2, Got: " + result1);
    System.out.println("Test 1 " + (result1 == 2 ? "PASSED" : "FAILED"));
    System.out.println();
    
    // Test case 2: "abcde" and "edbca" should return 12
    String s2 = "abcde";
    String t2 = "edbca";
    int result2 = lesson.findPermutationDifference(s2, t2);
    System.out.println("Test 2: s = \"" + s2 + "\", t = \"" + t2 + "\"");
    System.out.println("Expected: 12, Got: " + result2);
    System.out.println("Test 2 " + (result2 == 12 ? "PASSED" : "FAILED"));
}
}
