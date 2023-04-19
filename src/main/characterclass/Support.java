package main.characterclass;

import java.util.Arrays;

public class Support extends CharacterClass {
    public Support() {
        super(100, 5, 10, Arrays.asList("Heal", "Shield", "Bless"));
    }
}