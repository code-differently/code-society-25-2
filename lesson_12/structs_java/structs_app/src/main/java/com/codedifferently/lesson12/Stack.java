package com.codedifferently.lesson12;

/** Implement the below Stack by providing code for the class methods. */
public class Stack {
  private ListNode top;

  public Stack() {
    this.top = null;
  }

  public void push(int value) {
    ListNode node = new ListNode(value);
    node.next = this.top;
    this.top = node;
  }

  public int pop() {
    // checks if the stack is empty, if so return -1
    if (this.top == null) {
      return -1;
    }
    // stores the current top value
    int value = this.top.val;
    // move the top pointer to the next node, this removes the prev node from the stack
    this.top = this.top.next;
    // return the new value after the that node has been removed
    return value;
  }

  public int peek() {
    if (this.top == null) {
      return -1;
    }
    return this.top.val;
  }

  public boolean isEmpty() {
    return true;
  }
}
