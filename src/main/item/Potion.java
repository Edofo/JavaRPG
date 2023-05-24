package main.item;

import java.util.List;
import java.util.Random;

public class Potion extends Item {
    public Potion(String name, List<Integer> stats) {
        super(name, ItemType.POTION, stats);
    }

    // method to generate a random potion
    public static Potion generateRandomPotion() {
        Random random = new Random();

        // Create a list of potions like: "Name", minHealthRestore, maxHealthRestore
        List<String[]> potionsList = List.of(
                new String[] { "Healing Potion", "20", "50" },
                new String[] { "Elixir of Life", "50", "100" },
                new String[] { "Stamina Tonic", "30", "60" },
                new String[] { "Antidote", "10", "20" },
                new String[] { "Melvin Potion", "100", "500" });

        // Get a random potion from the list
        String[] randomPotion = potionsList.get(random.nextInt(potionsList.size()));

        // Generate random stats for health restored from the min and max health
        // restored of the potion
        int minHealthRestore = Integer.parseInt(randomPotion[1]);
        int maxHealthRestore = Integer.parseInt(randomPotion[2]);
        int randomHealthRestore = random.nextInt(maxHealthRestore - minHealthRestore + 1) + minHealthRestore;

        // Create a new potion with the name and the stats
        return new Potion(randomPotion[0], List.of(0, 0, randomHealthRestore));
    }

}
