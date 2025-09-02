package com.codedifferently.lesson11;
 
import java.util.ArrayList;
import java.util.List;

public class Lesson11 {

  /**
   * Provide the solution to LeetCode 1929 here:
   * https://leetcode.com/problems/concatenation-of-array
   */
  public int[] getConcatenation(int[] nums) {
    int[] ans = new int [nums.length * 2];

      for (int i = 0; i < 2; i++) {
        for (int j = 0; j < nums.length; j++) {
            ans[ i * nums.length + j] = nums[j];
        }
      }
      return ans;    
  }

  /**
   * Provide the solution to LeetCode 2942 here:
   * https://leetcode.com/problems/find-words-containing-character/
   */
  public List<Integer> findWordsContaining(String[] words, char x) {
    List<Integer> resultArray = new ArrayList <>();
			
    for (int i = 0; i < words.length; i++) { 
        for (int j = 0; j < words[i].length(); j++) {
            if (words[i].charAt(j) == x) {
                resultArray.add(i);
                break;
            }      
        }
    }    
        return resultArray;
    }       
  }
