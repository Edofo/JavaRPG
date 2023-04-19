package main.item;

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

    // method to print the item's stats
    public void printStats() {
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println(
                "Stats: " + "Attack: " + stats.get(0) + " Defense: " + stats.get(1) + " Health: " + stats.get(2));
    }

    // Method to get random Weapon
    public static Item getRandomItem() {

        int randomType = (int) (Math.random() * ItemType.values().length);
        // int randomType = 0;

        Item item = null;

        switch (randomType) {
            case 0:
                item = Weapon.generateRandomWeapon();
                break;
            case 1:
                item = Armor.generateRandomArmor();
                break;
            case 2:
                item = Potion.generateRandomPotion();
                break;
            default:
                item = Weapon.generateRandomWeapon();
                break;
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