package main.dungeon;

import java.util.List;

public class Room {
    private List<Monster> monsters;

    public Room(Monster... monsters) {
        this.monsters = List.of(monsters);
    }

    // Getters and setters for each attribute
    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }
}
