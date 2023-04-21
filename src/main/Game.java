package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.characterclass.*;
import main.dungeon.*;
import main.item.Item;
import main.utils.*;

public class Game {
    private ArrayList<Room> dungeon;
    private boolean isGameOver;
    private Scanner scanner;
    private Party party;

    public Game() {
        dungeon = new ArrayList<>();
        isGameOver = false;
        scanner = new Scanner(System.in);
    }

    public void createCharacter() {
        Colors.printColoredString(Colors.BLUE, "Enter character name:");
        String name = scanner.nextLine();

        int classChoice;

        List<String> list = new ArrayList<String>();
        list.add("  1. Warrior");
        list.add("  2. Mage");
        list.add("  3. Support");

        classChoice = SelectList.selectIntFromListScanner(scanner, "Choose you class:", list);

        CharacterClass characterClass = null;
        CharacterClass ai1 = null;
        CharacterClass ai2 = null;

        switch (classChoice) {
            case 1:
                characterClass = new Warrior();
                ai1 = new Mage();
                ai2 = new Support();
                break;
            case 2:
                characterClass = new Mage();
                ai1 = new Warrior();
                ai2 = new Support();
                break;
            case 3:
                characterClass = new Support();
                ai1 = new Warrior();
                ai2 = new Mage();
                break;
            default:
                System.out.println("Invalid class choice");
                break;
        }

        Heros player = new Heros(name, characterClass.getHealth(), characterClass.getAttack(),
                characterClass.getDefense(), characterClass.getClass().getSimpleName());

        Heros ai1Character = new Heros("AI1", ai1.getHealth(), ai1.getAttack(), ai1.getDefense(),
                ai1.getClass().getSimpleName());

        Heros ai2Character = new Heros("AI2", ai2.getHealth(), ai2.getAttack(), ai2.getDefense(),
                ai2.getClass().getSimpleName());

        party = new Party(player, ai1Character, ai2Character);
    }

    public void createDungeon() {
        // generate random number of rooms between 5 and 10
        int numRooms = (int) (Math.random() * 6) + 5;

        System.out.println("Generating dungeon with " + numRooms + " rooms...");

        for (int i = 0; i < numRooms; i++) {
            // check if the room is the last room
            if (i == numRooms - 1) {
                Boss boss = Boss.generateRandomBoss();

                Room room = new Room(boss);

                dungeon.add(room);
            } else {
                // generate random number of monsters between 1 and 3
                int numMonsters = (int) (Math.random() * 3) + 1;

                List<Monster> monsters = Monster.generateRandomMonsterList(numMonsters);

                dungeon.add(new Room(monsters.toArray(new Monster[0])));
            }
        }

        System.out.println("Dungeon generated!");

    }

    public void play() {
        int currentRoom = 0;
        int numRooms = dungeon.size();

        while (!isGameOver) {
            if (currentRoom == numRooms) {
                isGameOver = true;
                break;
            }

            Room room = dungeon.get(currentRoom);

            Colors.printColoredString(Colors.RED_BACKGROUND_BRIGHT,
                    "Entering room " + (currentRoom + 1) + " with monsters:");

            for (Monster monster : room.getMonsters()) {
                System.out.println("- " + monster.getName() + " (" + monster.getHealth() + " HP)");
            }

            // Code for player actions and combat
            Combat combat = new Combat(party, room.getMonsters());
            combat.startCombat(scanner);

            // Check if the party is alive
            if (party.getAlivePlayers().size() == 0) {
                isGameOver = true;
                break;
            }

            Colors.printColoredString(Colors.GREEN, "You defeated the monsters!");

            room.setClear(true);

            // heal 30% all players in the party
            for (Heros player : party.getAlivePlayers()) {
                int healthMax = player.getMaxHealth();

                int healthNew = (int) (30 * healthMax / 100);

                player.heal(healthNew);

                // System.out.println(player.getName() + " healed to " + player.getHealth() + "
                // HP");
            }

            if (currentRoom + 1 == dungeon.size()) {
                currentRoom++;
                isGameOver = true;
                break;
            }

            Item item = room.generateItem();

            if (item != null) {
                Colors.printColoredString(Colors.GREEN,
                        "You found a new Item!");
                item.printStats();

                int equipChoice;

                List<String> list = new ArrayList<String>();
                list.add("  1. Yes");
                list.add("  2. No");

                equipChoice = SelectList.selectIntFromListScanner(scanner, "Do you want to equit it?", list);

                if (equipChoice == 1) {
                    int playerChoice;

                    List<String> listPlayer = new ArrayList<String>();

                    for (int i = 0; i < party.getAlivePlayers().size(); i++) {
                        Character player = party.getAlivePlayers().get(i);
                        listPlayer.add("  " + (i + 1) + ". " + player.getName() + " (" + player.getHealth() + " HP)");
                    }

                    playerChoice = SelectList.selectIntFromListScanner(scanner, "Choose a player to equip the item:",
                            listPlayer);

                    Heros player = party.getAlivePlayers().get(playerChoice - 1);

                    player.addItemToInventory(item);

                    Colors.printColoredString(Colors.GREEN, "Item equipped!");
                }

            }

            currentRoom++;
        }

        if (isGameOver) {
            // check if the players of the party are alive
            if (party.getAlivePlayers().size() > 0) {
                Colors.printColoredString(Colors.GREEN, "Congratulations, you finished the dungeon!");

                // new game or exit
                int choice;

                List<String> list = new ArrayList<String>();
                list.add("  1. Yes");
                list.add("  2. No");

                choice = SelectList.selectIntFromListScanner(scanner, "Do you want to play again?", list);

                if (choice == 1) {

                    isGameOver = false;
                    currentRoom = 0;
                    party = null;
                    dungeon = new ArrayList<>();
                    createCharacter();
                    createDungeon();
                    play();
                } else {
                    Colors.printColoredString(Colors.RED, "Goodbye!");
                }

            } else {
                Colors.printColoredString(Colors.RED, "You died, game over!");
            }

        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.createCharacter();
        game.createDungeon();
        game.play();
    }
}
