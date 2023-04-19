package main.item;

import java.io.File;
import java.util.List;

public class Item {
    private String name;
    private ItemType type;
    private List<Integer> stats; // an array to hold the custom stats of the item (attack, defense, health)

    public Item(String name, ItemType type, List<Integer> stats) {
        this.name = name;
        this.type = type;
        this.stats = stats;
    }

    // Method to get random Weapon
    public static Item getRandomItem() {

        // int randomType = (int) (Math.random() * ItemType.values().length);
        int randomType = 0;

        String type;

        switch (randomType) {
            case 0:
                type = "weapon";
                break;
            case 1:
                type = "armor";
                break;
            case 2:
                type = "potion";
                break;
            default:
                type = "weapon";
                break;
        }

        File itemFolder = new File("src/main/item/" + type);

        // Get all files in the folder
        File[] itemFiles = itemFolder.listFiles();

        // Get a random file
        File randomItemFile = itemFiles[(int) (Math.random() * itemFiles.length)];

        // Get the name of the file
        String itemName = randomItemFile.getName();

        // Remove the .java extension
        itemName = itemName.substring(0, itemName.length() - 5);

        // Create an instance of the item
        Item item = null;

        try {
            item = (Item) Class.forName("main.item." + type + "." + itemName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return item;
    }

    // Getters and setters for each attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public List<Integer> getStats() {
        return stats;
    }

    public void setStats(List<Integer> stats) {
        this.stats = stats;
    }
}