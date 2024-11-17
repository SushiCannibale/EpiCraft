package org.epigame.thimbleplugin;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Arena {
    private boolean has_started;
    private List<Player> all_players;
    private Queue<Player> player_queue;
    private Stack<Player> eliminated;

    private final Location diving_board;
    private final ProtectedRegion pool_region;

    public Arena(Location diving_board, ProtectedRegion pool_region, List<Player> players) {
        Collections.shuffle(players);

        this.has_started = false;
        this.all_players = players;
        this.player_queue = new LinkedList<Player>(players);
        this.eliminated = new Stack<Player>();

        this.diving_board = diving_board;
        this.pool_region = pool_region;
    }

    public void stop() {
        this.has_started = false;
    }

    public void start() {
        this.has_started = true;
        for (Player p : this.all_players) {
            p.setGameMode(GameMode.SPECTATOR);
            p.teleport(this.diving_board);
        }

        this.roundtrip();
    }

    public boolean getHasStarted() {
        return this.has_started;
    }

    public void roundtrip() {
        Player player = this.player_queue.poll();
        if (player == null) {
            // End of the game. Winner is on top of eliminated stack
            Player winner = this.eliminated.peek();
            Bukkit.getServer().sendMessage(Component.text().color(TextColor.color(0x55FF55))
                    .append(winner.displayName())
                    .append(Component.text(" won the game !")));
            this.stop();
            return;
        }
        player.teleport(this.diving_board);
        player.setGameMode(GameMode.ADVENTURE);
        /* Then waits for the event... */
    }

    public void handlePlayerMovement(@NotNull PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (!all_players.contains(player)) {
            return;
        }

        Location pos = player.getLocation();
        if (!this.pool_region.contains(pos.getBlockX(), pos.getBlockY(), pos.getBlockZ())) {
            return;
        }

        if (Math.abs(player.getVelocity().getY()) <= 0.01D) {
            /* Consider player is not falling anymore */
            if (pos.getBlock().getType() == Material.WATER) {
                player.getWorld().setBlockData(pos, Material.SPRUCE_LEAVES.createBlockData());
                this.player_queue.offer(player);
            } else {
                Bukkit.getServer().sendMessage(Component.text().color(TextColor.color(0xFF5555))
                        .append(player.displayName())
                        .append(Component.text(" is eliminated !")));
                this.eliminated.push(player);
            }
            player.setGameMode(GameMode.SPECTATOR);
            this.roundtrip();
        }
    }
}
