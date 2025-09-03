package com.codedifferently.lesson12;

import java.util.EmptyStackException;

public class Stack {
  private ListNode top;

  public Stack() {
    this.top = null;
  }

  public void push(int value) {
    ListNode newNode = new ListNode(value);
    newNode.next = top;
    top = newNode;
  }

  public int pop() {
    if (top == null) {
            throw new EmptyStackException(); 
        }
        int value = top.val;
        top = top.next;
        return value;
  }

  public int peek() {
        if (top == null) {
            throw new EmptyStackException();  
        }
        return top.val;
  }

  public boolean isEmpty() {
    return top == null;
  }
}
