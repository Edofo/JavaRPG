package main;

import java.util.ArrayList;
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

        Character player = new Heros(name, characterClass.getHealth(), characterClass.getAttack(),
                characterClass.getDefense(), characterClass.getClass().getSimpleName());

        Character ai1Character = new Heros("AI1", ai1.getHealth(), ai1.getAttack(), ai1.getDefense(),
                ai1.getClass().getSimpleName());

        Character ai2Character = new Heros("AI2", ai2.getHealth(), ai2.getAttack(), ai2.getDefense(),
                ai2.getClass().getSimpleName());

        party = new Party(player, ai1Character, ai2Character);
    }

    public void createDungeon() {
        // create array list of monsters
        Monster[] room0Monsters = { new Monster("Skeleton", 12, 6, 4),
                new Monster("Troll", 20, 10, 8) };
        dungeon.add(new Room(room0Monsters));

        Monster[] room1Monsters = { new Monster("Skeleton", 12, 6, 4) };
        dungeon.add(new Room(room1Monsters));

        Monster[] room2Monsters = { new Monster("Troll", 20, 10, 8) };
        dungeon.add(new Room(room2Monsters));

        Monster[] room3Monsters = { new Monster("Dragon", 30, 15, 12),
                new Monster("Giant Spider", 10, 5, 3) };
        dungeon.add(new Room(room3Monsters));

        dungeon.add(new Room(new Boss("Evil Wizard", 50, 20, 15)));
    }

    public void play() {
        int currentRoom = 0;
        int numRooms = dungeon.size();

        while (!isGameOver || currentRoom < numRooms) {
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
            }

            Colors.printColoredString(Colors.GREEN, "You defeated the monsters!");

            room.setClear(true);

            // heal 30% all players in the party
            for (Character player : party.getAlivePlayers()) {
                int healthMax = player.getMaxHealth();

                int healthNew = (int) (30 * healthMax / 100);

                player.heal(healthNew);

                // System.out.println(player.getName() + " healed to " + player.getHealth() + "
                // HP");
            }

            Item item = room.generateItem();

            if (item != null) {
                Colors.printColoredString(Colors.GREEN,
                        "You found a " + item.getName() + " stats:" + item.getStats() + "!");

                int choice;

                while (true) {
                    Colors.printColoredString(Colors.BLUE, "Do you want to equip it? (1 = yes, 2 = no)");

                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        if (choice == 1 || choice == 2) {
                            break;
                        }
                    }

                    scanner.nextLine();
                    Colors.printColoredString(Colors.RED, "Invalid choice");
                }
            }

            currentRoom++;
        }

        if (!isGameOver) {

            // check if the players of the party are alive
            if (party.getAlivePlayers().size() > 0) {
                Colors.printColoredString(Colors.GREEN, "Congratulations, you finished the dungeon!");

                // Code to reward the player
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
