package main;

import java.util.ArrayList;
import java.util.List;

import main.utils.Colors;

public class Party {
    private Character player;
    private Character ai1;
    private Character ai2;

    // Constructor
    public Party(Character player, Character ai1, Character ai2) {
        this.player = player;
        this.ai1 = ai1;
        this.ai2 = ai2;
    }

    public Character getPlayer() {
        return player;
    }

    public Character getAI1() {
        return ai1;
    }

    public Character getAI2() {
        return ai2;
    }

    // get all players
    public List<Character> getPlayers() {
        List<Character> players = new ArrayList<>();

        players.add(player);
        players.add(ai1);
        players.add(ai2);

        return players;
    }

    // get all alive players
    public List<Character> getAlivePlayers() {
        List<Character> alivePlayers = new ArrayList<>();

        for (Character player : getPlayers()) {
            if (player.isAlive()) {
                alivePlayers.add(player);
            }
        }

        return alivePlayers;
    }

    // get all dead players
    public List<Character> getDeadPlayers() {
        List<Character> deadPlayers = new ArrayList<>();

        for (Character player : getPlayers()) {
            if (!player.isAlive()) {
                deadPlayers.add(player);
            }
        }

        return deadPlayers;
    }

    // get random alive player
    public Character getRandomAlivePlayer() {
        List<Character> alivePlayers = getAlivePlayers();
        int randomIndex = (int) (Math.random() * alivePlayers.size());

        return alivePlayers.get(randomIndex);
    }

    // kill player
    public void killPlayer(Character player) {
        player.setHealth(0);

        Colors.printColoredString(Colors.RED, player.getName() + " is dead!");
    }

}
