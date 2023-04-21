package main.characterclass;

import main.abilities.*;

import java.util.ArrayList;
import java.util.List;

public class Mage extends CharacterClass {
    public Mage() {
        super(50, 20, 5, null);

        List<Abilities> abilities = new ArrayList<Abilities>();

        abilities.add(new Abilities("Fireball", AbilitiesType.ATTACK, 30, 0, 0));
        abilities.add(new Abilities("Blizzard", AbilitiesType.ATTACK, 25, 0, 0));

        this.setAbilities(abilities);
    }
}