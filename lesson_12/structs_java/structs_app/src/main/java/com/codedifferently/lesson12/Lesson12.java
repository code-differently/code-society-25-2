package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public String gameResult(ListNode head) {
    int evenScore = 0;
    int oddScore = 0;

    ListNode currentNode = head;
    while (currentNode != null) {
      if (currentNode.val > currentNode.next.val) {
        evenScore++;
      } else if (currentNode.val < currentNode.next.val) {
        oddScore++;
      }
      currentNode = currentNode.next.next;
    }
    if (oddScore == evenScore) {
      return "Tie";
    } else if (oddScore > evenScore) {
      return "Odd";
    }
    return "Even";
  }
}
