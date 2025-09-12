package com.codedifferently.lesson16;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codedifferently.lesson16.nicolejackson.BestFriend;
import com.codedifferently.lesson16.nicolejackson.FriendshipLevel;

public class BestFriendTest {
    private BestFriend bestFriend;
    
    @BeforeEach
    public void setUp(){
        ArrayList<String> activities = new ArrayList<>(Arrays.asList("Baking","Movie Nights","Catching Up"));
        bestFriend = new BestFriend("Jennah",23,true, FriendshipLevel.FAMILY, 101.0, activities);

    }
    @Test
    public void getNameTest(){
        String expected = "Jennah";
        assertEquals(bestFriend.getName(), expected);
    }
}
