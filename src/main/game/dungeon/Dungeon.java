package main.game.dungeon;

import main.utils.DisplayMessage;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private String name;
    private List<Room> rooms;

    public Dungeon(String name, List<Room> rooms) {
        this.name = name;
        this.rooms = rooms;
    }

    // Method to generate a random dungeon
    public static Dungeon generateRandomDungeon() {
        DisplayMessage.outputTextArea("Generating dungeon...");

        // Generate a random number of rooms between 5 and 10
        // int numberOfRooms = (int) (Math.random() * 5) + 5;
        int numberOfRooms = 1;

        List<Room> rooms = new ArrayList<>(numberOfRooms);

        DisplayMessage.outputTextArea("Generating dungeon with " + numberOfRooms + " rooms");

        for (int i = 0; i < numberOfRooms; i++) {
            // Generate a random room
            rooms.add(i == numberOfRooms - 1 ? Room.generateRoom(true) : Room.generateRoom(false));
        }

        return new Dungeon("Dungeon", rooms);
    }

    // Method to check if the dungeon is cleared
    public boolean isCleared() {
        for (Room room : rooms) {
            if (!room.isCleared()) {
                return false;
            }
        }
        return true;
    }

    // Getters and setters for each attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    // Method to get a room by its index
    public Room getRoom(int index) {
        return rooms.get(index);
    }
}
