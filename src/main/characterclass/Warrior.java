package main.characterclass;

import java.util.ArrayList;
import java.util.List;

import main.abilities.*;

public class Warrior extends CharacterClass {
    public Warrior() {
        super(100, 10, 10, null);

        List<Abilities> abilities = new ArrayList<Abilities>();

        abilities.add(new Abilities("Slash", AbilitiesType.ATTACK, 20, 0, 0));
        abilities.add(new Abilities("Vampirism", AbilitiesType.ATTACK, 15, 5, 5));

        this.setAbilities(abilities);
    }

}
