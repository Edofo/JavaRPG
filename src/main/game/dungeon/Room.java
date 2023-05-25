package main.game.dungeon;

import main.game.item.Item;
import main.utils.DisplayMessage;
import main.utils.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Room {
    private List<Monster> monsters;
    private BufferedImage image;
    private boolean isCleared;

    public Room(List<Monster> monsters, BufferedImage image) {
        this.monsters = monsters;
        this.image = image;
        this.isCleared = false;
    }

    public static Room generateRoom(boolean isBossRoom) {
        List<String> images = List.of("background_skin.png", "background_skin_2.png", "background_skin_3.png");
        BufferedImage image = ImageLoader.loadImage(images.get(new Random().nextInt(images.size())));

        if (isBossRoom) {
            Monster boss = Boss.generateRandomBoss();
            return new Room(List.of(boss), image);
        }

        int numberOfMonsters = new Random().nextInt(3) + 1;
        List<Monster> monsters = new ArrayList<>(numberOfMonsters);

        for (int i = 0; i < numberOfMonsters; i++) {
            monsters.add(Monster.generateRandomMonster());
        }

        return new Room(monsters, image);
    }

    public void enterRoom(int index) {
        DisplayMessage.outputTextArea("Entering room " + index);
        DisplayMessage.outputTextArea("Monsters in this room:");

        for (Monster monster : monsters) {
            DisplayMessage.outputTextArea("- " + monster.getName() + " (" + monster.getHealth() + " HP)");
        }
    }

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

    public boolean hasBoss() {
        for (Monster monster : monsters) {
            if (monster instanceof Boss) {
                return true;
            }
        }
        return false;
    }

    public Item generateItem() {
        if (!isCleared) {
            return null;
        }

        return Item.getRandomItem();
    }

    public List<Monster> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<Monster> monsters) {
        this.monsters = monsters;
    }

    public void setClear(boolean clear) {
        isCleared = clear;
    }

    public BufferedImage getBackgroundImage() {
        return image;
    }

    public void setBackgroundImage(BufferedImage image) {
        this.image = image;
    }
}
