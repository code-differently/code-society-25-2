package com.codedifferently.lesson16.evanderblue16;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
  public void testSetters() throws InvalidLevelException {
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

  // ...existing code...

  @Test
  public void testInvalidLevelException() {
    // Test setting level too low
    assertThrows(
        InvalidLevelException.class,
        () -> {
          pokemon.setLvl(0);
        });

    assertThrows(
        InvalidLevelException.class,
        () -> {
          pokemon.setLvl(-5);
        });

    // Test setting level too high
    assertThrows(
        InvalidLevelException.class,
        () -> {
          pokemon.setLvl(101);
        });

    assertThrows(
        InvalidLevelException.class,
        () -> {
          pokemon.setLvl(150);
        });

    // Test valid boundary levels
    assertDoesNotThrow(
        () -> {
          pokemon.setLvl(1);
          assertEquals(1, pokemon.getLvl());
        });

    assertDoesNotThrow(
        () -> {
          pokemon.setLvl(100);
          assertEquals(100, pokemon.getLvl());
        });
  }

  @Test
  public void testTypeEffectiveness() {
    // Test super effective combinations
    pokemon.setPokeType(PokemonType.ELECTRIC);
    assertEquals("Super Effective!", pokemon.getTypeEffectiveness(PokemonType.WATER));

    pokemon.setPokeType(PokemonType.WATER);
    assertEquals("Super Effective!", pokemon.getTypeEffectiveness(PokemonType.FIRE));

    pokemon.setPokeType(PokemonType.FIRE);
    assertEquals("Super Effective!", pokemon.getTypeEffectiveness(PokemonType.GRASS));

    // Test normal damage cases
    pokemon.setPokeType(PokemonType.ELECTRIC);
    assertEquals("Normal damage", pokemon.getTypeEffectiveness(PokemonType.ELECTRIC));

    pokemon.setPokeType(PokemonType.NORMAL);
    assertEquals("Normal damage", pokemon.getTypeEffectiveness(PokemonType.FIGHTING));
  }

  @Test
  public void testLearnMove() {
    // Test learning new moves
    pokemon.learnMove("Quick Attack");
    assertThat(pokemon.getMoves()).contains("Quick Attack");
    assertThat(pokemon.getMoves()).contains("Thunderbolt");

    // Test learning duplicate moves (should not add duplicates)
    int initialMoveCount = pokemon.getMoves().size();
    pokemon.learnMove("Thunderbolt");
    assertEquals(initialMoveCount, pokemon.getMoves().size());

    // Test multiple new moves
    pokemon.learnMove("Thunder");
    pokemon.learnMove("Agility");
    assertThat(pokemon.getMoves()).contains("Thunder", "Agility");

    // Verify getMoves returns a copy (defensive programming)
    var moves = pokemon.getMoves();
    moves.add("Should not affect original");
    assertThat(pokemon.getMoves()).doesNotContain("Should not affect original");
  }

  @Test
  public void testDisplayAllMoves() {
    // Test with initial move
    String moves = pokemon.displayAllMoves();
    assertEquals("Thunderbolt", moves);

    // Test with multiple moves
    pokemon.learnMove("Quick Attack");
    pokemon.learnMove("Thunder");
    moves = pokemon.displayAllMoves();
    assertThat(moves).contains("Thunderbolt");
    assertThat(moves).contains("Quick Attack");
    assertThat(moves).contains("Thunder");
    assertThat(moves).contains(", ");

    // Test with empty moves
    pokemon.setPokeAttack(new String[] {});
    moves = pokemon.displayAllMoves();
    assertEquals("", moves);
  }

  @Test
  public void testEdgeCases() {
    // Test null attack array
    pokemon.setPokeAttack(null);
    assertThat(pokemon.getMoves()).isEmpty();
    assertEquals("", pokemon.displayAllMoves());

    // Test empty attack array
    pokemon.setPokeAttack(new String[] {});
    assertThat(pokemon.getMoves()).isEmpty();
    assertEquals("", pokemon.displayAllMoves());

    // Test level up at max level (should handle gracefully)
    assertDoesNotThrow(
        () -> {
          pokemon.setLvl(100);
          pokemon.lvlUp(); // Should not throw exception, just not increase
          assertEquals(100, pokemon.getLvl());
        });

    // Test learning null move (should handle gracefully)
    assertDoesNotThrow(
        () -> {
          pokemon.learnMove(null);
        });
  }

  @Test
  public void testConstructorWithNullAttacks() {
    // Test constructor with null attacks
    Pokemon nullAttackPokemon = new Pokemon("Magikarp", PokemonType.WATER, 5, "Hardy", null);
    assertEquals("Magikarp", nullAttackPokemon.getPokeName());
    assertEquals(PokemonType.WATER, nullAttackPokemon.getPokeType());
    assertEquals(5, nullAttackPokemon.getLvl());
    assertEquals("Hardy", nullAttackPokemon.getBehavior());
    assertThat(nullAttackPokemon.getMoves()).isEmpty();
    assertEquals("", nullAttackPokemon.displayAllMoves());
  }
  // ...existing code...
}
