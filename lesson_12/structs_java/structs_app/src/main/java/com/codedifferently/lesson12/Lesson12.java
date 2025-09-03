package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public String gameResult(ListNode head) {
    int oddScore = 0;
    int evenScore = 0;

    while (head != null && head.next != null) {
      int firstValue = head.val;
      int secondValue = head.next.val;

      if (firstValue > secondValue) {
        evenScore++; // Even team gets a point
      } else if (secondValue > firstValue) {
        oddScore++; // Odd team gets a point
      }
      head = head.next.next; // Move to next pair
    }

    if (oddScore > evenScore) {
      return "Odd";
    } else if (evenScore > oddScore) {
      return "Even";
    } else {
      return "Tie";
    }
  }
}
