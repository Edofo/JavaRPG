package main.characterclass;

import java.util.Arrays;

public class Warrior extends CharacterClass {
    public Warrior() {
        super(100, 10, 10, Arrays.asList("Slash", "Stab", "Pierce"));
    }

}
