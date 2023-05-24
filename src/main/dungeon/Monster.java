package main.dungeon;

import main.Character;

import java.util.Random;

public class Monster extends Character {
    private static final int TOTAL_STAT_POINTS = 40;
    private static final String[] NAMES = { "Goblin", "Orc", "Troll", "Dragon", "Giant" };

    public Monster(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    // Generate a random monster
    public static Monster generateRandomMonster() {
        Random random = new Random();

        int nameIndex = random.nextInt(NAMES.length);
        String name = NAMES[nameIndex];

        int health = random.nextInt(TOTAL_STAT_POINTS);
        health = Math.max(health, 5);
        int remainingStatPoints = TOTAL_STAT_POINTS - health;

        int attack = random.nextInt(remainingStatPoints);
        attack = Math.max(attack, 5);
        int defense = remainingStatPoints - attack;

        return new Monster(name, health, attack, defense);
    }
}
