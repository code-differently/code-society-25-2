package com.codedifferently.lesson16.Dice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dice {

  private final int sides;
  private final String color;
  private final boolean loaded;
  private final DiceMaterial material;
  private final List<Integer> rollHistory;

  private final Random random;

  public Dice(int sides, String color, boolean loaded, DiceMaterial material) {
    this.sides = sides;
    this.color = color;
    this.loaded = loaded;
    this.material = material;
    this.rollHistory = new ArrayList<>();
    this.random = new Random();
  }

  public int roll() throws InvalidRollException {
    if (sides < 2) {
      throw new InvalidRollException("A dice must have at least 2 sides.");
    }

    int result;

    result = loaded ? random.nextInt(sides / 2, sides) + 1 : random.nextInt(sides) + 1;

    rollHistory.add(result);
    return result;
  }

  public void showHistory() {
    if (rollHistory.isEmpty()) {
      System.out.println("No rolls yet!");
      return;
    }

    System.out.print("Roll History: ");
    for (int roll : rollHistory) {
      System.out.print(roll + " ");
    }
    System.out.println();
  }

  public double getAverageRoll() {
    if (rollHistory.isEmpty()) {
      return 0.0;
    }

    int sum = 0;
    for (int roll : rollHistory) {
      sum += roll;
    }
    return (double) sum / rollHistory.size();
  }

  public int getSides() {
    return sides;
  }

  public String getColor() {
    return color;
  }

  public boolean isLoaded() {
    return loaded;
  }

  public DiceMaterial getMaterial() {
    return material;
  }
}
