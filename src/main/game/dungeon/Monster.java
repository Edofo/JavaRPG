package main.game.dungeon;

import main.game.Character;
import main.gui.CharacterSprite;
import main.utils.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Monster extends Character {
    private static final int TOTAL_STAT_POINTS = 40;
    private static final String[] NAMES = { "Goblin", "Orc", "Troll", "Dragon", "Giant" };

    private CharacterSprite sprite;

    public Monster(String name, int health, int attack, int defense, CharacterSprite sprite) {
        super(name, health, attack, defense);
        this.sprite = sprite;
    }

    // Generate a random monster
    public static Monster generateRandomMonster() {

        Random random = new Random();

        int nameIndex = random.nextInt(NAMES.length);
        String name = NAMES[nameIndex];

        BufferedImage image = ImageLoader.loadImage("monsters/" + name.toLowerCase() + ".png");

        int width = image.getWidth();
        int height = image.getHeight();
        int characterY = 130;

        switch (name) {
            case "Goblin":
                width = 150;
                height = 150;
                characterY = 200;
                break;
            case "Orc":
                width = 150;
                height = 175;
                characterY = 175;
                break;
            case "Troll":
                width = 175;
                height = 225;
                break;
            case "Dragon":
                width = 300;
                height = 200;
                characterY = 175;
                break;
            case "Giant":
                width = 200;
                height = 250;
                break;
        }

        CharacterSprite sprite = new CharacterSprite(image, width, height, characterY);

        int health = random.nextInt(TOTAL_STAT_POINTS);
        health = Math.max(health, 5);
        int remainingStatPoints = TOTAL_STAT_POINTS - health;

        int attack = random.nextInt(remainingStatPoints);
        attack = Math.max(attack, 5);
        int defense = remainingStatPoints - attack;

        return new Monster(name, health, attack, defense, sprite);
    }

    public CharacterSprite getSprite() {
        return sprite;
    }

    public void setSprite(CharacterSprite sprite) {
        this.sprite = sprite;
    }
}
