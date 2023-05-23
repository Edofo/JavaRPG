package main.item;

import java.util.List;

import main.Character;
import main.utils.DisplayMessage;

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
        DisplayMessage.outputTextArea("Name: " + name);
        DisplayMessage.outputTextArea("Type: " + type);
        DisplayMessage.outputTextArea(
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

    public void useItem(Character character) {
        DisplayMessage.outputTextArea("Using item: " + this.getName() + this.getType() + this.getStats());
        if (this.getType() == ItemType.POTION) {
            if (character.getHealth() + this.getStats().get(2) > character.getMaxHealth()) {
                character.setHealth(character.getMaxHealth());
            } else {
                character.setHealth(character.getHealth() + this.getStats().get(2));
            }

        }

        // if (item.getType() == ItemType.WEAPON) {
        // character.setAttack(character.getAttack() + item.getStats().get(0));
        // }

        // if (item.getType() == ItemType.ARMOR) {
        // character.setDefense(character.getDefense() + item.getStats().get(1));
        // }
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