package com.codedifferently.lesson16.zoo;

import java.util.ArrayList;

public class ZooAnimal {
  private String name;
  private int age;
  private double weight;
  private AnimalType type;
  private ArrayList<String> favoriteFoods;

  public ZooAnimal(
      String name, int age, double weight, AnimalType type, ArrayList<String> favoriteFoods) {
    this.name = name;
    this.age = age;
    this.weight = weight;
    this.type = type;
    this.favoriteFoods = favoriteFoods;
  }

  public boolean isEndangered() {
    
    if (type == AnimalType.MAMMAL && age > 15) return true;
    if (type == AnimalType.BIRD && age < 1) return true;
    return false;
  }

  public void addFavoriteFood(String food) {
    if (favoriteFoods == null) favoriteFoods = new ArrayList<>();
    favoriteFoods.add(food);
  }

  public void removeFavoriteFood(String food) throws FoodNotFoundException {
    if (favoriteFoods == null || !favoriteFoods.contains(food)) {
      throw new FoodNotFoundException("Food not found: " + food);
    }
    favoriteFoods.remove(food);
  }

  public String listFavoriteFoods() {
    if (favoriteFoods == null || favoriteFoods.isEmpty()) return "";
    StringBuilder sb = new StringBuilder();
    for (String food : favoriteFoods) {
      sb.append(food).append(", ");
    }
    
    return sb.substring(0, sb.length() - 2);
  }

  
  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public double getWeight() {
    return weight;
  }

  public AnimalType getType() {
    return type;
  }

  public ArrayList<String> getFavoriteFoods() {
    return favoriteFoods;
  }
}
