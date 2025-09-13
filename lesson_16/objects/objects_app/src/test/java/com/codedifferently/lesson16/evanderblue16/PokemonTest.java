package com.codedifferently.lesson16.evanderblue16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PokemonTest {
  private Pokemon pokemon;

  @BeforeEach
  void setUp() {
    // Assuming Pokemon constructor takes (name, type, level, behavior, attack)
    pokemon =
        new Pokemon("Pikachu", PokemonType.ELECTRIC, 20, "Docile", new String[] {"Thunderbolt"});
  }

  @Test
  public void testGetters() {
    assertEquals("Pikachu", pokemon.getPokeName());
    // Compare enum using == (preferred method)
    assertEquals(PokemonType.ELECTRIC, pokemon.getPokeType());
    assertEquals(20, pokemon.getLvl());
    assertEquals("Docile", pokemon.getBehavior());
    assertArrayEquals(new String[] {"Thunderbolt"}, pokemon.getPokeAttack());
  }

  @Test
  public void testSetters() {
    pokemon.setPokeName("Pikachu");
    assertEquals("Pikachu", pokemon.getPokeName());

    // Setting enum value - use the enum constant
    pokemon.setPokeType(PokemonType.ELECTRIC);
    // Compare enum using == (this is the preferred way)
    assertEquals(PokemonType.ELECTRIC, pokemon.getPokeType());

    pokemon.setLvl(20);
    assertEquals(20, pokemon.getLvl());

    pokemon.setBehavior("Docile");
    assertEquals("Docile", pokemon.getBehavior());

    pokemon.setPokeAttack(new String[] {"Thunderbolt"});
    assertArrayEquals(new String[] {"Thunderbolt"}, pokemon.getPokeAttack());
  }

  @Test
  public void testEnumComparisons() {
    // Method 1: Using == (RECOMMENDED for enums)
    pokemon.setPokeType(PokemonType.FIRE);
    assertThat(pokemon.getPokeType() == PokemonType.FIRE).isTrue();

    // Method 2: Using .equals() (also works)
    assertThat(pokemon.getPokeType().equals(PokemonType.FIRE)).isTrue();

    // Method 3: Using assertThat with isEqualTo
    assertThat(pokemon.getPokeType()).isEqualTo(PokemonType.FIRE);

    // Method 4: Different enum values
    pokemon.setPokeType(PokemonType.WATER);
    assertThat(pokemon.getPokeType() != PokemonType.FIRE).isTrue();
  }

  /*@Test
      public void TestLvlUp() {

      }
  */
}
