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

        while (true) {
            Colors.printColoredList("Choose your class:", new String[] {
                    "  1. Warrior",
                    "  2. Mage",
                    "  3. Support",
            });

            if (scanner.hasNextInt()) {
                classChoice = scanner.nextInt();
                if (classChoice >= 1 && classChoice <= 3) {
                    break;
                }
            }

            scanner.nextLine();
            Colors.printColoredString(Colors.RED, "Invalid choice");
        }

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

            Item item = room.generateItem();

            if (item != null) {
                Colors.printColoredString(Colors.GREEN,
                        "You found a new Item!");
                item.printStats();

                int choice;
                boolean isNextRoom = false;

                while (!isNextRoom) {
                    Colors.printColoredList("Do you want to equit it?", new String[] {
                            "  1. Yes",
                            "  2. No",
                    });

                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        if (choice == 1) {
                            int playerChoice;

                            while (true) {
                                Colors.printColoredString(Colors.BLUE, "Choose a player to equip the item:");

                                for (int i = 0; i < party.getAlivePlayers().size(); i++) {
                                    Character player = party.getAlivePlayers().get(i);
                                    Colors.printColoredString(Colors.YELLOW,
                                            "  " + (i + 1) + ". " + player.getName() + " ("
                                                    + player.getHealth() + " HP)");
                                }

                                if (scanner.hasNextInt()) {
                                    playerChoice = scanner.nextInt();
                                    if (playerChoice >= 1 && playerChoice <= 3) {
                                        Heros player = party.getAlivePlayers().get(playerChoice - 1);

                                        player.addItemToInventory(item);

                                        Colors.printColoredString(Colors.GREEN, "Item equipped!");

                                        isNextRoom = true;
                                        break;
                                    }
                                }

                                scanner.nextLine();
                                Colors.printColoredString(Colors.RED, "Invalid choice");
                            }

                        } else {
                            isNextRoom = true;
                            break;
                        }
                    }

                    scanner.nextLine();
                    Colors.printColoredString(Colors.RED, "Invalid aachoice");
                }
            }

            currentRoom++;
        }

        if (!isGameOver) {
            // check if the players of the party are alive
            if (party.getAlivePlayers().size() > 0) {
                Colors.printColoredString(Colors.GREEN, "Congratulations, you finished the dungeon!");

                // new game or exit
                int choice;

                while (true) {
                    Colors.printColoredList("Do you want to play again?", new String[] {
                            "  1. Yes",
                            "  2. No",
                    });

                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        if (choice == 1 || choice == 2) {
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
                            break;
                        }
                    }

                    scanner.nextLine();
                    Colors.printColoredString(Colors.RED, "Invalid choice");
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
