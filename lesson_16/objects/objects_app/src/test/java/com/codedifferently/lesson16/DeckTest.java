package com.codedifferently.lesson16;
import com.codedifferently.lesson16.danielsonobject.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

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
    public void removeJokerTest() throws JokerException {
        deck.removeJokers();
        assertEquals(deck.getSize(), 52);
        for (Card card : deck.getCards()){
            if (card.getRank() == 14){
                fail("There is still a Joker in the deck");
            }
        }
    }

    @Test
    public void addJokerTest() throws JokerException {
        assertThatThrownBy(
                () -> {deck.addJokers();})
                .isInstanceOf(JokerException.class)
                .hasMessage("Jokers are already accounted for");
        deck.removeJokers();
        deck.addJokers();
        assertEquals(deck.getSize(), 54);
        int count = 0;
        for (Card card : deck.getCards()){
            if (card.getSuit() == Suit.NONE){
                count ++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void drawTest(){
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
    public void addToDeckTest(){
        int initialSize = deck.getSize();
        Card card1 = deck.draw();
        deck.addToDeck(card1);
        assertEquals(card1, deck.getCards().get(0));
        assertEquals(initialSize, deck.getSize());
    }

    @Test
    public void shuffleTest(){
        Deck copy = new Deck("Bicyle");
        assertEquals(deck.getCards(), copy.getCards());
        deck.shuffle();
        assertNotEquals(deck.getCards(), copy.getCards());
        assertEquals(deck.getSize(), copy.getSize());
    }
}
