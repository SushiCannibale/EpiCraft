package org.epigame.thimbleplugin;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.jspecify.annotations.NonNull;

public class EventListener implements Listener {
    @EventHandler
    public static void onPlayerConnects(@NonNull PlayerJoinEvent e) {

    }

    @EventHandler
    public static void onPlayerMove(@NonNull PlayerMoveEvent e) {
        ArenaManager.getInstance().dispatch(e);
    }
}
