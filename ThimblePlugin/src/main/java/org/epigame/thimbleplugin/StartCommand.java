package org.epigame.thimbleplugin;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StartCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command.");
            return false;
        }

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(player.getWorld()));
        if (manager == null) {
            sender.sendMessage("No regions defined !");
            return false;
        }

        ProtectedRegion pool_region = manager.getRegion("pool");
        if (pool_region == null) {
            sender.sendMessage("No pool region defined !");
            return false;
        }

        Location jump = new Location(player.getWorld(), -49.0D, 93.0D, 164.0D);
//        Location stands = new Location(player.getWorld(), -63.0D, 79.0D, 164.0D);
        List<Player> online_players = new ArrayList<>(Bukkit.getOnlinePlayers()); // F**k type capture

        Arena arena = new Arena(jump, pool_region, online_players);
        ArenaManager.getInstance().registerArena(arena); // Mainly for event dispatching

        Bukkit.getScheduler().runTaskAsynchronously(ThimblePlugin.getInstance(), arena::start);

        return true;
    }
}
