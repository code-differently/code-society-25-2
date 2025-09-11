package com.codedifferently.skyscraper;

public class SkyscraperIsClosedException extends IllegalStateException {
    public SkyscraperIsClosedException(String message) {
        super(message);
    }
}
