package com.codedifferently.lesson14.exceptions;

import java.util.List;

/** Exception thrown when attempting partial refunds (must refund all items) */
public class PartialRefundNotAllowedException extends Exception {
  private final List<String> missingItems;

  public PartialRefundNotAllowedException(List<String> missingItems) {
    super(
        String.format(
            "Partial refunds are not allowed. Missing items for complete refund: %s",
            String.join(", ", missingItems)));
    this.missingItems = missingItems;
  }

  public List<String> getMissingItems() {
    return missingItems;
  }
}
