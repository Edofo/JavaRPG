package main.utils;

import javax.swing.JTextArea;

public class DisplayMessage {

    private static JTextArea outputTextArea;

    public static void setOutputTextArea(JTextArea outputTextArea) {
        DisplayMessage.outputTextArea = outputTextArea;
    }

    public static void outputTextArea(String message) {
        outputTextArea.append(message + "\n");
    }
}
