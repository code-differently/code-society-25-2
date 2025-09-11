package com.codedifferently.f1drivers;

import java.util.ArrayList;

// Enum for teams
enum Team {
    MERCEDES, FERRARI, RED_BULL, MCLAREN, ASTON_MARTIN
}

// Custom Exception
class InvalidDriverAgeException extends Exception {
    public InvalidDriverAgeException(String message) {
        super(message);
    }
}

// F1Driver class
public class F1Driver {
    private String name;
    private int age;
    private Team team;
    private ArrayList<String> raceWins;
    private boolean worldChampion;
    private int careerPoints;
    private int podiums;

    // Constructor
    public F1Driver(String name, int age, Team team, boolean worldChampion, int careerPoints, int podiums) throws InvalidDriverAgeException {
        if (age < 18) {
            throw new InvalidDriverAgeException("F1 drivers must be at least 18 years old.");
        }
        this.name = name;
        this.age = age;
        this.team = team;
        this.worldChampion = worldChampion;
        this.careerPoints = careerPoints;
        this.podiums = podiums;
        this.raceWins = new ArrayList<>();
    }

    // Function 1: Conditional
    public boolean isVeteran() {
        return this.age > 30;
    }

    // Function 2: Uses collection
    public void addRaceWin(String raceName) {
        raceWins.add(raceName);
    }

    // Function 3: Uses loop
    public void printRaceWins() {
        if (raceWins.isEmpty()) {
            System.out.println(name + " has no race wins yet.");
        } else {
            System.out.println(name + "'s race wins:");
            for (String win : raceWins) {
                System.out.println("- " + win);
            }
        }
    }

    // New Function: Update stats
    public void addPodium(int pointsEarned) {
        podiums++;
        careerPoints += pointsEarned;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public Team getTeam() { return team; }
    public boolean isWorldChampion() { return worldChampion; }
    public int getCareerPoints() { return careerPoints; }
    public int getPodiums() { return podiums; }
}
