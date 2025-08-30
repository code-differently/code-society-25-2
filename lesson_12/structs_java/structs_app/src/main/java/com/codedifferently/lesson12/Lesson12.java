package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public String gameResult(ListNode head) {
    int evenTeam = 0;
    int oddTeam = 0;

    while (head != null) {
      if (head.val > head.next.val) {
        evenTeam++;
      } else {
        oddTeam++;
      }
      head = head.next.next;
    }

    if (evenTeam == oddTeam) {
      return "Tie";
    }

    return evenTeam > oddTeam ? "Even" : "Odd";
  }
}
