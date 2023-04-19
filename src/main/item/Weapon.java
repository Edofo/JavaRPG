package main.item;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Weapon extends Item {
    public Weapon(String name, List<Integer> stats) {
        super(name, ItemType.WEAPON, stats);
    }

    // Method to print the weapon's stats
    @Override
    public void printStats() {
        System.out.println("Name: " + this.getName());
        System.out.println("Attack: " + this.getStats().get(0));
    }

    // method to generate a random weapon
    public static Weapon generateRandomWeapon() {

        Random random = new Random();

        // create a list of weapons like : "Name", minAttack, maxAttack
        List<String[]> weaponsList = new ArrayList<>();

        weaponsList.add(new String[] { "Dragon Slayer", "35", "75" });
        weaponsList.add(new String[] { "Excalibur", "45", "85" });
        weaponsList.add(new String[] { "Tenka-Goken", "20", "60" });
        weaponsList.add(new String[] { "Sword of Goujian", "25", "50" });
        weaponsList.add(new String[] { "Wallace", "15", "40" });
        weaponsList.add(new String[] { "Honjo Masamune", "40", "100" });
        weaponsList.add(new String[] { "Sword of Damocles", "5", "20" });
        weaponsList.add(new String[] { "Kunai", "25", "40" });
        weaponsList.add(new String[] { "Katana", "30", "50" });
        weaponsList.add(new String[] { "Melvin Sword", "100", "500" });

        // get a random weapon from the list
        String[] randomWeapon = weaponsList.get(random.nextInt(weaponsList.size()));

        // generate random stats for attack from the min and max attack of the weapon
        int randomAttack = random.nextInt(Integer.parseInt(randomWeapon[2]) - Integer.parseInt(randomWeapon[1]))
                + Integer.parseInt(randomWeapon[1]);

        // create a new weapon with the name and the stats
        return new Weapon(randomWeapon[0], List.of(randomAttack, 0, 0));
    }
}