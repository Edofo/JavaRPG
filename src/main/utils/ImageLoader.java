package main.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

    public static BufferedImage loadImage(String imagePath) {
        try {
            return ImageIO.read(ImageLoader.class.getResource("/resources/" + imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
