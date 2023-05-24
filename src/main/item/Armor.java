package main.item;

import java.util.List;
import java.util.Random;

public class Armor extends Item {
    public Armor(String name, List<Integer> stats) {
        super(name, ItemType.ARMOR, stats);
    }

    // Method to generate a random armor
    public static Armor generateRandomArmor() {
        Random random = new Random();

        // Create a list of armors with their stats in the form of "Name", mindefense,
        // maxdefense
        List<String[]> armorsList = List.of(
                new String[] { "Plate Mail", "10", "50" },
                new String[] { "Chain Mail", "7", "40" },
                new String[] { "Leather Armor", "5", "30" },
                new String[] { "Wizard Robe", "2", "20" },
                new String[] { "Shaman Robe", "4", "25" },
                new String[] { "Dragon Scale Mail", "15", "75" },
                new String[] { "Adamantite Plate", "20", "100" },
                new String[] { "Silk Robe", "1", "15" },
                new String[] { "Gold Armor", "12", "60" },
                new String[] { "Titanium Chain", "8", "45" },
                new String[] { "Melvin Armor", "100", "500" });

        // Get a random armor from the list
        String[] randomArmor = armorsList.get(random.nextInt(armorsList.size()));

        // Generate random stats for defense and health from the min and max values of
        // the armor
        int minDefense = Integer.parseInt(randomArmor[1]);
        int maxDefense = Integer.parseInt(randomArmor[2]);
        int randomDefense = random.nextInt(maxDefense - minDefense + 1) + minDefense;

        // Create a new armor with the name and the stats
        return new Armor(randomArmor[0], List.of(randomDefense, 0, 0));
    }

}
