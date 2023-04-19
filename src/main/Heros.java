package main;

import java.util.List;

import main.item.Item;
import main.item.ItemType;

public class Heros extends Character {
    private String characterClass;
    private List<Item> inventory;

    public Heros(String name, int health, int attack, int defense, String characterClass) {
        super(name, health, attack, defense);
        this.characterClass = characterClass;
    }

    // Get all damage from the character's inventory and add it to the character's
    // attack
    public int getAttack() {
        int attack = super.getAttack();
        for (Item item : inventory) {
            if (item.getType() == ItemType.WEAPON) {
                attack += item.getStats().get(0);
            }
        }
        return attack;
    }

    // Get all defense from the character's inventory and add it to the character's
    // defense
    public int getDefense() {
        int defense = super.getDefense();
        for (Item item : inventory) {
            if (item.getType() == ItemType.ARMOR) {
                defense += item.getStats().get(1);
            }
        }
        return defense;
    }

    // Method to add an item to the character's inventory
    public void addItemToInventory(Item item) {
        inventory.add(item);
        // check if the item is not a potion
        if (item.getType() != ItemType.POTION) {
            // add the item's stats to the character's stats
            this.getAttack();
            this.getDefense();
        }

    }

    // Method to remove an item from the character's inventory
    public void removeItemFromInventory(Item item) {
        inventory.remove(item);
        // check if the item is not a potion
        if (item.getType() != ItemType.POTION) {
            // remove the item's stats from the character's stats
            this.getAttack();
            this.getDefense();
        }
    }

    // Method to print the character's inventory
    public void printInventory() {
        System.out.println("Inventory:");
        for (Item item : inventory) {
            // check if the item is a potion
            // if (item.getType() == ItemType.POTION) {
            // System.out.println(item.getName() + " - " + item.getStats().get(0) + "
            // health");
            // } else {
            // System.out.println(item.getName() + " - " + item.getStats().get(0) + "
            // attack, " + item.getStats().get(1) + " defense");
            // }
            System.out.println("- " + item.getName());
        }
    }

    // Getters and setters for each attribute
    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

}
