package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public String gameResult(ListNode head) {
    int evenScore = 0;
    int oddScore = 0;

    ListNode current = head;
    while (current != null && current.next != null) {
      if (current.val > current.next.val) {
        evenScore++;
      } else if (current.val < current.next.val) {
        oddScore++;
      }

      current = current.next.next;
    }

    if (evenScore > oddScore) {
      return "Even";
    } else if (oddScore > evenScore) {
      return "Odd";
    } else {
      return "Tie";
    }
  }
}
