package org.epigame.thimbleplugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.epigame.thimbleplugin.inventory.TeamSelectionInventory;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class EventListener implements Listener {
    @EventHandler
    public static void onPlayerConnects(@NonNull PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.openInventory(new TeamSelectionInventory().getInventory());
//        TeamManager.getInstance().main_scoreboard
    }

    @EventHandler
    public static void onPlayerTick(@NonNull ) {
        // TODO: Force player to choose a team if it has not
    }

    @EventHandler
    public static void onPlayerMove(@NonNull PlayerMoveEvent e) {
        ArenaManager.getInstance().dispatch(e);
    }
}
