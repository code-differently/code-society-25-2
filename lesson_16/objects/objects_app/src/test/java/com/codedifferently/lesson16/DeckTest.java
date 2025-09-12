package com.codedifferently.lesson16;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.codedifferently.lesson16.danielsonobject.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DeckTest {
  Deck deck;

  @BeforeEach
  public void setUp() {
    deck = new Deck("Bicycle");
  }

  @Test
  public void Deckexists() {
    Deck deck = new Deck("Bicycle");
    assertThat(deck).isNotNull();
  }

  @Test
  public void getCards() {
    ArrayList<Card> cards = deck.getCards();
    assertThat(cards).isNotNull();
  }

  @Test
  public void removeJokerTest() throws JokerException {
    deck.removeJokers();
    assertEquals(deck.getSize(), 52);
    for (Card card : deck.getCards()) {
      if (card.getRank() == 14) {
        fail("There is still a Joker in the deck");
      }
    }
  }

  @Test
  public void addJokerTest() throws JokerException {
    assertThatThrownBy(() -> deck.addJokers())
        .isInstanceOf(JokerException.class)
        .hasMessage("Jokers are already accounted for");

    deck.removeJokers();
    deck.addJokers();
    assertEquals(deck.getSize(), 54);
    int count = 0;
    for (Card card : deck.getCards()) {
      if (card.getSuit() == Suit.NONE) {
        count++;
      }
    }
    assertEquals(2, count);
  }

  @Test
  public void brandTest() {
    assertEquals("Bicycle", deck.getBrand());
    assertEquals("Copag", new Deck("Copag").getBrand());
  }

  @Test
  public void drawTest() {
    Card card1 = deck.draw();
    Card card2 = deck.draw();

    assertNotEquals(card1, card2);

    assertEquals(52, deck.getSize());
  }

  @Test
  public void sizeTest() {
    assertEquals(deck.getSize(), 54);
  }

  @Test
  public void addToDeckTest() {
    int initialSize = deck.getSize();
    Card card1 = deck.draw();
    deck.addToDeck(card1);
    assertEquals(card1, deck.getCards().getFirst());
    assertEquals(initialSize, deck.getSize());
  }

  @Test
  public void shuffleTest() {
    Deck copy = new Deck("Bicyle");
    assertEquals(deck.getCards(), copy.getCards());
    deck.shuffle();
    assertNotEquals(deck.getCards(), copy.getCards());
    assertEquals(deck.getSize(), copy.getSize());
  }
}
