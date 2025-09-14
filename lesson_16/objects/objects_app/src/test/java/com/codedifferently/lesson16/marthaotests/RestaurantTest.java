package com.codedifferently.lesson16.marthaotests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson16.marthao.CuisineType;
import com.codedifferently.lesson16.marthao.Restaurant;

public class RestaurantTest {
    private Restaurant restaurant;

    @BeforeEach
    public void setUp() {
        restaurant = new Restaurant("Martha's Kitchen", 5.0, CuisineType.NIGERIAN, "123 Apple Street", new String[] {"Dinner"}); 
    }

    @Test 
    public void testGetName() {
        //Arrange
        String expected = "Martha's Kitchen";
        //Act
        String actual = restaurant.getName();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetName() {
        //Arrange
        String expected = "Joe's Diner";
        //Act
        restaurant.setName("Joe's Diner");
        String actual = restaurant.getName();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testGetRating() {
        //Arrange
        double expected = 5.0;
        //Act
        double actual = restaurant.getRating();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetRating() {
        //Arrange
        double expected = 4.5;
        //Act
        restaurant.setRating(4.5);
        double actual = restaurant.getRating();
        //Assert
        assertEquals(expected, actual);
    }

    @Test 
    public void testGetCuisineType() {
        //Arrange
        CuisineType expected = CuisineType.NIGERIAN;
        //Act
        CuisineType actual = restaurant.getCuisineType();
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    public void testSetCuisineType() {
        //Arrange
        CuisineType expected = CuisineType.ITALIAN;
        //Act
        restaurant.setCuisineType(CuisineType.ITALIAN);
        CuisineType actual = restaurant.getCuisineType();
        //Assert
        assertEquals(expected, actual);
    }

   @Test
    public void testGetMenuByTime_Success() throws Exception {
    //Arrange
    String[] menus = {"Breakfast", "Lunch", "Dinner"};
    restaurant = new Restaurant("Bob's Burgers", 4.0, CuisineType.AMERICAN, "123 Rd", menus);
    //Act
    String actual = restaurant.getMenuByTime("Afternoon");
    //Assert
    assertEquals("Lunch", actual);
    }

  
}