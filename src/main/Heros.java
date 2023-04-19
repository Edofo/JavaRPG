package main;

public class Heros extends Character {
    private String characterClass;

    public Heros(String name, String firstName, int health, int attack, int defense, String characterClass) {
        super(name, firstName, health, attack, defense);
        this.characterClass = characterClass;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

}
