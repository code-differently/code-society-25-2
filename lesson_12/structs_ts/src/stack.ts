import { ListNode } from './list_node.js';

export class Stack {
  private top: ListNode | undefined;

  constructor() {
    this.top = undefined;
  }

  push(value: number): void {
    const newNode = new ListNode(value);
    if (this.top === undefined) {
      this.top = newNode;
    } else {
      newNode.next = this.top;
      this.top = newNode;
    }
  }

  pop(): number | undefined {
    if (this.top === undefined) {
      throw new Error('Empty Stack');
    }
    const value : number = this.top.val;
    this.top = this.top.next;
    return value; 
  }

  peek(): number | null {
    if (this.top === undefined) {
      throw new Error('Empty Stack');
    }
    return this.top.val; 
  }

  isEmpty(): boolean {
    return this.top === undefined;
  }
}
