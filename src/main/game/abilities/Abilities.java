package main.game.abilities;

import main.game.Character;

import main.utils.DisplayMessage;

public class Abilities {
    private String name;

    private AbilitiesType type;

    private int damage;
    private int heal;
    private int defense;
    // private int manaCost;

    public Abilities(String name, AbilitiesType type, int damage, int heal, int defense) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.heal = heal;
        this.defense = defense;
    }

    // use the abilities and change the stats of the player
    public void useAbilities(Character character, Character target) {
        switch (type) {
            case HEAL:
                character.heal(target, heal);
                break;
            case ATTACK:
                character.attack(target, damage);
                character.heal(this.heal);
                break;
            case DEFEND:
                target.setDefense(target.getDefense() + defense);
                break;
            // case BUFF:
            // player.setDamage(player.getDamage() + damage);
            // break;
            // case DEBUFF:
            // player.setDamage(player.getDamage() - damage);
            // break;
            default:
                DisplayMessage.outputTextArea("Invalid ability type");
                break;
        }
    }

    // Getters and Setters for each attribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbilitiesType getType() {
        return type;
    }

    public void setType(AbilitiesType type) {
        this.type = type;
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

}
