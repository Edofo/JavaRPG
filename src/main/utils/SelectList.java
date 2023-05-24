package main.utils;

import java.util.List;

import javax.swing.JOptionPane;

import main.GameUI;

public class SelectList {

    public static Integer selectIntFromList(String title, List<String> list) {

        // Create a string array of the list
        String[] listArray = new String[list.size()];
        listArray = list.toArray(listArray);

        // Get user input
        String input = (String) JOptionPane.showInputDialog(null, title, "Select", JOptionPane.QUESTION_MESSAGE,
                null, listArray, listArray[0]);

        System.out.println("Input: " + input);

        // If the user cancels the input, return null
        if (input == null) {
            GameUI.stopGame();
            return null;
        }

        // Get the index of the user's selection
        int choice = list.indexOf(input) + 1;

        System.out.println("Choice: " + choice + " Selection: " + input + " List length: " + list.size());

        // print list
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        if (choice >= 1 && choice <= list.size()) {
            return choice;
        }

        // check if choice was cancelled
        if (choice == 0) {
            return 0;
        }

        return 0;
    }
}
