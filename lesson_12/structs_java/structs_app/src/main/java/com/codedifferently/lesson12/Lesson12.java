package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public String gameResult(ListNode head) {
    int evenTeam = 0;
    int oddTeam = 0;

    ListNode current = head; // Start from the head of the list
    while (current != null && current.next != null) {
      if (current.val > current.next.val) {
        evenTeam++;
      } else {
        oddTeam++;
      }
      current = current.next.next; // Move to the next pair
    }

    if (evenTeam == oddTeam) {
      return "Tie";
    } else if (evenTeam > oddTeam) {
      return "Even";
    } else {
      return "Odd";
    }
  }
}
