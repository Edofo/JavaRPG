package main.characterclass;

import java.util.Arrays;

public class Mage extends CharacterClass {
    public Mage() {
        super(50, 20, 5, Arrays.asList("Fireball", "Icebolt", "Lightning"));
    }
}