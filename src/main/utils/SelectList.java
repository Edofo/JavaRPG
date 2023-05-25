package main.utils;

import javax.swing.JOptionPane;

import main.game.Game;

import java.util.List;

public class SelectList {

    public static Integer selectIntFromList(String title, List<String> list) {

        // Create an array of the list
        String[] listArray = list.toArray(new String[0]);

        // Show the selection dialog
        String input = (String) JOptionPane.showInputDialog(null, title, "Select", JOptionPane.QUESTION_MESSAGE,
                null, listArray, listArray[0]);

        // System.out.println(input);

        // If the user cancels the input or closes the dialog, return null
        if (input == null) {
            Game.stopGame();
            return -1;
        }

        // Get the index of the user's selection
        int choice = list.indexOf(input) + 1;

        // Check if the choice is within the valid range
        if (choice >= 1 && choice <= list.size()) {
            return choice;
        }

        // Return 0 for invalid choices
        return 0;
    }

    public static Integer yesOrNo(String title, String message) {
        // Show the selection dialog
        int input = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);

        // If the user cancels the input or closes the dialog, return null
        if (input == JOptionPane.CLOSED_OPTION) {
            Game.stopGame();
            return null;
        }

        // Return 1 for yes, 2 for no
        return input;
    }
}
