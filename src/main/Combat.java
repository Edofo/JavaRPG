package main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.abilities.*;

import main.characterclass.CharacterClass;

import main.dungeon.Monster;

import main.item.*;
import main.utils.*;

public class Combat {

    private Party party;
    private List<Monster> monsters;

    public Combat(Party party, List<Monster> monsters) {
        this.party = party;
        this.monsters = monsters;
    }

    // Create a method to start the combat
    public void startCombat() {
        int loop = 0;

        int coinFlip = 0;
        int choice = 0;

        List<String> list = new ArrayList<String>();
        list.add("Heads");
        list.add("Tails");
        // ask the player to choose the coin flip
        choice = SelectList.selectIntFromList("Choose heads or tails to start the combat:", list);

        if (choice == 1) {
            DisplayMessage.outputTextArea("You chose heads!");
        } else {
            DisplayMessage.outputTextArea("You chose tails!");
        }

        // get a random number between 1 and 2
        coinFlip = (int) (Math.random() * 2) + 1;

        // check if the player's choice is the same as the coin flip
        if (choice == coinFlip) {
            DisplayMessage.outputTextArea("You won the coin flip! You start the combat!");
        } else {
            DisplayMessage.outputTextArea("You lost the coin flip! The monsters start the combat!");
        }

        // Loop until the combat is over
        while (true) {

            boolean isCombatOver = isCombatOver();
            // Check if the combat is over
            if (isCombatOver) {
                // Print the message
                DisplayMessage.outputTextArea("Combat is over!");

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
                    // Print the message
                    DisplayMessage.outputTextArea("It's your turn!");

                    playerTurn();
                }
            } else {
                // Check if the player win the coin flip
                if (choice == coinFlip) {
                    // Print the message
                    DisplayMessage.outputTextArea("It's your turn!");

                    playerTurn();
                } else {
                    monsterTurn();
                }
            }
        }
    }

    // Create a method for the player's turn
    private void playerTurn() {
        // Select a player
        Heros player = selectPlayer();

        // Get the player's choice
        int playerChoice = getPlayerChoice(player);

        switch (playerChoice) {
            // attack
            case 1:
                // Get the monster's choice
                Monster monsterChoice = selectMonster();

                // Deal damage to the monster
                player.attack(monsterChoice);
                break;
            // use spell
            case 2:
                Abilities abilities = selectAbilities(player);

                if (abilities.getType() == AbilitiesType.HEAL || abilities.getType() == AbilitiesType.DEFEND) {
                    Heros playerChoice2 = selectPlayer();

                    abilities.useAbilities(player, playerChoice2);

                    break;
                }

                Monster monsterChoice2 = selectMonster();

                abilities.useAbilities(player, monsterChoice2);
                break;
            // use item
            case 3:
                Item item = selectItem(player);

                if (item == null) {
                    playerTurn();
                    return;
                }

                Heros playerChoice3 = selectPlayer();

                item.useItem(playerChoice3);

                // Remove the item from the player's inventory
                player.removeItemFromInventory(item);

                break;
            // back
            case 4:
                playerTurn();
                return;
            default:
                return;
        }

    }

    // Create a method for the monster's turn
    private void monsterTurn() {
        // Print the message
        DisplayMessage.outputTextArea("It's the monster's turn!");

        // Get a random monster alive
        Monster monster = getRandomMonsterAlive();

        // Get a random player alive
        Character player = party.getRandomAlivePlayer();

        // Deal damage to the player
        monster.attack(player);
    }

    // Create a method to select a player
    private Heros selectPlayer() {
        int choice;

        List<String> list = new ArrayList<String>();

        List<Heros> players = party.getPlayers().stream().filter(player -> player.getHealth() > 0)
                .collect(Collectors.toList());

        for (int i = 0; i < players.size(); i++) {
            String value = players.get(i).getName() + " (" + players.get(i).getHealth() + " HP)";
            if (players.get(i).getHealth() > 0) {
                list.add(value);
            } else {
                list.add(value);
            }
        }

        choice = SelectList.selectIntFromList("Which player do you want to use?", list);

        Character player = players.get(choice - 1);

        // Check if the player's choice is valid
        if (player.getHealth() <= 0) {
            // Print the message
            DisplayMessage.outputTextArea("Invalid choice");

            // Get the player's choice
            return selectPlayer();
        }

        // Return the player
        return party.getPlayers().get(choice - 1);
    }

    // Create a method to get the player's choice
    private int getPlayerChoice(Character player) {
        List<String> list = new ArrayList<String>();
        list.add("Attack a monster");
        list.add("Use abilities");
        list.add("Use item");
        list.add("Back");

        int choice = SelectList.selectIntFromList("What do you want to do?", list);

        // Return the choice
        return choice;
    }

    // Create a method to get the monster's choice
    private Monster selectMonster() {
        // Print the message
        int choice;

        List<String> list = new ArrayList<String>();

        for (int i = 0; i < monsters.size(); i++) {
            String value = monsters.get(i).getName() + " (" + monsters.get(i).getHealth()
                    + " HP)";
            if (monsters.get(i).getHealth() > 0) {
                list.add(value);
            } else {
                list.add(value);
            }
        }

        choice = SelectList.selectIntFromList("Which monster do you want to attack?", list);

        // check if the monster's choice is valid
        Monster monster = monsters.get(choice - 1);

        // Check if the monster is dead or not exist
        if (monster.getHealth() <= 0) {
            // Print the message
            // Colors.printColoredString(Colors.RED, "Invalid choice");

            // Decrement the loop
            selectMonster();
        }

        // Return the choice
        return monster;
    }

    // Create a method to get the abilities choice
    private Abilities selectAbilities(Heros player) {
        CharacterClass characterClass = player.getCharacterClass();
        List<Abilities> abilities = characterClass.getAbilities();

        List<String> list = new ArrayList<String>();

        for (Abilities abilitie : abilities) {
            list.add(abilitie.getName());
        }

        int choice = SelectList.selectIntFromList("What do you want to do?", list);

        Abilities selectedAbilities = abilities.get(choice - 1);

        return selectedAbilities;
    }

    // Create a method to get the item choice
    private Item selectItem(Heros player) {
        List<Item> items = player.getInventory();

        // filter to have only potion
        items = items.stream().filter(item -> item instanceof Potion).collect(Collectors.toList());

        List<String> list = new ArrayList<String>();
        list.add("Back");

        for (Item item : items) {
            list.add(item.getName());
        }

        // add the back option

        int choice = SelectList.selectIntFromList("What do you want to do?", list);

        Item selectedItem = items.get(choice - 1);

        return selectedItem;
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
            DisplayMessage.outputTextArea("The players are dead!");
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

        if (isMonsterDead) {
            DisplayMessage.outputTextArea("The monsters are dead!");
        }

        // Return the result
        if (isPlayerDead || isMonsterDead) {
            return true;
        } else {
            return false;
        }
    }

}
