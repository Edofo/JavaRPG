package main;

import main.dungeon.*;
import main.characterclass.*;
import main.item.*;
import main.utils.DisplayMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameUI extends JFrame implements ActionListener {

    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel classLabel;
    private JComboBox<String> classComboBox;
    private JButton startButton;
    private JTextArea outputTextArea;

    private ArrayList<Room> dungeon = new ArrayList<>();
    private boolean isGameOver = false;
    private Party party;

    public GameUI() {
        super("Dungeon Game");

        // Set up the components
        nameLabel = new JLabel("Enter character name:");
        nameTextField = new JTextField(20);
        classLabel = new JLabel("Choose your class:");
        classComboBox = new JComboBox<>(new String[] {
                "Warrior",
                "Mage",
                "Support"
        });
        startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        outputTextArea = new JTextArea(10, 30);
        outputTextArea.setEditable(false);

        DisplayMessage.setOutputTextArea(outputTextArea);

        // Set up the layout
        JPanel inputPanel = new JPanel(new GridLayout(0, 2));
        inputPanel.add(nameLabel);
        inputPanel.add(nameTextField);
        inputPanel.add(classLabel);
        inputPanel.add(classComboBox);
        inputPanel.add(startButton);

        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);
        outputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputScrollPane, BorderLayout.CENTER);

        // Add the main panel to the frame
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameUI::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            startGame();
        }
    }

    public void startGame() {
        // Get the user's input
        String name = nameTextField.getText();
        String selectedClass = (String) classComboBox.getSelectedItem();

        // Process the user's input
        outputTextArea.append("Starting game...\n");
        outputTextArea.append("Character name: " + name + "\n");
        outputTextArea.append("Selected class: " + selectedClass + "\n");

        CharacterClass characterClass = null;
        CharacterClass ai1 = null;
        CharacterClass ai2 = null;

        switch (selectedClass) {
            case "Warrior":
                characterClass = new Warrior();
                ai1 = new Mage();
                ai2 = new Support();
                break;
            case "Mage":
                characterClass = new Mage();
                ai1 = new Warrior();
                ai2 = new Support();
                break;
            case "Support":
                characterClass = new Support();
                ai1 = new Warrior();
                ai2 = new Mage();
                break;
        }

        Heros player = new Heros(name, characterClass.getHealth(), characterClass.getAttack(),
                characterClass.getDefense(), characterClass);

        Heros ai1Character = new Heros("AI1", ai1.getHealth(), ai1.getAttack(), ai1.getDefense(),
                ai1);

        Heros ai2Character = new Heros("AI2", ai2.getHealth(), ai2.getAttack(), ai2.getDefense(),
                ai2);
        ;

        party = new Party(player, ai1Character, ai2Character);

        createDungeon();

        play();
    }

    public void createDungeon() {
        // generate random number of rooms between 5 and 10
        int numRooms = (int) (Math.random() * 6) + 5;

        outputTextArea.append("Generating dungeon with " + numRooms + " rooms...\n");

        for (int i = 0; i < numRooms; i++) {
            // check if the room is the last room
            if (i == numRooms - 1) {
                Boss boss = Boss.generateRandomBoss();

                Room room = new Room(boss);

                dungeon.add(room);
            } else {
                Room room = Room.generateRoom();

                List<Monster> monsters = room.getMonsters();

                dungeon.add(new Room(monsters.toArray(new Monster[0])));
            }
        }

        outputTextArea.append("Dungeon generated!\n");
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

            outputTextArea.append("Entering room " + (currentRoom + 1) + " with monsters:\n");

            for (Monster monster : room.getMonsters()) {
                outputTextArea.append("- " + monster.getName() + " (" + monster.getHealth() + " HP)\n");
            }

            // Code for player actions and combat
            Combat combat = new Combat(party, room.getMonsters());
            combat.startCombat();

            // Check if the party is alive
            if (party.getAlivePlayers().size() == 0) {
                isGameOver = true;
                break;
            }

            outputTextArea.append("You defeated the monsters!\n");

            room.setClear(true);

            // heal 30% all players in the party
            for (Heros player : party.getAlivePlayers()) {
                int healthMax = player.getMaxHealth();

                int healthNew = (int) (30 * healthMax / 100);

                player.heal(healthNew);

                outputTextArea.append(player.getName() + " healed to " + player.getHealth() + " HP\n");
            }

            Item item = room.generateItem();

            if (item != null) {
                outputTextArea.append("You found a " + item.getName() + "!\n");

                int choice = JOptionPane.showConfirmDialog(null, "Do you want to pick up the " + item.getName() + "?\n",
                        "Pick up item", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {

                    List<Heros> alivePlayers = party.getAlivePlayers();

                    // get names of alive players
                    String[] playerNames = new String[alivePlayers.size()];
                    for (int i = 0; i < alivePlayers.size(); i++) {
                        playerNames[i] = alivePlayers.get(i).getName();
                    }

                    int playerChoice = JOptionPane.showOptionDialog(null,
                            "Which player should pick up the " + item.getName() + "?", "Pick up item\n",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            playerNames, null);

                    Heros player = party.getAlivePlayers().get(playerChoice);

                    player.addItemToInventory(item);

                    outputTextArea.append(player.getName() + " picked up the " + item.getName() + "!\n");

                } else {
                    outputTextArea.append("You left the " + item.getName() + " on the ground.\n");
                }
            }

            currentRoom++;
        }

        if (isGameOver) {
            // check if the players of the party are alive
            if (party.getAlivePlayers().size() == 0) {
                outputTextArea.append("You died! Game over!\n");
            } else {
                outputTextArea.append("You defeated the dungeon! You win!\n");
            }

            // new game or exit
            int choice = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?\n",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                isGameOver = false;
                dungeon.clear();
                startGame();
            } else {

                System.exit(0);
            }
        }

    }
}
