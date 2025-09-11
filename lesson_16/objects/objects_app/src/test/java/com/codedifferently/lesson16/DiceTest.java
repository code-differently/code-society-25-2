package com.codedifferently.lesson16;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiceTest {

    private Dice dice;

    @BeforeEach
    void setUp() {
        
        dice = new Dice(6, "Red", false, DiceMaterial.PLASTIC);
    }

    
    @Test
    void testRollWithinRange() throws InvalidRollException {
        int result = dice.roll();
        assertTrue(result >= 1 && result <= 6, "Roll should be between 1 and 6");
    }

    
    @Test
    void testInvalidRollException() {
        Dice badDice = new Dice(1, "Blue", false, DiceMaterial.METAL);
        assertThrows(InvalidRollException.class, badDice::roll);
    }

    
    @Test
    void testRollHistoryRecordsRolls() throws InvalidRollException {
        dice.roll();
        dice.roll();
        assertFalse(dice.getAverageRoll() == 0.0, "History should not be empty after rolls");
    }

    
    @Test
    void testAverageRoll() throws InvalidRollException {
        dice.roll(); 
        dice.roll();
        double avg = dice.getAverageRoll();
        assertTrue(avg >= 1.0 && avg <= 6.0, "Average should be within dice range");
    }

    
    @Test
    void testGetters() {
        assertEquals(6, dice.getSides());
        assertEquals("Red", dice.getColor());
        assertFalse(dice.isLoaded());
        assertEquals(DiceMaterial.PLASTIC, dice.getMaterial());
    }
}

