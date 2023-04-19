package main.characterclass;

import java.util.Arrays;

public class Warrior extends CharacterClass {
    public Warrior() {
        super(100, 10, 10, Arrays.asList("Slash", "Stab", "Pierce"));
    }

    // get abilities of class
    public String getAbilitiesOfClass() {

        return "Warrior Abilities: " + String.join(", ", super.getAbilities());
    }
}
