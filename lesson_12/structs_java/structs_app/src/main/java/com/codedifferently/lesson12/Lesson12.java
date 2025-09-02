package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Provide the solution to LeetCode 3062 here:
   * https://github.com/yang-su2000/Leetcode-algorithm-practice/tree/master/3062-winner-of-the-linked-list-game
   */
  public String gameResult(ListNode head) {
    int oddSum = 0;    
    int evenSum = 0;   
    int position = 1;  
    
    ListNode current = head;  // Pointer to traverse the list
    
    // Loop through each node in the linked list
    while (current != null) {
        if (position % 2 == 1) {  
            oddSum += current.val;
        } else {                  
            evenSum += current.val;
        }
        
        current = current.next;   
        position++;               
    }
    
    // Compare sums and return winner
    if (oddSum > evenSum) {
        return "Odd";
    } else if (evenSum > oddSum) {
        return "Even";
    } else {
        return "Tie";
    }
}
}