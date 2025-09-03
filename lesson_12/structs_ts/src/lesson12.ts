import { ListNode } from './list_node.js';

export class Lesson12 {

  public gameResult(head: ListNode | null): string {
    let oddPoints = 0;
    let evenPoints = 0;
    let current: ListNode | null = head;

    while (current !== null && current.next !== undefined) {
      const evenVal = current.val;
      const oddVal = current.next.val;

      if (oddVal > evenVal) {
        oddPoints++;
      } else if (evenVal > oddVal) {
        evenPoints++;
      }

      current = current.next.next ?? null;
    }

    if (oddPoints > evenPoints) {
      return 'Odd';
    } else if (evenPoints > oddPoints) {
      return 'Even';
    } else {
      return 'Tie';
    }
  }
}
