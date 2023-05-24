package main.gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.game.Game;
import main.utils.ImageLoader;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameInterface extends JFrame implements ActionListener {

    private BackgroundPanel backgroundPanel;

    private JPanel currentPanel;
    private JButton startButton;
    private JButton exitButton;

    // private static final int FRAME_WIDTH = 775;
    // private static final int FRAME_HEIGHT = 425;

    public GameInterface() {
        // Set up the main frame
        setTitle("JAVA RPG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Set the default size of the frame
        // setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        // Load the skin images
        BufferedImage characterSkin = ImageLoader.loadImage("character_skin.png");
        BufferedImage backgroundSkin = ImageLoader.loadImage("background_skin.png");

        backgroundPanel = new BackgroundPanel(backgroundSkin);
        backgroundPanel.setCharacter(new CharacterSprite(characterSkin, 128, 220, 130), 0, 0);

        startButton = new JButton("Start Game");
        exitButton = new JButton("Exit Game");

        // Set up the button panel
        currentPanel = new JPanel();
        currentPanel.add(startButton);
        currentPanel.add(exitButton);

        // Add the background panel to the frame
        add(backgroundPanel);

        // Add the button panel to the frame
        add(currentPanel, BorderLayout.SOUTH);

        // Add the action listeners
        startButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Pack and display the frame
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            Game.startGame(this);
        }

        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public void setCurrentPanel(JPanel panel) {
        remove(currentPanel);
        currentPanel = panel;
        currentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(currentPanel, BorderLayout.SOUTH);
        pack();
    }

    public BufferedImage getBackgroundImage() {
        return backgroundPanel.getBackgroundImage();
    }

    public void setBufferedImage(BufferedImage image) {
        backgroundPanel.setBackgroundImage(image);
    }

    public BackgroundPanel getBackgroundPanel() {
        return backgroundPanel;
    }
}
