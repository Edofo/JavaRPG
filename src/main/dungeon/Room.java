package main.dungeon;

import main.item.Item;
import main.utils.DisplayMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private List<Monster> monsters;
    private boolean isCleared;

    public Room(List<Monster> monsters) {
        this.monsters = monsters;
        this.isCleared = false;
    }

    public static Room generateRoom(boolean isBossRoom) {
        if (isBossRoom) {
            Monster boss = Boss.generateRandomBoss();
            return new Room(List.of(boss));
        }

        int numberOfMonsters = new Random().nextInt(3) + 1;
        List<Monster> monsters = new ArrayList<>(numberOfMonsters);

        for (int i = 0; i < numberOfMonsters; i++) {
            // Generate a random monster
            monsters.add(Monster.generateRandomMonster());
        }

        return new Room(monsters);
    }

    public void enterRoom(int index) {
        DisplayMessage.outputTextArea("Entering room " + index);

        // Display the monsters in the room
        DisplayMessage.outputTextArea("Monsters in this room:");
        for (Monster monster : monsters) {
            DisplayMessage.outputTextArea("- " + monster.getName() + " (" + monster.getHealth() + " HP)");
        }
    }

    // Method to check if the room is cleared
    public boolean isCleared() {
        if (isCleared) {
            return true;
        }

        for (Monster monster : monsters) {
            if (monster.isAlive()) {
                return false;
            }
        }

        isCleared = true;
        return true;
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
        if (!isCleared) {
            return null;
        }

        return Item.getRandomItem();
    }

    // Getters and setters for each attribute
    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public void setClear(boolean clear) {
        isCleared = clear;
    }
}
