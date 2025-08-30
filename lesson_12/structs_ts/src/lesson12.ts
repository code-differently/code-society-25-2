import { ListNode } from './list_node.js';

export class Lesson12 {
  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public gameResult(head: ListNode | null): string {
    let evenTeam = 0;
    let oddTeam = 0;

    while (head != null) {
      if(head.next == null) {
        break;
      }

      if (head.val > head.next.val) {
        evenTeam++;
      } else {
        oddTeam++;
      }
      head = head.next.next ?? null;
    }

    if (evenTeam == oddTeam) {
      return "Tie";
    }

    return evenTeam > oddTeam ? "Even" : "Odd";
  }
}
