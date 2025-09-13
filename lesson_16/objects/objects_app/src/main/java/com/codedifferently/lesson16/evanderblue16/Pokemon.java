package com.codedifferently.lesson16.evanderblue16;

public class Pokemon {
  private String pokeName;
  private PokemonType pokeType;
  private int lvl;
  private String behavior;
  private String[] pokeAttack;

  // Constructor
  public Pokemon(
      String pokeName, PokemonType pokeType, int lvl, String behavior, String[] pokeAttack) {
    this.pokeName = pokeName;
    this.pokeType = pokeType;
    this.lvl = lvl;
    this.behavior = behavior;
    this.pokeAttack = pokeAttack;
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

  public void setLvl(int lvl) {
    this.lvl = lvl;
  }

  public void setBehavior(String behavior) {
    this.behavior = behavior;
  }

  public void setPokeAttack(String[] pokeAttack) {
    this.pokeAttack = pokeAttack;
  }

  public void lvlUp() {
    this.lvl += 1;
  }
}
