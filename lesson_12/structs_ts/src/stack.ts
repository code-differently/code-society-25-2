import { ListNode } from './list_node.js';

export class Stack {
  private top: ListNode | undefined;

  constructor() {
    this.top = undefined;
  }

  push(value: number): void {
    const newNode = new ListNode(value);
    newNode.next = this.top;
    this.top = newNode;
  }

  pop(): number | undefined {
    if (this.isEmpty()) {
      throw new Error('Stack is empty');
    }

    const poppedValue = this.top?.val;
    this.top = this.top?.next;
    return poppedValue;
  }

  peek(): number | null {
    if (this.isEmpty()) {
      throw new Error('Stack is empty');
    }
    return this.top?.val ?? null;
  }

  isEmpty(): boolean {
    return this.top === undefined;
  }
}
