import { ListNode } from './list_node.js';

export class Lesson12 {
  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  /**
   * Determines the winner of the linked list game.
   * If the list has an odd number of nodes, the last node is ignored for pairwise comparison.
   * @param head The head node of the linked list.
   * @returns 'Even' if the even team wins, 'Odd' if the odd team wins, or 'Tie' if there is no winner.
   */
  public gameResult(head: ListNode | null): string {
    if (head === null) {
      return 'Tie';
    }

    let evenWins = 0;
    let oddWins = 0;
    let current: ListNode | null = head;

    // Process pairs of nodes (Even team vs Odd team)
    while (current !== null && current.next !== null) {
      // Apply game logic based on current.val
      const evenValue = current.val;
      const oddValue = current.next?.val ?? 0;

      if (evenValue > oddValue) {
        evenWins++;
      } else if (oddValue > evenValue) {
        oddWins++;
      }
      // If equal, no one wins that round
      current = current.next && current.next.next ? current.next.next : null;
    }

    if (evenWins > oddWins) {
      return 'Even';
    } else if (oddWins > evenWins) {
      return 'Odd';
    } else {
      return 'Tie';
    }
    return '';
  }
} 