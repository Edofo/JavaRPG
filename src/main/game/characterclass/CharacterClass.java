package main.game.characterclass;

import java.util.List;

import main.game.abilities.Abilities;

public class CharacterClass {

    private int health;
    private int attack;
    private int defense;
    private List<Abilities> abilities;

    private int mana;

    public CharacterClass(int health, int attack, int defense, List<Abilities> abilities, int mana) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.abilities = abilities;
        this.mana = mana;
    }

    // Getters and Setters for each attribute
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

}
