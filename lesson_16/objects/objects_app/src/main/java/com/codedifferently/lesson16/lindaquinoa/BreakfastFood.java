package com.codedifferently.lesson16.lindaquinoa;

import java.util.ArrayList;
import java.util.List;

/** A class representing a breakfast food item */
public class BreakfastFood {

  private String name;
  private double calories;
  private int preparationTimeMinutes;
  private boolean isHealthy;
  private List<String> ingredients;
  private BreakfastType type;

  /** Constructor */
  public BreakfastFood(String name, double calories, int preparationTimeMinutes, BreakfastType type)
      throws InvalidBreakfastException {

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
    this.isHealthy = determineIfHealthy();
  }

  public boolean determineIfHealthy() {
    if (calories < 300) {
      this.isHealthy = true;
      return true;
    } else {
      this.isHealthy = false;
      return false;
    }
  }

  public void addIngredient(String ingredient) throws InvalidBreakfastException {
    if (ingredient == null || ingredient.trim().isEmpty()) {
      throw new InvalidBreakfastException("Ingredient cannot be null or empty");
    }
    ingredients.add(ingredient.toLowerCase());
  }

  public String listIngredients() {
    if (ingredients.isEmpty()) {
      return "No ingredients added yet.";
    }

    StringBuilder result = new StringBuilder("Ingredients: ");
    for (int i = 0; i < ingredients.size(); i++) {
      result.append(ingredients.get(i));
      if (i < ingredients.size() - 1) {
        result.append(", ");
      }
    }
    return result.toString();
  }

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
