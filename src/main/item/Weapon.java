package main.item;

import java.util.List;
import java.util.Random;

public class Weapon extends Item {
    public Weapon(String name, List<Integer> stats) {
        super(name, ItemType.WEAPON, stats);
    }

    // method to generate a random weapon
    public static Weapon generateRandomWeapon() {
        Random random = new Random();

        // Create a list of weapons like: "Name", minAttack, maxAttack
        List<String[]> weaponsList = List.of(
                new String[] { "Dragon Slayer", "35", "75" },
                new String[] { "Excalibur", "45", "85" },
                new String[] { "Tenka-Goken", "20", "60" },
                new String[] { "Sword of Goujian", "25", "50" },
                new String[] { "Wallace", "15", "40" },
                new String[] { "Honjo Masamune", "40", "100" },
                new String[] { "Sword of Damocles", "5", "20" },
                new String[] { "Kunai", "25", "40" },
                new String[] { "Katana", "30", "50" },
                new String[] { "Melvin Sword", "100", "500" });

        // Get a random weapon from the list
        String[] randomWeapon = weaponsList.get(random.nextInt(weaponsList.size()));

        // Generate random stats for attack from the min and max attack of the weapon
        int minAttack = Integer.parseInt(randomWeapon[1]);
        int maxAttack = Integer.parseInt(randomWeapon[2]);
        int randomAttack = random.nextInt(maxAttack - minAttack + 1) + minAttack;

        // Create a new weapon with the name and the stats
        return new Weapon(randomWeapon[0], List.of(randomAttack, 0, 0));
    }

}