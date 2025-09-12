package com.codedifferently.lesson16.danielsonobject;

import com.codedifferently.lesson16.danielsonobject.Card;
import com.codedifferently.lesson16.danielsonobject.Suit;
import java.util.ArrayList;

public class Deck {
  private String brand;
  private ArrayList<Card> cards;
  private int max_size = 54;

  public Deck(String brand){
    this.brand = brand;
    this.cards = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      if (suit == Suit.NONE) {
        cards.add(new Card(Suit.NONE, 14)); // This will represent the Jokers.
        cards.add(new Card(Suit.NONE, 14));
      }
      for (int rank = 1; rank <= 13; rank++) {
        cards.add(new Card(suit, rank));
      }
    }
  }

  public void shuffle() {}

  public Card draw() {
    return null;
  }

  public int getSize() {
    return cards.size();
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public void returnToDeck(Card card) {}

  public void shuffleIntoDeck(Card card){

  }

  public void removeJokers() {
    max_size = 52;
  }

  public void addJokers() {
    max_size = 54;
  }
}
