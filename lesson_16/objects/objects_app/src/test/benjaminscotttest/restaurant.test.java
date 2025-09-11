package com.codedifferently.lesson16;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson16.benjaminscott.Restaurant;

class restaurantTest {
    @Test
    public void testConstructor() {
        Restaurant restaurant = new Restaurant();
    }
    @Test
    public void testGetName() {
        String expected = "Pasta Place";
        Restaurant restaurant = new Restaurant("Pasta Place");
        String actual = restaurant.getName();
        assertEquals(expected, actual);
    }
}
