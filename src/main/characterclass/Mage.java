package main.characterclass;

import java.util.Arrays;

public class Mage extends CharacterClass {
    public Mage() {
        super(100, 10, 5, Arrays.asList("Fireball", "Icebolt", "Lightning"));
    }
}