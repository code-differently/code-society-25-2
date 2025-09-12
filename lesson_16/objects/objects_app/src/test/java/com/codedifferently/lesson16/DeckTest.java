package com.codedifferently.lesson16;
import com.codedifferently.lesson16.danielsonobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeckTest {
    Deck deck;
    @BeforeEach
    public void setUp(){
        deck = new Deck("Bicycle");
    }
    @Test
    public void Deckexists(){
        Deck deck = new Deck("Bicycle");
        assertThat(deck).isNotNull();
    }
    @Test
    public void getCards(){
        ArrayList<Card> cards = deck.getCards();
        assertThat(deck).isNotNull();
    }


    @Test
    public void jokerTest(){
        assertThatThrownBy(
        () -> {deck.addJokers();})
                .isInstanceOf(JokerException.class)
                .hasMessage("There are already jokers");

        deck.removeJokers();
        assertEquals(deck.getSize(),52);
        deck.addJokers();
        assertEquals(deck.getSize(), 54);
    }

    @Test
    public void draw(){
        Card card1 = deck.draw();
        Card card2 = deck.draw();

        assertNotEquals(card1, card2);

        assertEquals(52,deck.getSize());
    }

    @Test
    public void sizeTest(){
        assertEquals(deck.getSize(),54);
    }

    @Test
    public void returnToDeckTest(){
        int initalSize = deck.getSize();
        Card card1 = deck.draw();
        deck.returnToDeck(card1);
        assertEquals(card1, deck.getCards().get(0));
        assertEquals(initalSize, deck.getSize());
    }

    @Test
    public void shuffleTest(){
        Deck copy = deck;
        deck.shuffle();
        assertNotEquals(deck, copy);
        assertEquals(deck.getSize(), copy.getSize());
    }
}
