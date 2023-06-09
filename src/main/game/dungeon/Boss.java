package main.game.dungeon;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

import main.gui.CharacterSprite;
import main.utils.ImageLoader;

public class Boss extends Monster {
    public Boss(String name, int health, int attack, int defense, CharacterSprite sprite) {
        super(name, health, attack, defense, sprite);
    }

    // generate a random boss
    public static Boss generateRandomBoss() {

        Random random = new Random();

        List<String> bossNames = List.of(
                "Evil Wizard",
                "Evil King",
                "Evil Queen",
                "Evil Prince",
                "Evil Princess",
                "Evil Duke");

        List<Integer> bossStats1 = List.of(50, 100, 75, 65, 55, 90);
        List<Integer> bossStats2 = List.of(40, 20, 30, 25, 35, 15);
        List<Integer> bossStats3 = List.of(15, 30, 25, 25, 20, 35);

        int randomIndex = random.nextInt(bossNames.size());

        String bossName = bossNames.get(randomIndex);
        int stat1 = bossStats1.get(randomIndex);
        int stat2 = bossStats2.get(randomIndex);
        int stat3 = bossStats3.get(randomIndex);

        BufferedImage image = ImageLoader.loadImage("monsters/boss.png");

        int width = 300;
        int height = 400;

        CharacterSprite sprite = new CharacterSprite(image, width, height, 25);

        return new Boss(bossName, stat1, stat2, stat3, sprite);
    }

}
