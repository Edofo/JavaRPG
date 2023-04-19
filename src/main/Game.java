package main;

import java.util.ArrayList;
import java.util.Scanner;

import main.characterclass.*;
import main.dungeon.*;
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

        Colors.printColoredString(Colors.BLUE, "Enter character first name:");
        String firstName = scanner.nextLine();

        Colors.printColoredString(Colors.BLUE, "Choose character class:");
        Colors.printColoredString(Colors.YELLOW, "  1. Warrior");
        Colors.printColoredString(Colors.YELLOW, "  2. Mage");
        Colors.printColoredString(Colors.YELLOW, "  3. Support");
        int classChoice = scanner.nextInt();

        scanner.nextLine(); // Consume newline character

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

        Character player = new Heros(name, firstName, characterClass.getHealth(), characterClass.getAttack(),
                characterClass.getDefense(), characterClass.getClass().getSimpleName());

        Character ai1Character = new Heros("AI1", "AI1", ai1.getHealth(), ai1.getAttack(), ai1.getDefense(),
                ai1.getClass().getSimpleName());

        Character ai2Character = new Heros("AI2", "AI2", ai2.getHealth(), ai2.getAttack(), ai2.getDefense(),
                ai2.getClass().getSimpleName());

        party = new Party(player, ai1Character, ai2Character);
    }

    public void createDungeon() {
        // create array list of monsters
        Monster[] room0Monsters = { new Monster("Skeleton", "lv 1", 12, 6, 4),
                new Monster("Troll", "lv 2", 20, 10, 8) };
        dungeon.add(new Room(room0Monsters));

        Monster[] room1Monsters = { new Monster("Skeleton", "lv 3", 12, 6, 4) };
        dungeon.add(new Room(room1Monsters));

        Monster[] room2Monsters = { new Monster("Troll", "lv 5", 20, 10, 8) };
        dungeon.add(new Room(room2Monsters));

        Monster[] room3Monsters = { new Monster("Dragon", "lv 6", 30, 15, 12),
                new Monster("Giant Spider", "lv 6", 10, 5, 3) };
        dungeon.add(new Room(room3Monsters));

        dungeon.add(new Room(new Boss("Evil Wizard", "lv 15", 50, 20, 15)));
    }

    public void play() {
        int currentRoom = 0;
        int numRooms = dungeon.size();

        while (!isGameOver && currentRoom < numRooms) {
            Room room = dungeon.get(currentRoom);

            System.out.println("Entering room " + (currentRoom + 1) + " with monsters:");

            for (Monster monster : room.getMonsters()) {
                System.out.println("- " + monster.getName() + " (" + monster.getHealth() + " HP)");
            }

            // Code for player actions and combat
            Combat combat = new Combat(party, room.getMonsters());
            combat.startCombat(scanner);

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
