package main.utils;

import java.util.List;

import javax.swing.JOptionPane;

public class SelectList {

    public static Integer selectIntFromList(String title, List<String> list) {
        while (true) {

            String[] choices = list.toArray(new String[0]);

            int selection = JOptionPane.showOptionDialog(null, "Select your choice:", title,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, null);

            // Adding 1 to the selection index to match the desired range of choices
            int choice = selection + 1;

            if (choice >= 1 && choice <= list.size()) {
                return choice;
            }

        }
    }
}
