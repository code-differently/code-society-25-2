package com.codedifferently.lesson16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codedifferently.lesson16.nicolejackson.BestFriend;
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
    bestFriend = new BestFriend("Jennah", 23, true, 101.0, activities);
  }

  @Test
  public void getNameTest() {
    String expected = "Jennah";
    assertEquals(bestFriend.getName(), expected);
  }

  @Test
  public void getAgeTest() {
    int expected = 23;
    assertEquals(bestFriend.getAge(), expected);
  }

  @Test
  public void getLivesNearbyTest() {
    boolean expected = true;
    assertEquals(bestFriend.getLivesNearby(), expected);
  }

  @Test
  public void getTrustScoreTest() {
    double expected = 101.0;
    assertEquals(bestFriend.getTrustScore(), expected);
  }

  @Test
  public void getFavoriteActivitiesTest() {
    ArrayList<String> expected = new ArrayList<>();
    assertEquals(expected, bestFriend.getFavoriteActivities());
  }

  @Test
  public void isTrustworthyTest() {
    boolean expected = true;
    assertEquals(expected, bestFriend.isTrustworthy());
  }
}
