package main;

import java.util.ArrayList;
import java.util.List;

import main.characterclass.CharacterClass;
import main.item.Item;
import main.item.ItemType;

public class Heros extends Character {
    private CharacterClass characterClass;
    private List<Item> inventory;

    public Heros(String name, int health, int attack, int defense, CharacterClass characterClass) {
        super(name, health, attack, defense);
        this.characterClass = characterClass;
        this.inventory = new ArrayList<>();
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
        List<Item> itemsToRemove = new ArrayList<>();
        // check if the item is not a potion
        if (item.getType() != ItemType.POTION) {
            if (item.getType() == ItemType.WEAPON) {
                // check if the character already has a weapon equipped
                for (Item i : inventory) {
                    if (i.getType() == ItemType.WEAPON) {
                        // remove the old weapon's stats from the character's stats
                        itemsToRemove.add(i);
                    }
                }
            }

            if (item.getType() == ItemType.ARMOR) {
                // check if the character already has armor equipped
                for (Item i : inventory) {
                    if (i.getType() == ItemType.ARMOR) {
                        // remove the old armor's stats from the character's stats
                        itemsToRemove.add(i);
                    }
                }
            }
            inventory.add(item);

            // add the item's stats to the character's stats
            this.getAttack();
            this.getDefense();
        } else {
            // check if the item is a potion
            inventory.add(item);
        }

        // Remove the items that need to be removed
        for (Item itemToRemove : itemsToRemove) {
            removeItemFromInventory(itemToRemove);
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
    public CharacterClass getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
        this.getAttack();
        this.getDefense();
    }
}
