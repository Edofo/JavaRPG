package main.game.characterclass;

import main.game.abilities.Abilities;
import main.game.abilities.AbilitiesType;

import java.util.List;

public class Support extends CharacterClass {
    public Support() {
        super(75, 5, 10, List.of(
                new Abilities("Heal", AbilitiesType.HEAL, 0, 30, 0, 15),
                new Abilities("Shield", AbilitiesType.DEFEND, 0, 0, 15, 15)), 125);
    }
}
