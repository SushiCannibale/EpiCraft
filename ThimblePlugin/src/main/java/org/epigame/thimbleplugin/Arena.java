package org.epigame.thimbleplugin;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Arena {
    private boolean has_started;
    private List<Player> all_players;
    private Queue<Player> players;
    private Stack<Player> eliminated;
    private Location diving_board;
    private Location stands;

    public Arena(Location diving_board, Location stands, List<Player> players) {
        Collections.shuffle(players);

        this.has_started = false;
        this.all_players = players;
        this.players = new LinkedList<Player>(players);
        this.eliminated = new Stack<Player>();
        this.diving_board = diving_board;
        this.stands = stands;
    }

    public void roundtrip() {
        // TODO: Choose a player, TP, then countdown, ...
    }

    public void handlePlayerMovement(@NotNull PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!all_players.contains(player)) {
            return;
        }

        // TODO: Check if player is in the pool's wg region
        // if so, check if it's touching water or ground. Then enqueue back / eliminate accordingly.
    }
}
