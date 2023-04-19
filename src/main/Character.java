package main;

public class Character {

    private String name;
    private String firstName;

    private int health;
    private int attack;
    private int defense;

    // Constructor
    public Character(String name, String firstName, int health, int attack, int defense) {
        this.name = name;
        this.firstName = firstName;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    // Method to print the character's stats
    public void printStats() {
        System.out.println("Name: " + this.name);
        System.out.println("Health: " + this.health);
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
    public void heal(Character target) {
        target.health += this.attack;
        System.out.println(this.name + " heals " + target.name + " for " + this.attack + " health!");
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
