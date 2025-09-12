package com.codedifferently.lesson16.danielsonobject;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
  private Random rand = new Random();
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

  public String getBrand(){
    return brand;
  }
  public void shuffle() {}

  public Card draw() {
    if (cards.isEmpty()){
      throw new IllegalStateException("There are no Cards to draw");
    }
    return cards.removeFirst();
  }

  public int getSize() {
    return cards.size();
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public void returnToDeck(Card card) {
    cards.addFirst(card);
  }

  public void shuffleIntoDeck(Card card){
    if (cards.size() + 1 > max_size){
      throw new IllegalStateException("Deck limit reached");
    }
    int insert = rand.nextInt(0, max_size);
    cards.add(insert, card);

  }

  public void removeJokers() {
    cards.remove(new Card());
    /*
    for(Card card : cards){
      if (card.getSuit == Suit.NONE){
          cards.remove(card);
      }
    }
     */
    max_size = 52;
  }

  public void addJokers() {
    if (max_size == 52) {
      max_size = 54;
      shuffleIntoDeck(new Card());
      shuffleIntoDeck(new Card());
    }
    else if (max_size == 54){
      throw new RuntimeException("Jokers are already accounted for");
    }
  }
}
