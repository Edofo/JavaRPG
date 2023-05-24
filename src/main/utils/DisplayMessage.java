package main.utils;

import javax.swing.JTextArea;

public class DisplayMessage {
    private static JTextArea outputTextArea;

    private DisplayMessage() {
        // Private constructor to prevent instantiation
    }

    public static void outputTextArea(String message) {
        if (outputTextArea != null) {
            outputTextArea.append(message + "\n");
        }
    }

    public static void setOutputTextArea(JTextArea outputTextArea) {
        DisplayMessage.outputTextArea = outputTextArea;
    }

    public static JTextArea getOutputTextArea() {
        return outputTextArea;
    }

}
