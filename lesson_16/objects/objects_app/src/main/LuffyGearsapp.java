import java.util.ArrayLists;
import java.util.List;


class GearLimitException extends Exception {
    public GearLimitException(String message) {
        super(message);
    }
}

enum Gear {
    Gear2, Gear3, Gear4, Gear5
}

public class Luffy {
    private string name;
    private int bounty;
    private double stamina;
    private Gear currentGear;
    private List<Gear> unlockedGears;

}

public Luffy(string name, int bounty, double stamina) {
    this.name = name;
    this.bounty = bounty;
    this.stamina = stamina;
    this.currentGear = Gear.Gear2;
    this.unlockedGears = new ArrayList<>();
    unlockedGears.add(Gear.Gear2);
}

public void switchGear(Gear newGear) throws GearLimitException {
    if (!unlockedGears.contains(newGear)) {
        throw new GearLimitException("Gear " + newGear + " is not unlocked yet!");
    }
}

if (stamina < 20 && newGear == Gear.Gear5) {
    throw new GearLimitException("Not enough stamina to switch to Gear 5!");
}

this.currentGear = newGear;
System.out.println(name + " switched to " + newGear);
}

 public void unlockGear(Gear newGear) {
    if (!unlockedGears.contains(newGear)) {
        unlockedGears.add(newGear);
        System.out.println(name + " unlocked " + newGear);
    } else {
        System.out.println(name + " already unlocked " + newGear);
    }
}

public void showUnlockedGears() {
    System.out.println(name + "'s unlocked gears: ");
    for (Gear g : unlockedGears) {
        System.out.println("- " + g);
    }

}

public Gear getCurrentGear() {
    return currentGear;
}