package com.codedifferently.lesson16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.codedifferently.lesson16.danielsonobject.Card;
import com.codedifferently.lesson16.danielsonobject.Suit;
import org.junit.jupiter.api.Test;

public class CardTest {
  // Test cases for Card class
  @Test
  public void testCardCreation() {
    Card card = new Card(Suit.HEARTS, 10);
    assertThat(card.getSuit()).isEqualTo(Suit.HEARTS);
    assertThat(card.getRank()).isEqualTo(10);

    card = new Card(Suit.SPADES, 3);
    assertThat(card.getSuit()).isEqualTo(Suit.SPADES);
    assertThat(card.getRank()).isEqualTo(3);

    assertThatThrownBy(() -> new Card(Suit.HEARTS, 0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Rank must be between 1 and 14");
  }

  @Test
  public void JokerCreation(){
    Card card = new Card();
    assertThat(card.getSuit()).isEqualTo(Suit.NONE);
    assertThat(card.getRank()).isEqualTo(14);
  }

  @Test
  public void testCompareTo() {
    Card card1 = new Card(Suit.HEARTS, 10);
    Card card2 = new Card(Suit.SPADES, 3);
    Card card3 = new Card(Suit.DIAMONDS, 10);
    Card card4 = new Card(Suit.NONE, 14); // Joker

    assertThat(card1.compareTo(card2)).isEqualTo(card1); // 10 > 3
    assertThat(card2.compareTo(card1)).isEqualTo(card1); // 3 < 10
    assertThat(card1.compareTo(card4)).isEqualTo(card4); // 10 < Joker
    assertThat(card4.compareTo(card2)).isEqualTo(card4); // Joker >
    assertThat(card1.compareTo(card3)).isNull(); // 10
  }
}
