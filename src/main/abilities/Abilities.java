package main.abilities;

public class Abilities {
    private String name;

    private int damage;
    private int heal;
    private int defense;
    // private int manaCost;

    public Abilities(String name, int damage, int heal, int defense) {
        this.name = name;
        this.damage = damage;
        this.heal = heal;
        this.defense = defense;
    }

    // Getters and Setters for each attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHeal() {
        return heal;
    }

    public void setHeal(int heal) {
        this.heal = heal;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Abilities{" +
                "name='" + name + '\'' +
                ", damage=" + damage +
                ", heal=" + heal +
                ", defense=" + defense +
                '}';
    }

}
