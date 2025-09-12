/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson16;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson16.danielcustomobject.Home;
import com.codedifferently.lesson16.danielcustomobject.HomeType;

/**
 *
 * @author vscode
 */
public class HomeTest {

    private Home home;

    @BeforeEach
    public void setUp() {
        home = new Home(HomeType.DUPLEX, List.of("Living Room", "Kitchen", "Bedroom"), 2, 250000.0, "Downtown");
    }

    @Test
    public void getterAndSetterTest() {
        // Test HomeType getter and setter
        assertTrue(home.getHomeType() == HomeType.DUPLEX);
        home.setHomeType(HomeType.LUXURY);
        assertTrue(home.getHomeType() == HomeType.LUXURY);

        // Test rooms getter and setter
        assertTrue(home.getRooms().equals(List.of("Living Room", "Kitchen", "Bedroom")));
        home.setRooms(List.of("Living Room", "Kitchen", "Bedroom", "Bathroom"));
        assertTrue(home.getRooms().equals(List.of("Living Room", "Kitchen", "Bedroom", "Bathroom")));

        // Test numberOfStories getter and setter
        assertTrue(home.getNumberOfStories() == 2);
        home.setNumberOfStories(3);
        assertTrue(home.getNumberOfStories() == 3);

        // Test price getter and setter
        assertTrue(home.getPrice() == 250000.0);
        home.setPrice(300000.0);
        assertTrue(home.getPrice() == 300000.0);

        // Test neighborhood getter
        assertTrue(home.getNeighborhood().equals("Downtown"));

    }

    






}
