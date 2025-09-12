package com.codedifferently.lesson16.lindaquinoa;

import java.util.ArrayList;
import java.util.List;

/** A class representing a breakfast food item */
public class BreakfastFood {

  // Required: At least 5 member variables of at least 3 different types
  private String name; // String type
  private double calories; // double type
  private int preparationTimeMinutes; // int type
  private boolean isHealthy; // boolean type
  private List<String> ingredients; // Collection type (required)
  private BreakfastType type; // Enum type (required)

  /** Constructor - required */
  public BreakfastFood(String name, double calories, int preparationTimeMinutes, BreakfastType type)
      throws InvalidBreakfastException {

    // Validation using custom exception (required by assignment)
    if (name == null || name.trim().isEmpty()) {
      throw new InvalidBreakfastException("Breakfast food name cannot be null or empty");
    }
    if (calories < 0) {
      throw new InvalidBreakfastException("Calories cannot be negative");
    }
    if (preparationTimeMinutes < 0) {
      throw new InvalidBreakfastException("Preparation time cannot be negative");
    }
    if (type == null) {
      throw new InvalidBreakfastException("Breakfast type cannot be null");
    }

    this.name = name;
    this.calories = calories;
    this.preparationTimeMinutes = preparationTimeMinutes;
    this.type = type;
    this.ingredients = new ArrayList<>();
    this.isHealthy = determineIfHealthy(); // Will be set by conditional function
  }

  /** Function with conditional expression (required) */
  public boolean determineIfHealthy() {
    // Conditional: Consider healthy if under 300 calories
    if (calories < 300) {
      this.isHealthy = true;
      return true;
    } else {
      this.isHealthy = false;
      return false;
    }
  }

  /** Function that uses collection member variable (required) */
  public void addIngredient(String ingredient) throws InvalidBreakfastException {
    if (ingredient == null || ingredient.trim().isEmpty()) {
      throw new InvalidBreakfastException("Ingredient cannot be null or empty");
    }

    // Using collection
    ingredients.add(ingredient.toLowerCase());
  }

  /** Function with loop (required) */
  public String listIngredients() {
    if (ingredients.isEmpty()) {
      return "No ingredients added yet.";
    }

    StringBuilder result = new StringBuilder("Ingredients: ");

    // Loop through ingredients (required)
    for (int i = 0; i < ingredients.size(); i++) {
      result.append(ingredients.get(i));
      if (i < ingredients.size() - 1) {
        result.append(", ");
      }
    }

    return result.toString();
  }

  // Basic getter methods
  public String getName() {
    return name;
  }

  public double getCalories() {
    return calories;
  }

  public int getPreparationTimeMinutes() {
    return preparationTimeMinutes;
  }

  public boolean isHealthy() {
    return isHealthy;
  }

  public List<String> getIngredients() {
    return ingredients;
  }

  public BreakfastType getType() {
    return type;
  }
}
