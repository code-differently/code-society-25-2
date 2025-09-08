package com.codedifferently.lesson14.exceptions;

/**
 * Exception thrown when an invalid category is specified
 */
public class InvalidCategoryException extends Exception {
    private final String category;

    public InvalidCategoryException(String category) {
        super(String.format("Category '%s' is invalid. Valid categories: ELECTRONICS, CLOTHING, BOOKS, HOME, SPORTS", category));
        this.category = category;
    }

    public String getCategory() { return category; }
}
