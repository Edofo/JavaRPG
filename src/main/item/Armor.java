package main.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.utils.DisplayMessage;

public class Armor extends Item {
    public Armor(String name, List<Integer> stats) {
        super(name, ItemType.ARMOR, stats);
    }

    // Method to print the armor's stats
    @Override
    public void printStats() {
        DisplayMessage.outputTextArea("Name: " + this.getName());
        DisplayMessage.outputTextArea("Defense: " + this.getStats().get(0));
    }

    // Method to generate a random armor
    public static Armor generateRandomArmor() {
        Random random = new Random();

        // Create a list of armors with their stats in the form of "Name", mindefense,
        // maxdefense
        List<String[]> armorsList = new ArrayList<>();

        armorsList.add(new String[] { "Plate Mail", "10", "50" });
        armorsList.add(new String[] { "Chain Mail", "7", "40" });
        armorsList.add(new String[] { "Leather Armor", "5", "30" });
        armorsList.add(new String[] { "Wizard Robe", "2", "20" });
        armorsList.add(new String[] { "Shaman Robe", "4", "25" });
        armorsList.add(new String[] { "Dragon Scale Mail", "15", "75" });
        armorsList.add(new String[] { "Adamantite Plate", "20", "100" });
        armorsList.add(new String[] { "Silk Robe", "1", "15" });
        armorsList.add(new String[] { "Gold Armor", "12", "60" });
        armorsList.add(new String[] { "Titanium Chain", "8", "45" });
        armorsList.add(new String[] { "Melvin Armor", "100", "500" });

        // Get a random armor from the list
        String[] randomArmor = armorsList.get(random.nextInt(armorsList.size()));

        // Generate random stats for defense and health from the min and max values of
        // the armor
        int randomDefense = random.nextInt(Integer.parseInt(randomArmor[2]) - Integer.parseInt(randomArmor[1]))
                + Integer.parseInt(randomArmor[1]);

        // Create a new armor with the name and the stats
        return new Armor(randomArmor[0], List.of(randomDefense, 0, 0));

    }

}
