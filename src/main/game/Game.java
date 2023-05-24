package main.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import main.game.dungeon.Dungeon;
import main.game.dungeon.Monster;
import main.game.dungeon.Room;
import main.game.item.Item;
import main.gui.BackgroundPanel;
import main.gui.CharacterSprite;
import main.gui.GameInterface;
import main.utils.DisplayMessage;
import main.utils.SelectList;

public class Game {

    private static GameInterface gameInterface;

    private static Dungeon dungeon;
    private static Party party;

    public Game(Dungeon dungeon, Party party) {
        Game.dungeon = dungeon;
        Game.party = party;
    }

    public static void startGame(GameInterface gameInterface) {
        Game.gameInterface = gameInterface;

        createParty();
    }

    public static void createParty() {
        // Create the input panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        JTextField nameTextField = new JTextField(20);
        JComboBox<String> classComboBox = new JComboBox<>(new String[] {
                "Warrior",
                "Mage",
                "Support"
        });
        JButton nextButton = new JButton("Next");

        // Add components to the input panel
        inputPanel.add(new JLabel("Enter your name: "));
        inputPanel.add(nameTextField);
        inputPanel.add(new JLabel("Choose your class:"));
        inputPanel.add(classComboBox);
        inputPanel.add(new JLabel());
        inputPanel.add(nextButton);

        // Set the input panel as the current panel in GameInterface
        gameInterface.setCurrentPanel(inputPanel);

        // Add the action listener to the next button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Get the name and class from the input fields
                String name = nameTextField.getText();
                String className = (String) classComboBox.getSelectedItem();

                // Create the party
                party = Party.createParty(name, className);

                // Create the dungeon
                dungeon = Dungeon.generateRandomDungeon();

                play();
            }
        });

    }

    public static void play() {
        int numRooms = dungeon.getRooms().size();

        // transform currentPanel to a outputtextArea
        JPanel outputPanel = new JPanel(new BorderLayout());
        JTextArea outputTextArea = new JTextArea(10, 50);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setPreferredSize(new Dimension(500, 100));

        outputPanel.add(scrollPane, BorderLayout.CENTER);

        gameInterface.setCurrentPanel(outputPanel);

        DisplayMessage.setOutputTextArea(outputTextArea);

        for (int currentRoom = 0; currentRoom < numRooms; currentRoom++) {
            Room room = dungeon.getRooms().get(currentRoom);

            // System.out.println("Room " + (currentRoom + 1) + " of " + numRooms);

            // change the background image
            gameInterface.setBufferedImage(room.getBackgroundImage());

            BackgroundPanel bg = gameInterface.getBackgroundPanel();

            // get the monsters in the room
            List<CharacterSprite> monstersSprite = new ArrayList<>();

            List<Monster> monsters = room.getMonsters();

            for (Monster monster : monsters) {
                monstersSprite.add(monster.getSprite());
            }

            bg.setMonsters(monstersSprite);

            Combat combat = new Combat(party, room.getMonsters());

            combat.startCombat();

            // Check if the party is alive
            if (party.getAlivePlayers().size() == 0) {
                currentRoom = numRooms;
                break;
            }

            DisplayMessage.outputTextArea("You defeated the monsters!\n");

            room.setClear(true);

            if (dungeon.isCleared()) {
                // DisplayMessage.outputTextArea("You cleared the dungeon!\n");
                break;
            }

            // heal 30% all players in the party
            for (Heros player : party.getAlivePlayers()) {
                int healthMax = player.getMaxHealth();

                int healthNew = (int) (30 * healthMax / 100);

                player.heal(healthNew);

                DisplayMessage.outputTextArea(player.getName() + " healed to " + player.getHealth() + " HP\n");
            }

            Item item = room.generateItem();

            if (item != null) {
                DisplayMessage.outputTextArea("You found a " + item.getName() + "!\n");

                int choice = SelectList.yesOrNo("Do you want to pick up the" + item.getName() + "?\n", "Pick up item");

                if (choice == JOptionPane.YES_OPTION) {

                    List<Heros> alivePlayers = party.getAlivePlayers();

                    // get names of alive players
                    List<String> playerNames = new ArrayList<>();

                    for (Heros player : alivePlayers) {
                        playerNames.add(player.getName());
                    }

                    // get the player's choice
                    int playerChoice = SelectList.selectIntFromList("Select a player do you want to equip",
                            playerNames);

                    Heros player = party.getAlivePlayers().get(playerChoice - 1);

                    player.addItemToInventory(item);

                    DisplayMessage.outputTextArea(player.getName() + " picked up the " + item.getName() + "!\n");

                } else {
                    DisplayMessage.outputTextArea("You left the " + item.getName() + " on the ground.\n");
                }
            }

        }
    }

    public static void stopGame() {
        DisplayMessage.outputTextArea("Game stopped!\n");

        JPanel buttonPanel = new JPanel(new GridLayout(0, 2));
        JButton newGameButton = new JButton("New Game");
        JButton exitButton = new JButton("Exit");

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                createParty();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(newGameButton);
        buttonPanel.add(exitButton);

        gameInterface.setCurrentPanel(buttonPanel);

        // new game or exit
        // int choice = JOptionPane.showConfirmDialog(null, "Do you want to play
        // again?", "Play again?\n",
        // JOptionPane.YES_NO_OPTION);

        // if (choice == JOptionPane.YES_OPTION) {
        // // startGame();
        // } else {
        // System.exit(0);
        // }
    }

    // getters and setters
    public static Dungeon getDungeon() {
        return dungeon;
    }

    public static Party getParty() {
        return party;
    }

}
