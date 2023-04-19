package main.dungeon;

import java.util.HashMap;
import java.util.Random;

public class Boss extends Monster {
    public Boss(String name, int health, int attack, int defense) {
        super(name, health, attack, defense);
    }

    // generate a random boss
    public static Boss generateRandomBoss() {
        Random random = new Random();

        HashMap<Integer, Boss> bosses = new HashMap<>();

        bosses.put(0, new Boss("Evil Wizard", 50, 40, 15));
        bosses.put(1, new Boss("Evil King", 100, 20, 30));
        bosses.put(2, new Boss("Evil Queen", 75, 30, 25));
        bosses.put(3, new Boss("Evil Prince", 65, 25, 25));
        bosses.put(4, new Boss("Evil Princess", 55, 35, 20));
        bosses.put(5, new Boss("Evil Duke", 90, 15, 35));

        int randomInt = random.nextInt(bosses.size());

        return bosses.get(randomInt);
    }
}
