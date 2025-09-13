package com.codedifferently.lesson16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    
}
