package main.dungeon;

import main.Character;

public class Monster extends Character {

    public Monster(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    // Generate a random monster
    public static Monster generateRandomMonster() {

        int TOTAL_STAT_POINTS = 40;

        String[] names = { "Goblin", "Orc", "Troll", "Dragon", "Giant" };
        int nameIndex = (int) (Math.random() * names.length);
        String name = names[nameIndex];

        int health = (int) (Math.random() * TOTAL_STAT_POINTS);
        if (health < 5)
            health = 5;
        TOTAL_STAT_POINTS -= health;

        int attack = (int) (Math.random() * TOTAL_STAT_POINTS);
        if (attack < 5)
            attack = 5;
        TOTAL_STAT_POINTS -= attack;

        int defense = TOTAL_STAT_POINTS;

        return new Monster(name, health, attack, defense);
    }

}