public class LuffyGearsTest {

public static void main(String[] args) {
}
}
Luffy luffy = new Luffy("Monkey D. Luffy", 3000000000, 50);

luffy.showUnlockedGears();

luffy.unlockGear(Gear.Gear3);
luffy.unlockGear(Gear.Gear4);

luffy.showUnlockedGears();

try {
    luffy.switchGear(Gear.Gear3);
    System.out.println("Switched to Gear 3");

    luffy.switchGear(Gear.Gear4);
    System.out.println("Switched to Gear 4");
}

luffy.switchGear(Gear.Gear5);
} catch (GearNotUnlockedException e) {
    System.out.println(e.getMessage());
}

luffy.showCurrentGear();
}
}