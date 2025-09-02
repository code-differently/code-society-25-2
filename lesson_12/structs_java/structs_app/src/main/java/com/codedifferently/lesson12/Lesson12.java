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

    // Traverse the linked list in pairs
    while (current != null && current.next != null) {
      int evenValue = current.val; // Even-indexed node (0, 2, 4, ...)
      int oddValue = current.next.val; // Odd-indexed node (1, 3, 5, ...)

      // Compare the pair and award points
      if (evenValue > oddValue) {
        evenScore++;
      } else if (oddValue > evenValue) {
        oddScore++;
      }
      // If equal, no points awarded to either team

      // Move to the next pair
      current = current.next.next;
    }

    // Determine the winner
    if (evenScore > oddScore) {
      return "Even";
    } else if (oddScore > evenScore) {
      return "Odd";
    } else {
      return "Tie";
    }
  }
}
