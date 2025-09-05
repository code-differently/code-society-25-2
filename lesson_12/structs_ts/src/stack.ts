import { ListNode } from './list_node.js';

export class Stack {
  private top: ListNode | undefined;

  constructor() {
    this.top = undefined;
  }

  push(value: number): void {
    this.top = new ListNode(value, this.top);
  }

  pop(): number | undefined {
    if (this.top === undefined) {
      return undefined;
    }
    const value = this.top.val;
    this.top = this.top.next;
    return value;
  }

  peek(): number {
    if (this.top === undefined) {
      throw new Error('No elements in stack');
    }
    return this.top.val;
  }

  isEmpty(): boolean {
    return this.top === undefined;
  }
}
