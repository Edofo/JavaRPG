package main.dungeon;

import java.util.ArrayList;
import java.util.List;

import main.utils.DisplayMessage;

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
        int numberOfRooms = (int) (Math.random() * 5) + 5;

        List<Room> rooms = new ArrayList<Room>();

        DisplayMessage.outputTextArea("Generating dungeon with " + numberOfRooms + " rooms");

        for (int i = 0; i < numberOfRooms; i++) {
            // Generate a random room
            if (i == numberOfRooms - 1) {
                rooms.add(Room.generateRoom(true));
            } else {
                rooms.add(Room.generateRoom(false));
            }
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
