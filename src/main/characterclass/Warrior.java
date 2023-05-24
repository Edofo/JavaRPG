package main.characterclass;

import main.abilities.Abilities;
import main.abilities.AbilitiesType;

import java.util.List;

public class Warrior extends CharacterClass {
    public Warrior() {
        super(100, 10, 10, List.of(
                new Abilities("Slash", AbilitiesType.ATTACK, 20, 0, 0),
                new Abilities("Vampirism", AbilitiesType.ATTACK, 15, 5, 5)));
    }
}
