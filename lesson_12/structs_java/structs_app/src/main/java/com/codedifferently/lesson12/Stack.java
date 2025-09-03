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
    if (this.top == null) {
      return -1;
    }
    int value = this.top.val;
    this.top = this.top.next;
    return value;
  }

  public int peek() {
    try {
      if (this.top == null) {
        throw new Exception("Stack is empty");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return this.top.val;
  }

  public boolean isEmpty() {
    if (this.top == null) {
      return true;
    }
    return false;
  }
}
