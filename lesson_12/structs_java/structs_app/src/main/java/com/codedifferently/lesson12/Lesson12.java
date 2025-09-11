package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public String gameResult(ListNode head) {
    if (head == null) {
      return "Tie";
    }

    int evenWins = 0;
    int oddWins = 0;
    ListNode current = head;

    // Process pairs of nodes (Even team vs Odd team)
    while (current != null && current.next != null) {
      // Apply game logic based on current.val
      int evenValue = current.val;
      int oddValue = current.next.val;

      if (evenValue > oddValue) {
        evenWins++;
      } else if (oddValue > evenValue) {
        oddWins++;
      }
      // If equal, no one wins that round
      current = current.next.next; // Move to next pair
    }

    if (evenWins > oddWins) {
      return "Even";
    } else if (oddWins > evenWins) {
      return "Odd";
    } else {
      return "Tie";
    }
  }
}
