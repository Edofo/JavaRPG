package main.dungeon;

public class Item {
    private String name;
    private String type;

    public Item(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // Getters and setters for each attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}