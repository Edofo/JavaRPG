package main.characterclass;

import java.util.ArrayList;
import java.util.List;

import main.abilities.*;

public class Support extends CharacterClass {
    public Support() {
        super(75, 5, 10, null);

        List<Abilities> abilities = new ArrayList<Abilities>();

        abilities.add(new Abilities("Heal", AbilitiesType.HEAL, 0, 30, 0));
        abilities.add(new Abilities("Shield", AbilitiesType.DEFEND, 0, 0, 15));

        this.setAbilities(abilities);
    }
}