package main.characterclass;

import main.abilities.Abilities;
import main.abilities.AbilitiesType;

import java.util.List;

public class Mage extends CharacterClass {
    public Mage() {
        super(50, 20, 5, List.of(
                new Abilities("Fireball", AbilitiesType.ATTACK, 30, 0, 0),
                new Abilities("Blizzard", AbilitiesType.ATTACK, 25, 0, 0)));
    }
}
