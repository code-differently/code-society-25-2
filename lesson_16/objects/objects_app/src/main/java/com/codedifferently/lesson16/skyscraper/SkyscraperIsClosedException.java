package com.codedifferently.lesson16.skyscraper;

public class SkyscraperIsClosedException extends IllegalStateException {
  public SkyscraperIsClosedException(String message) {
    super(message);
  }
}
