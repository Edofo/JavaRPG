package main.characterclass;

import main.abilities.Abilities;
import main.abilities.AbilitiesType;

import java.util.List;

public class Support extends CharacterClass {
    public Support() {
        super(75, 5, 10, List.of(
                new Abilities("Heal", AbilitiesType.HEAL, 0, 30, 0),
                new Abilities("Shield", AbilitiesType.DEFEND, 0, 0, 15)));
    }
}
