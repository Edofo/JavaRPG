package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// import main.abilities.*;
import main.dungeon.Monster;
import main.utils.Colors;
import main.utils.SelectList;

public class Combat {

    private Party party;
    private List<Monster> monsters;

    public Combat(Party party, List<Monster> monsters) {
        this.party = party;
        this.monsters = monsters;
    }

    // Create a method to start the combat
    public void startCombat(Scanner scanner) {
        int loop = 0;

        int coinFlip = 0;
        int choice = 0;

        List<String> list = new ArrayList<String>();
        list.add("  1. Tails");
        list.add("  2. Heads");
        // ask the player to choose the coin flip
        choice = SelectList.selectIntFromListScanner(scanner, "Choose heads or tails to start the combat:", list);

        // get a random number between 1 and 2
        coinFlip = (int) (Math.random() * 2) + 1;

        // check if the player's choice is the same as the coin flip
        if (choice == coinFlip) {
            // print the message
            Colors.printColoredString(Colors.GREEN, "You won the coin flip! You start the combat!");
        } else {
            // print the message
            Colors.printColoredString(Colors.RED, "You lost the coin flip! The monsters start the combat!");
        }

        // Loop until the combat is over
        while (true) {

            boolean isCombatOver = isCombatOver();
            // Check if the combat is over
            if (isCombatOver) {
                // Print the message
                System.out.println("Combat is over!");

                // Break the loop
                break;
            }

            // Increment the loop
            loop++;

            // Check if the player's turn
            if (loop % 2 == 0) {
                // Check if the player won the coin flip
                if (choice == coinFlip) {
                    monsterTurn();
                } else {
                    playerTurn(scanner);
                }
            } else {
                // Check if the player win the coin flip
                if (choice == coinFlip) {
                    playerTurn(scanner);
                } else {
                    monsterTurn();
                }
            }
        }
    }

    // Create a method for the player's turn
    private void playerTurn(Scanner scanner) {
        // Print the message
        System.out.println("It's your turn!");

        // Select a player
        Character player = selectPlayer(scanner);

        // Get the player's choice
        int playerChoice = getPlayerChoice(scanner, player);

        // Check if the player's choice is to attack
        if (playerChoice == 1) {
            // Get the monster's choice
            Monster monsterChoice = selectMonster(scanner);

            // Deal damage to the monster
            player.attack(monsterChoice);
        }
    }

    // Create a method for the monster's turn
    private void monsterTurn() {
        // Print the message
        System.out.println("It's the monster's turn!");

        // Get a random monster alive
        Monster monster = getRandomMonsterAlive();

        // Get a random player alive
        Character player = party.getRandomAlivePlayer();

        // Deal damage to the player
        monster.attack(player);
    }

    // Create a method to select a player
    private Character selectPlayer(Scanner scanner) {
        int choice;

        while (true) {
            Colors.printColoredString(Colors.BLUE, "Which player do you want to use?");

            // Loop through the players and print their names
            for (int i = 0; i < party.getPlayers().size(); i++) {
                String value = "  " + (i + 1) + ". " + party.getPlayers().get(i).getName() + " ("
                        + party.getPlayers().get(i).getHealth()
                        + " HP)";
                if (party.getPlayers().get(i).getHealth() > 0) {
                    Colors.printColoredString(Colors.YELLOW, value);
                } else {
                    Colors.printColoredString(Colors.RED_UNDERLINED, value);
                }
            }

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= party.getPlayers().size()) {
                    break;
                }
            }

            scanner.nextLine(); // consume the invalid input
            Colors.printColoredString(Colors.RED, "Invalid choice");
        }

        Character player = party.getPlayers().get(choice - 1);

        // Check if the player's choice is valid
        if (player.getHealth() <= 0) {
            // Print the message
            Colors.printColoredString(Colors.RED, "Invalid choice");

            // Get the player's choice
            return selectPlayer(scanner);
        }

        // Return the player
        return party.getPlayers().get(choice - 1);
    }

    // Create a method to get the monster's choice
    private Monster selectMonster(Scanner scanner) {
        // Print the message
        int choice;

        while (true) {
            Colors.printColoredString(Colors.BLUE, "Which monster do you want to attack?");

            // loop through the monsters and print their names
            for (int i = 0; i < monsters.size(); i++) {
                String value = "  " + (i + 1) + ". " + monsters.get(i).getName() + " (" + monsters.get(i).getHealth()
                        + " HP)";
                if (monsters.get(i).getHealth() > 0) {
                    Colors.printColoredString(Colors.YELLOW, value);
                } else {
                    Colors.printColoredString(Colors.RED_UNDERLINED, value);
                }
            }

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= monsters.size()) {
                    break;
                }
            }

            scanner.nextLine(); // consume the invalid input
            Colors.printColoredString(Colors.RED, "Invalid choice");
        }

        // check if the monster's choice is valid
        Monster monster = monsters.get(choice - 1);

        // Check if the monster is dead or not exist
        if (monster.getHealth() <= 0) {
            // Print the message
            Colors.printColoredString(Colors.RED, "Invalid choice");

            // Decrement the loop
            selectMonster(scanner);
        }

        // Return the choice
        return monster;
    }

    // Create a method to get the player's choice
    private int getPlayerChoice(Scanner scanner, Character player) {
        int choice;

        List<String> list = new ArrayList<String>();
        list.add("  1. Attack a monster");
        list.add("  2. Use abilities");

        choice = SelectList.selectIntFromListScanner(scanner, "What do you want to do?", list);

        // Return the choice
        return choice;
    }

    // Get a random monster alive
    private Monster getRandomMonsterAlive() {
        // Create a list of monsters alive
        List<Monster> monstersAlive = new ArrayList<>();

        // Loop through the monsters and check if they are alive
        for (Monster monster : monsters) {
            if (monster.getHealth() > 0) {
                monstersAlive.add(monster);
            }
        }

        // Get a random monster alive
        Monster monster = monstersAlive.get((int) (Math.random() * monstersAlive.size()));

        // Return the monster
        return monster;
    }

    // Create a method to check if the combat is over
    private boolean isCombatOver() {
        boolean isPlayerDead = true;
        // Check if the player is dead
        List<Heros> playersDead = party.getDeadPlayers();

        if (playersDead.size() == party.getPlayers().size()) {
            Colors.printColoredString(Colors.RED, "The players are dead!");
        } else {
            isPlayerDead = false;
        }

        boolean isMonsterDead = true;
        // Check if the monsters are dead
        for (Monster monster : monsters) {
            if (monster.getHealth() > 0) {
                isMonsterDead = false;
            }
        }
        if (isMonsterDead)
            Colors.printColoredString(Colors.GREEN, "The monsters are dead!");

        // Return the result
        if (isPlayerDead || isMonsterDead) {
            return true;
        } else {
            return false;
        }
    }

}
