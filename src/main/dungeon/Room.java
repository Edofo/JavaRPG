package main.dungeon;

import java.util.ArrayList;
import java.util.List;

import main.item.*;
import main.utils.DisplayMessage;

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

        int numberOfMonsters = (int) (Math.random() * 3) + 1;

        List<Monster> monsters = new ArrayList<>();

        for (int i = 0; i < numberOfMonsters; i++) {
            // Generate a random monster
            monsters.add(Monster.generateRandomMonster());
        }

        return new Room(monsters);
    }

    public void enterRoom(int index) {
        Room room = this;

        DisplayMessage.outputTextArea("Entering room ");

        // display the monsters in the room
        DisplayMessage.outputTextArea("Monsters in this room:");

        for (int i = 0; i < room.getMonsters().size(); i++) {
            DisplayMessage.outputTextArea(
                    "- " + room.getMonsters().get(i).getName() + " (" + room.getMonsters().get(i).getHealth() + " HP)");
        }

    }

    // Method to check if the room is cleared
    public boolean isCleared() {
        if (this.isCleared) {
            return true;
        }

        for (Monster monster : monsters) {
            if (monster.isAlive()) {
                return false;
            }
        }

        this.isCleared = true;
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

    public void setClear(boolean clear) {
        isCleared = clear;
    }
}
