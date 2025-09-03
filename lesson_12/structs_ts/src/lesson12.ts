import { ListNode } from './list_node.js';

export class Lesson12 {
  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  gameResult(head: ListNode | null): string {
    let evenScore = 0;
    let oddScore = 0;

    let current: ListNode | null = head;

    while (current?.next != null) {
      const next = current.next;
      if (current.val > next.val) {
        evenScore++;
      } else if (current.val < next.val) {
        oddScore++;
      }

      current = next.next ?? null;
    }

    if (evenScore > oddScore) {
      return 'Even';
    } else if (oddScore > evenScore) {
      return 'Odd';
    } else {
      return 'Tie';
    }
  }
}
