package main.item;

import java.util.List;

public class Weapon extends Item {
    public Weapon(String name, List<Integer> stats) {
        super(name, ItemType.WEAPON, stats);
    }

    // Method to print the weapon's stats
    public void printStats() {
        System.out.println("Name: " + this.getName());
        System.out.println("Attack: " + this.getStats().get(0));
        System.out.println("Defense: " + this.getStats().get(1));
        System.out.println("Health: " + this.getStats().get(2));
    }

    
}