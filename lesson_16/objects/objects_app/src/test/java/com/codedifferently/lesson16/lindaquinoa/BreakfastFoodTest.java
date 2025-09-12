package com.codedifferently.lesson16.lindaquinoa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;

/** Test class for BreakfastFood - requires at least 5 test methods */
public class BreakfastFoodTest {

  /** Test 1: Test breakfast food creation and basic properties */
  @Test
  void testBreakfastFoodCreationAndProperties() throws InvalidBreakfastException {
    BreakfastFood cereal = new BreakfastFood("Cheerios", 110, 2, BreakfastType.CEREAL);

    assertThat(cereal.getName()).isEqualTo("Cheerios");
    assertThat(cereal.getCalories()).isEqualTo(110);
    assertThat(cereal.getPreparationTimeMinutes()).isEqualTo(2);
    assertThat(cereal.getType()).isEqualTo(BreakfastType.CEREAL);
    assertThat(cereal.getIngredients()).isEmpty();
  }

  /** Test 2: Test invalid breakfast food creation throws exceptions */
  @Test
  void testInvalidBreakfastFoodCreation() {
    assertThatThrownBy(() -> new BreakfastFood(null, 100, 5, BreakfastType.TOAST))
        .isInstanceOf(InvalidBreakfastException.class)
        .hasMessage("Breakfast food name cannot be null or empty");

    assertThatThrownBy(() -> new BreakfastFood("Toast", -10, 5, BreakfastType.TOAST))
        .isInstanceOf(InvalidBreakfastException.class)
        .hasMessage("Calories cannot be negative");
  }

  /** Test 3: Test breakfast healthiness determination */
  @Test
  void testBreakfastHealthinessDetermination() throws InvalidBreakfastException {
    BreakfastFood healthyOatmeal =
        new BreakfastFood("Steel Cut Oats", 150, 15, BreakfastType.OATMEAL);
    BreakfastFood pancakes = new BreakfastFood("Fluffy Pancakes", 450, 20, BreakfastType.PANCAKES);

    assertThat(healthyOatmeal.isHealthy()).isTrue();
    assertThat(pancakes.isHealthy()).isFalse();
  }

  /** Test 4: Test breakfast ingredient management */
  @Test
  void testBreakfastIngredientManagement() throws InvalidBreakfastException {
    BreakfastFood healthyOatmeal =
        new BreakfastFood("Steel Cut Oats", 150, 15, BreakfastType.OATMEAL);

    healthyOatmeal.addIngredient("oats");
    healthyOatmeal.addIngredient("water");

    assertThat(healthyOatmeal.getIngredients()).hasSize(2);
    assertThat(healthyOatmeal.getIngredients()).contains("oats", "water");
  }

  /** Test 5: Test breakfast ingredient listing functionality */
  @Test
  void testBreakfastIngredientListing() throws InvalidBreakfastException {
    BreakfastFood healthyOatmeal =
        new BreakfastFood("Steel Cut Oats", 150, 15, BreakfastType.OATMEAL);

    healthyOatmeal.addIngredient("oats");
    healthyOatmeal.addIngredient("honey");

    String result = healthyOatmeal.listIngredients();
    assertThat(result).contains("oats");
    assertThat(result).contains("honey");
    assertThat(result).startsWith("Ingredients:");
  }
}
