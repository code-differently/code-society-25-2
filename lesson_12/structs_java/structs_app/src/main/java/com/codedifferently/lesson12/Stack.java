package com.codedifferently.lesson12;

/** Implement the below Stack by providing code for the class methods. */
public class Stack {
  private ListNode top;

  public Stack() {
    this.top = null;
  }

  public void push(int value) {
    // Create a new node with the value
    ListNode newNode = new ListNode(value);
    // Point the new node's next to the current top
    newNode.next = top;
    // Update top to point to the new node
    top = newNode;
  }

  public int pop() {
    // Check if stack is empty
    if (isEmpty()) {
      throw new RuntimeException("Stack is empty");
    }
    // Get the value from the top node
    int value = top.val;
    // Move top to the next node
    top = top.next;
    return value;
  }

  public int peek() {
    // Check if stack is empty
    if (isEmpty()) {
      throw new RuntimeException("Stack is empty");
    }
    // Return the value at the top without removing it
    return top.val;
  }

  public boolean isEmpty() {
    // Stack is empty if top is null
    return top == null;
  }
}
