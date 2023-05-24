package main.gui;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.util.List;

public class BackgroundPanel extends JPanel {

    private BufferedImage background;
    private CharacterSprite character;

    private List<CharacterSprite> monsters;

    public BackgroundPanel(BufferedImage background) {
        this.background = background;
        setPreferredSize(new Dimension(background.getWidth(), background.getHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);

        if (character != null) {
            character.paint(g, 20, character.getCharacterY());
        }

        if (monsters != null && !monsters.isEmpty()) {
            int spaceBetweenMonsters = -50; // Adjust the space between character and monster as needed
            int monsterX = getWidth();

            for (CharacterSprite monster : monsters) {
                monsterX -= monster.getCharacterWidth() + spaceBetweenMonsters;

                monster.paint(g, monsterX, monster.getCharacterY());
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

    public CharacterSprite getCharacter() {
        return character;
    }

    public void setCharacter(CharacterSprite character, int x, int y) {
        this.character = character;
        repaint(x, y, character.getCharacterWidth(), character.getCharacterHeight());
    }

    public List<CharacterSprite> getMonsters() {
        return monsters;
    }

    public void setMonsters(List<CharacterSprite> monsters) {
        this.monsters = monsters;
        repaint();
    }

}
