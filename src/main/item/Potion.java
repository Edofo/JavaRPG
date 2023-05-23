package main.item;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;

public class Potion extends Item {
    public Potion(String name, List<Integer> stats) {
        super(name, ItemType.POTION, stats);
    }

    // Method to print the potion's stats
    @Override
    public void printStats() {
        System.out.println("Name: " + this.getName());
        System.out.println("Health restored: " + this.getStats().get(2));
    }

    // method to generate a random potion
    public static Potion generateRandomPotion() {

        Random random = new Random();

        // create a list of potions like : "Name", minHealthRestore, maxHealthRestore
        List<String[]> potionsList = new ArrayList<>();

        potionsList.add(new String[] { "Healing Potion", "20", "50" });
        potionsList.add(new String[] { "Elixir of Life", "50", "100" });
        potionsList.add(new String[] { "Stamina Tonic", "30", "60" });
        potionsList.add(new String[] { "Antidote", "10", "20" });
        potionsList.add(new String[] { "Melvin Potion", "100", "500" });

        // get a random potion from the list
        String[] randomPotion = potionsList.get(random.nextInt(potionsList.size()));

        // generate random stats for health restored from the min and max health
        // restored of the potion
        int randomHealthRestore = random.nextInt(Integer.parseInt(randomPotion[2]) - Integer.parseInt(randomPotion[1]))
                + Integer.parseInt(randomPotion[1]);

        // create a new potion with the name and the stats
        return new Potion(randomPotion[0], List.of(0, 0, randomHealthRestore));

    }

}
