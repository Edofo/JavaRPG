package main.dungeon;

import java.util.ArrayList;
import java.util.List;

import main.item.*;

public class Room {
    private List<Monster> monsters;
    private boolean isCleared;

    public Room(Monster... monsters) {
        this.monsters = List.of(monsters);
        this.isCleared = false;
    }

    public static Room generateRoom() {
        int numberOfMonsters = (int) (Math.random() * 3) + 1;

        List<Monster> monsters = new ArrayList<>();

        for (int i = 0; i < numberOfMonsters; i++) {
            // Generate a random monster
            monsters.add(Monster.generateRandomMonster());
        }

        return new Room(monsters.toArray(new Monster[0]));
    }

    // Method to check if the room has a boss
    public boolean hasBoss() {
        for (Monster monster : monsters) {
            if (monster instanceof Boss) {
                return true;
            }
        }
        return false;
    }

    // Method to generate a random item when the room is cleared
    public Item generateItem() {
        if (!this.isCleared) {
            return null;
        }

        Item item = Item.getRandomItem();

        return item;
    }

    // Getters and setters for each attribute
    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public boolean isCleared() {
        return isCleared;
    }

    public void setClear(boolean clear) {
        isCleared = clear;
    }
}
