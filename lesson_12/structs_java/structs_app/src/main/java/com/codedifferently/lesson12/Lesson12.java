package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public String gameResult(ListNode head) {
    int evenPoints = 0;
    int oddPoints = 0;

    // Traverse the list in pairs
    ListNode current = head;
    while (current != null && current.next != null) {
      // current is at even index, current.next is at odd index
      int evenValue = current.val;
      int oddValue = current.next.val;

      if (evenValue > oddValue) {
        evenPoints++;
      } else if (oddValue > evenValue) {
        oddPoints++;
      }
      // If equal, no points awarded (though this shouldn't happen based on constraints)

      // Move to the next pair
      current = current.next.next;
    }

    if (evenPoints > oddPoints) {
      return "Even";
    } else if (oddPoints > evenPoints) {
      return "Odd";
    } else {
      return "Tie";
    }
  }
}
