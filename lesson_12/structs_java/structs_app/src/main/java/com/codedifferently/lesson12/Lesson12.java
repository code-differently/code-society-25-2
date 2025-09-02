package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   * 
   * You are given the head of a linked list of even length containing integers.

Each odd-indexed node contains an odd integer and each even-indexed node contains an even integer.

We call each even-indexed node and its next node a pair, e.g., the nodes with indices 0 and 1 are a pair, the nodes with indices 2 and 3 are a pair, and so on.

For every pair, we compare the values of the nodes in the pair:

If the odd-indexed node is higher, the "Odd" team gets a point.
If the even-indexed node is higher, the "Even" team gets a point.
Return the name of the team with the higher points, if the points are equal, return "Tie".

 

Example 1:

Input: head = [2,1]

Output: "Even"

Explanation: There is only one pair in this linked list and that is (2,1). Since 2 > 1, the Even team gets the point.

Hence, the answer would be "Even".

Example 2:

Input: head = [2,5,4,7,20,5]

Output: "Odd"

Explanation: There are 3 pairs in this linked list. Let's investigate each pair individually:

(2,5) -> Since 2 < 5, The Odd team gets the point.

(4,7) -> Since 4 < 7, The Odd team gets the point.

(20,5) -> Since 20 > 5, The Even team gets the point.

The Odd team earned 2 points while the Even team got 1 point and the Odd team has the higher points.

Hence, the answer would be "Odd".

Example 3:

Input: head = [4,5,2,1]

Output: "Tie"

Explanation: There are 2 pairs in this linked list. Let's investigate each pair individually:

(4,5) -> Since 4 < 5, the Odd team gets the point.

(2,1) -> Since 2 > 1, the Even team gets the point.

Both teams earned 1 point.

Hence, the answer would be "Tie".

 

Constraints:

The number of nodes in the list is in the range [2, 100].
The number of nodes in the list is even.
1 <= Node.val <= 100
The value of each odd-indexed node is odd.
The value of each even-indexed node is even.
   */
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
