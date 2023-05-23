package main.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class SelectList {

    public static Integer selectIntFromList(String title, List<String> list) {
        while (true) {
            // Colors.printColoredList(title, list);

            // Collections.reverse(list); // Reverse the order of the list

            String[] choices = list.toArray(new String[0]);
            JComboBox<String> comboBox = new JComboBox<>(choices);

            int selection = JOptionPane.showOptionDialog(null, "Select your choice:", title,
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, null);

            // Adding 1 to the selection index to match the desired range of choices
            int choice = selection + 1;

            if (choice >= 1 && choice <= list.size()) {
                return choice;
            }

            // Colors.printColoredString(Colors.RED, "Invalid choice");
        }
    }
}
