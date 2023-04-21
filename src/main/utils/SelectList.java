package main.utils;

import java.util.List;
import java.util.Scanner;

public class SelectList {

    public static Integer selectIntFromListScanner(Scanner scanner, String title, List<String> list) {
        int choice;

        while (true) {
            Colors.printColoredList(title, list);

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= list.size()) {
                    break;
                }
            }

            scanner.nextLine();
            Colors.printColoredString(Colors.RED, "Invalid choice");
        }

        return choice;
    }
}
