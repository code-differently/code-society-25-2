import { ListNode } from './list_node.js';

export class Lesson12 {
  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public gameResult(head: ListNode | null): string {
    let evenPoints = 0;
    let oddPoints = 0;

    // Traverse the list in pairs
    let current = head;
    while (current !== null && current.next !== undefined) {
      // current is at even index, current.next is at odd index
      const evenValue = current.val;
      const oddValue = current.next.val;

      if (evenValue > oddValue) {
        evenPoints++;
      } else if (oddValue > evenValue) {
        oddPoints++;
      }
      // If equal, no points awarded (though this shouldn't happen based on constraints)

      // Move to the next pair
      current = current.next.next || null;
    }

    if (evenPoints > oddPoints) {
      return 'Even';
    } else if (oddPoints > evenPoints) {
      return 'Odd';
    } else {
      return 'Tie';
    }
  }
}
