package main.game;

import java.util.ArrayList;
import java.util.List;

import main.game.characterclass.*;

public class Party {
    private Heros player;
    private Heros ai1;
    private Heros ai2;

    // Constructor
    public Party(Heros player, Heros ai1, Heros ai2) {
        this.player = player;
        this.ai1 = ai1;
        this.ai2 = ai2;
    }

    public static Party createParty(String herosName, String className) {
        CharacterClass characterClass = null;
        CharacterClass ai1 = null;
        CharacterClass ai2 = null;

        switch (className) {
            case "Warrior":
                characterClass = new Warrior();
                ai1 = new Mage();
                ai2 = new Support();
                break;
            case "Mage":
                characterClass = new Mage();
                ai1 = new Warrior();
                ai2 = new Support();
                break;
            case "Support":
                characterClass = new Support();
                ai1 = new Warrior();
                ai2 = new Mage();
                break;
        }

        Heros player = new Heros(herosName, characterClass.getHealth(), characterClass.getAttack(),
                characterClass.getDefense(), characterClass);

        Heros ai1Character = new Heros("AI1", ai1.getHealth(), ai1.getAttack(), ai1.getDefense(),
                ai1);

        Heros ai2Character = new Heros("AI2", ai2.getHealth(), ai2.getAttack(), ai2.getDefense(),
                ai2);

        return new Party(player, ai1Character, ai2Character);
    }

    public Heros getPlayer() {
        return player;
    }

    public Heros getAI1() {
        return ai1;
    }

    public Heros getAI2() {
        return ai2;
    }

    // get all players
    public List<Heros> getPlayers() {
        List<Heros> players = new ArrayList<>();

        players.add(player);
        players.add(ai1);
        players.add(ai2);

        return players;
    }

    // get all alive players
    public List<Heros> getAlivePlayers() {
        List<Heros> alivePlayers = new ArrayList<>();

        for (Heros player : getPlayers()) {
            if (player.isAlive()) {
                alivePlayers.add(player);
            }
        }

        return alivePlayers;
    }

    // get all dead players
    public List<Heros> getDeadPlayers() {
        List<Heros> deadPlayers = new ArrayList<>();

        for (Heros player : getPlayers()) {
            if (!player.isAlive()) {
                deadPlayers.add(player);
            }
        }

        return deadPlayers;
    }

    // get random alive player
    public Heros getRandomAlivePlayer() {
        List<Heros> alivePlayers = getAlivePlayers();
        int randomIndex = (int) (Math.random() * alivePlayers.size());

        return alivePlayers.get(randomIndex);
    }

}
