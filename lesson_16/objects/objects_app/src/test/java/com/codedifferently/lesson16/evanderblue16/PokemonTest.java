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

    assertThat(pokemon.getPokeType().equals(PokemonType.FIRE)).isTrue();

    assertThat(pokemon.getPokeType()).isEqualTo(PokemonType.FIRE);

    pokemon.setPokeType(PokemonType.WATER);
    assertThat(pokemon.getPokeType() != PokemonType.FIRE).isTrue();
  }

  @Test
  public void TestLvlUp() {

    int initialLevel = pokemon.getLvl();

    pokemon.lvlUp();

    assertEquals(initialLevel + 1, pokemon.getLvl());

    pokemon.lvlUp();
    assertEquals(initialLevel + 2, pokemon.getLvl());
  }
}
