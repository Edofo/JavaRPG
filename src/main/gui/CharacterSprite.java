package main.gui;

import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CharacterSprite extends JComponent {

    // private static final int CHARACTER_WIDTH = 128;
    // private static final int CHARACTER_HEIGHT = 220;

    private BufferedImage skinImage;

    private int characterWidth = 128;
    private int characterHeight = 220;

    private int characterY = 0;

    public CharacterSprite(BufferedImage skinImage, int characterWidth, int characterHeight, int characterY) {
        this.skinImage = skinImage;
        this.characterWidth = characterWidth;
        this.characterHeight = characterHeight;
        this.characterY = characterY;
    }

    public void paint(Graphics g, int x, int y) {
        g.drawImage(skinImage, x, characterY, characterWidth, characterHeight, null);
    }

    // getters and setters
    public BufferedImage getSkinImage() {
        return skinImage;
    }

    public void setSkinImage(BufferedImage skinImage) {
        this.skinImage = skinImage;
        repaint();
    }

    public int getCharacterWidth() {
        return characterWidth;
    }

    public void setCharacterWidth(int characterWidth) {
        this.characterWidth = characterWidth;
        repaint();
    }

    public int getCharacterHeight() {
        return characterHeight;
    }

    public void setCharacterHeight(int characterHeight) {
        this.characterHeight = characterHeight;
        repaint();
    }

    public int getCharacterY() {
        return characterY;
    }

    public void setCharacterY(int characterY) {
        this.characterY = characterY;
        repaint();
    }
}
