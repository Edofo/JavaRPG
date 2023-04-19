package main.characterclass;

import java.util.List;

public class CharacterClass {

    private int health;
    private int attack;
    private int defense;
    private List<String> abilities;

    public CharacterClass(int health, int attack, int defense, List<String> abilities) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.abilities = abilities;
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

    public List<String> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<String> abilities) {
        this.abilities = abilities;
    }

    @Override
    public String toString() {
        return "CharacterClass{" +
                "health=" + health +
                ", attack=" + attack +
                ", defense=" + defense +
                ", abilities=" + abilities +
                '}';
    }

}
