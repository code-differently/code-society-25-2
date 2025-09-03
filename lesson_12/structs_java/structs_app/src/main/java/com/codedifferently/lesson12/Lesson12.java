package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */

  public static void main(String[] args) {

  }
  public String gameResult(ListNode head) {
    int odd = 0;
    int even = 0;
    if (head == null || head.next == null) {
      return "Tie";
    }
    ListNode current = head;
    ListNode next = head.next;

    while (current != null && next != null) {
      if (current.val > next.val) {
        even++;
      } else if (current.val < next.val) {
        odd++;
      }
      current = next.next;
      if (current != null) {
        next = current.next;
      } else {
        next = null;
      }
    }

    if (odd < even) {
      return "Even";
    } else if (odd > even) {
      return "Odd";
    }
    return "Tie";
  }
}
