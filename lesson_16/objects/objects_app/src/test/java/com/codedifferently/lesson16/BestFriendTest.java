package com.codedifferently.lesson16;

import static org.junit.jupiter.api.Assertions.*;

import com.codedifferently.lesson16.nicolejackson.BestFriend;
import com.codedifferently.lesson16.nicolejackson.FriendLevel;
import com.codedifferently.lesson16.nicolejackson.InvalidActivityException;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BestFriendTest {
  private BestFriend bestFriend;

  @BeforeEach
  public void setUp() {
    ArrayList<String> activities =
        new ArrayList<>(Arrays.asList("Baking", "Movie Nights", "Catching Up"));
    bestFriend = new BestFriend("Jennah", 23, true, 101.0, activities, FriendLevel.BEST);
  }

  @Test
  public void getNameTest() {
    assertEquals("Jennah", bestFriend.getName());
  }

  @Test
  public void getAgeTest() {
    assertEquals(23, bestFriend.getAge());
  }

  @Test
  public void getLivesNearbyTest() {
    assertTrue(bestFriend.getLivesNearby());
  }

  @Test
  public void getTrustScoreTest() {
    assertEquals(101.0, bestFriend.getTrustScore());
  }

  @Test
  public void getFavoriteActivitiesTest() {
    ArrayList<String> expected =
        new ArrayList<>(Arrays.asList("Baking", "Movie Nights", "Catching Up"));
    assertEquals(expected, bestFriend.getFavoriteActivities());
  }

  @Test
  public void isTrustworthyTest() {
    assertTrue(bestFriend.isTrustworthy());
  }

  @Test
  public void addFavoriteActivityTest() throws InvalidActivityException {
    bestFriend.addFavoriteActivity("Hiking");
    assertTrue(bestFriend.getFavoriteActivities().contains("Hiking"));
  }

  @Test
  public void addFavoriteActivityThrowsExceptionTest() {
    assertThrows(InvalidActivityException.class, () -> bestFriend.addFavoriteActivity(""));
  }

  @Test
  public void getLevelTest() {
    assertEquals(FriendLevel.BEST, bestFriend.getLevel());
  }
}
