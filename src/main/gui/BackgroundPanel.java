package main.gui;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.List;

public class BackgroundPanel extends JPanel {

    private BufferedImage background;
    private List<CharacterSprite> character;

    private List<CharacterSprite> monsters;

    public BackgroundPanel(BufferedImage background) {
        this.background = background;
        setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        if (character != null && !character.isEmpty()) {
            int characterX = 10;

            for (CharacterSprite character : character) {

                character.paint(g, characterX, character.getCharacterY());
                characterX += 75;

            }
        }

        if (monsters != null && !monsters.isEmpty()) {
            int spaceBetweenMonsters = 100; // Adjust the space between character and monster as needed
            int monsterX = (int) (getWidth() / 1.4);

            for (CharacterSprite monster : monsters) {
                monster.paint(g, monsterX, monster.getCharacterY());

                monsterX -= monster.getWidth() + spaceBetweenMonsters;
            }
        }
    }

    // getters and setters
    public BufferedImage getBackgroundImage() {
        return background;
    }

    public void setBackgroundImage(BufferedImage background) {
        this.background = background;
        setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
        repaint();
    }

    public List<CharacterSprite> getCharacter() {
        return character;
    }

    public void setCharacter(List<CharacterSprite> character) {
        this.character = character;
        repaint();
    }

    public List<CharacterSprite> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<CharacterSprite> monsters) {
        this.monsters = monsters;
        repaint();
    }

}
