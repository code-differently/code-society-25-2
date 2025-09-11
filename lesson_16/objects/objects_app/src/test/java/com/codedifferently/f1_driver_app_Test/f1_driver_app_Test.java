package com.codedifferently.f1drivers;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class F1DriverTest {

    @Test
    public void testDriverCreation() throws InvalidDriverAgeException {
        F1Driver driver = new F1Driver("Lewis Hamilton", 38, Team.MERCEDES, true, 4500, 190);
        assertEquals("Lewis Hamilton", driver.getName());
        assertEquals(4500, driver.getCareerPoints());
        assertEquals(190, driver.getPodiums());
    }

    @Test
    public void testInvalidDriverAgeException() {
        assertThrows(InvalidDriverAgeException.class, () -> {
            new F1Driver("Young Driver", 16, Team.FERRARI, false, 0, 0);
        });
    }

    @Test
    public void testIsVeteranTrue() throws InvalidDriverAgeException {
        F1Driver driver = new F1Driver("Fernando Alonso", 42, Team.ASTON_MARTIN, true, 2200, 100);
        assertTrue(driver.isVeteran());
    }

    @Test
    public void testAddRaceWin() throws InvalidDriverAgeException {
        F1Driver driver = new F1Driver("Max Verstappen", 26, Team.RED_BULL, true, 3000, 80);
        driver.addRaceWin("Monaco GP");
        driver.addRaceWin("Silverstone GP");
        assertDoesNotThrow(() -> driver.printRaceWins());
    }

    @Test
    public void testAddPodiumUpdatesStats() throws InvalidDriverAgeException {
        F1Driver driver = new F1Driver("Charles Leclerc", 26, Team.FERRARI, false, 900, 30);
        driver.addPodium(18); // 2nd place points
        assertEquals(31, driver.getPodiums());
        assertEquals(918, driver.getCareerPoints());
    }
}
