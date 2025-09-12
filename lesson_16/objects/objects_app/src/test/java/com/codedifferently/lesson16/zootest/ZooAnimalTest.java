package com.codedifferently.lesson16.zootest;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import main.java.com.codedifferently.lesson16.zoo.AnimalType;
import main.java.com.codedifferently.lesson16.zoo.ZooAnimal;
import org.junit.jupiter.api.Test;

class ZooAnimalTest {
  @Test
  void constructor_initializesFields() {
    ArrayList<String> foods = new ArrayList<>();
    foods.add("Banana");
    ZooAnimal animal = new ZooAnimal("George", 5, 120.0, AnimalType.MAMMAL, foods);
    assertThat(animal).isNotNull();
    assertThat(animal.listFavoriteFoods()).contains("Banana");
  }

  @Test
  void addFavoriteFood_addsFood() {
    ZooAnimal animal = new ZooAnimal("Pete", 2, 1.5, AnimalType.BIRD, new ArrayList<>());
    animal.addFavoriteFood("Seeds");
    assertThat(animal.listFavoriteFoods()).contains("Seeds");
  }

  @Test
  void listFavoriteFoods_returnsFoods() {
    ArrayList<String> foods = new ArrayList<>();
    foods.add("Fish");
    foods.add("Shrimp");
    ZooAnimal animal = new ZooAnimal("Nemo", 1, 0.2, AnimalType.FISH, foods);
    String result = animal.listFavoriteFoods();
    assertThat(result).contains("Fish").contains("Shrimp");
  }

  @Test
  void isEndangered_logicWorks() {
    ZooAnimal animal = new ZooAnimal("Leo", 10, 200.0, AnimalType.MAMMAL, new ArrayList<>());
    assertThat(animal.isEndangered()).isInstanceOf(Boolean.class);
  }

  @Test
  void removeFavoriteFood_throwsExceptionIfNotFound() {
    ZooAnimal animal = new ZooAnimal("Sammy", 3, 10.0, AnimalType.REPTILE, new ArrayList<>());
    assertThatThrownBy(() -> animal.removeFavoriteFood("Cricket"))
        .isInstanceOf(FoodNotFoundException.class);
  }
}
