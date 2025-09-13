package com.codedifferently.lesson16.evanderblue16;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {
  private String pokeName;
  private PokemonType pokeType;
  private int lvl;
  private String behavior;
  private String[] pokeAttack;
  private List<String> moves; // Collection member variable

  public Pokemon(
      String pokeName, PokemonType pokeType, int lvl, String behavior, String[] pokeAttack) {
    this.pokeName = pokeName;
    this.pokeType = pokeType;
    this.lvl = lvl;
    this.behavior = behavior;
    this.pokeAttack = pokeAttack;
    this.moves = new ArrayList<>();
    // Initialize moves with attacks
    if (pokeAttack != null) {
      for (String attack : pokeAttack) {
        this.moves.add(attack);
      }
    }
  }

  // Getters
  public String getPokeName() {
    return pokeName;
  }

  public PokemonType getPokeType() {
    return pokeType;
  }

  public int getLvl() {
    return lvl;
  }

  public String getBehavior() {
    return behavior;
  }

  public String[] getPokeAttack() {
    return pokeAttack;
  }

  // Setters
  public void setPokeName(String pokeName) {
    this.pokeName = pokeName;
  }

  public void setPokeType(PokemonType pokeType) {
    this.pokeType = pokeType;
  }

  public void setLvl(int lvl) throws InvalidLevelException {
    // Custom exception usage
    if (lvl < 1 || lvl > 100) {
      throw new InvalidLevelException("Level must be between 1 and 100");
    }
    this.lvl = lvl;
  }

  public void setBehavior(String behavior) {
    this.behavior = behavior;
  }

  public void setPokeAttack(String[] pokeAttack) {
    this.pokeAttack = pokeAttack;
    // Update moves collection when attacks change
    this.moves.clear();
    if (pokeAttack != null) {
      for (String attack : pokeAttack) {
        this.moves.add(attack);
      }
    }
  }

  // Function with conditional expression
  public String getTypeEffectiveness(PokemonType opponentType) {
    return (this.pokeType == PokemonType.ELECTRIC && opponentType == PokemonType.WATER)
        ? "Super Effective!"
        : (this.pokeType == PokemonType.WATER && opponentType == PokemonType.FIRE)
            ? "Super Effective!"
            : (this.pokeType == PokemonType.FIRE && opponentType == PokemonType.GRASS)
                ? "Super Effective!"
                : "Normal damage";
  }

  // Function that uses collection member variable
  public void learnMove(String newMove) {
    if (!moves.contains(newMove)) {
      moves.add(newMove);
      // Update the array as well
      String[] newAttacks = moves.toArray(new String[0]);
      this.pokeAttack = newAttacks;
    }
  }

  // Function that uses a loop
  public String displayAllMoves() {
    StringBuilder moveList = new StringBuilder();
    for (int i = 0; i < moves.size(); i++) {
      moveList.append(moves.get(i));
      if (i < moves.size() - 1) {
        moveList.append(", ");
      }
    }
    return moveList.toString();
  }

  public void lvlUp() {
    try {
      setLvl(this.lvl + 1);
    } catch (InvalidLevelException e) {
      System.out.println("Cannot level up: " + e.getMessage());
    }
  }

  public List<String> getMoves() {
    return new ArrayList<>(moves);
  }
}
