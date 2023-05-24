package main;

import java.util.ArrayList;
import java.util.List;

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
