package com.codedifferently.lesson12;

/** Linked-list-backed stack. */
public class Stack {
  private ListNode top;

  public Stack() {
    this.top = null;
  }

  /** Push value onto the stack. */
  public void push(int value) {
    ListNode node = new ListNode(value); // assumes constructor taking (int)
    node.next = top;
    top = node;
  }

  /** Pop and return top value. Throws if empty. */
  public int pop() {
    if (top == null) {
      throw new IllegalStateException("Stack is empty");
    }
    int val = top.val; // change to top.value if your node field is named 'value'
    top = top.next;
    return val;
  }

  /** Return top value without removing. Throws if empty. */
  public int peek() {
    if (top == null) {
      throw new IllegalStateException("Stack is empty");
    }
    return top.val; // or top.value
  }

  /** Is the stack empty? */
  public boolean isEmpty() {
    return top == null;
  }
}
