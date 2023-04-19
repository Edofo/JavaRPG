package main;

public class Character {

    private String name;

    private int maxHealth;
    private int health;
    private int attack;
    private int defense;

    // Constructor
    public Character(String name, int health, int attack, int defense) {
        this.name = name;
        this.maxHealth = health;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    // Method to print the character's stats
    public void printStats() {
        System.out.println("Name: " + this.name);
        System.out.println("Health: " + this.health + "/" + this.maxHealth + " ("
                + (int) ((double) this.health / this.maxHealth * 100) + "%)");
        System.out.println("Attack: " + this.attack);
        System.out.println("Defense: " + this.defense);
    }

    // Method to print the character's name
    public void printName() {
        System.out.println("Name: " + this.name);
    }

    // Method to attack another character
    public void attack(Character opponent) {
        int damage = this.attack - (int) (Math.random() * opponent.defense);

        if (damage > 0) {
            opponent.health -= damage;
            System.out.println(this.name + " attacks " + opponent.name + " for " + damage + " damage!");

            if (opponent.health <= 0) {
                opponent.kill();
            }
        } else {
            System.out.println(this.name + " attacks " + opponent.name + " but does no damage.");
        }
    }

    // Method to heal another character
    public void heal(Character target, int amount) {
        target.health += amount;
        if (target.health > target.maxHealth) {
            target.health = target.maxHealth;
        }
        System.out.println(this.name + " heals " + target.name + " for " + amount + " health!");
    }

    // heal self
    public void heal(int amount) {
        this.health += amount;
        if (this.health > this.maxHealth) {
            this.health = this.maxHealth;
        }
        // System.out.println(this.name + " heals for " + amount + " health!");
    }

    // Method to check if a character is alive
    public boolean isAlive() {
        if (this.health <= 0) {
            return false;
        } else {
            return true;
        }
    }

    // Method to kill a character
    public void kill() {
        this.health = 0;
        System.out.println(this.name + " has died!");
    }

    // Getters and setters for each attribute :
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

}
