package com.codedifferently.lesson12;

public class Lesson12 {

  /**
   * Compare nodes in pairs: (a,b), (c,d), ...
   * a>b => Even gets a point; a<b => Odd gets a point; equal => no points.
   * Return "Even", "Odd", or "Tie".
   */
  public String gameResult(ListNode head) {
    int evenPoints = 0;
    int oddPoints = 0;

    ListNode curr = head;
    while (curr != null && curr.next != null) {
      int first = curr.val;       // if your ListNode uses 'value', change to curr.value
      int second = curr.next.val; // same note as above

      if (first > second) {
        evenPoints++;
      } else if (first < second) {
        oddPoints++;
      }
      curr = curr.next.next; // move to next pair
    }

    if (evenPoints > oddPoints) return "Even";
    if (oddPoints > evenPoints) return "Odd";
    return "Tie";
  }
}
