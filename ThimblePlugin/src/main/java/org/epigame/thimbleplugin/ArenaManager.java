package org.epigame.thimbleplugin;

import jdk.jfr.Event;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArenaManager {
    private static ArenaManager instance;

    public static ArenaManager getInstance() {
        if (instance == null) {
            instance = new ArenaManager();
        }
        return instance;
    }

    private List<Arena> arenas;

    private ArenaManager() {
        this.arenas = new ArrayList<>();
    }

    public void registerArena(Arena arena) {
        this.arenas.add(arena);
    }

    public void unregisterArena(Arena arena) {
        this.arenas.remove(arena);
    }

    public void dispatch(@NotNull PlayerMoveEvent event) {
        arenas.forEach(arena -> {
            if (arena.getHasStarted()) {
                arena.handlePlayerMovement(event);
            }
        });
    }
}
