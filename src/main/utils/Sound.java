package main.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

    // Method to play a sound
    public static void playSound(String soundPath) {
        try {
            // Load the sound file from the resources folder
            File soundFile = new File(Sound.class.getResource("/resources/" + soundPath).toURI());

            // Load the sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            // Get the audio format
            AudioFormat audioFormat = audioInputStream.getFormat();

            // Create a data line info object for the source data line
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);

            // Check if the system supports the data line
            if (!AudioSystem.isLineSupported(info)) {
                System.out.println("Audio line is not supported");
                return;
            }

            // Open the source data line
            SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceLine.open(audioFormat);

            // Start the source data line
            sourceLine.start();

            // Create a buffer to read data from the audio input stream
            byte[] buffer = new byte[4096];
            int bytesRead = 0;

            // Read data from the audio input stream and write it to the source data line
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                sourceLine.write(buffer, 0, bytesRead);
            }

            // Close the source data line and audio input stream
            sourceLine.drain();
            sourceLine.close();
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
