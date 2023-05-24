package main;

import javax.swing.SwingUtilities;

import main.gui.GameInterface;

public class App {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(GameInterface::new);

    }
}
