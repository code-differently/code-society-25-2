import { ListNode } from './list_node.js';

export class Lesson12 {
  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
  */
  public gameResult(head: ListNode | null): string {
    if (head === null || head.next === null) {
      return 'Tie';
    }
    let odd = 0;
    let even = 0;
    let current: ListNode | null = head;
    let next: ListNode | null = head.next ? head.next : null;

    while (current !== null && next !== null) {
      if (current.val > next.val) {
        even++;
      } else if (current.val < next.val) {
        odd++;
      }
      current = next.next ? next.next : null;
      if (current !== null) {
        next = current.next ? current.next : null;
      } else {
        next = null;
      }
    }

    if (odd < even) {
      return 'Even';
    } else if (odd > even) {
      return 'Odd';
    } 
    return 'Tie';
  }
}
