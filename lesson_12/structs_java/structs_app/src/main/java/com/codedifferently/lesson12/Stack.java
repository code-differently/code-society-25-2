package com.codedifferently.lesson12;

/** Implement the below Stack by providing code for the class methods. */
public class Stack {
  private ListNode top;

  public Stack() {
    this.top = null;
  }

  public void push(int value) {
    if (top == null) {
      top = new ListNode(value);
    } else {
      ListNode newNode = new ListNode(value);
      newNode.next = top;
      top = newNode;
    }
  }

  public int pop() {
    if (top != null) {
      int value = top.val;
      top = top.next;
      return value;
    }
    return -1;
  }

  public int peek() {
    if (top != null) {
      return top.val;
    }
    return -1;
  }

  public boolean isEmpty() {
    return top == null;
  }
}
