package org.epigame.thimbleplugin;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class StartCommand implements CommandExecutor {

    public static final Pattern ARENA_PATTERN = Pattern.compile("thimble*");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command.");
            return false;
        }

        /* Randomly chooses a WorldGuard's region (defined previously) as  */
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager manager = container.get(BukkitAdapter.adapt(player.getWorld()));
        if (manager == null) {
            sender.sendMessage("No regions defined !");
            return false;
        }

        Map<String, ProtectedRegion> regions = manager.getRegions();
        List<ProtectedRegion> playable_regions = new ArrayList<>();
        regions.forEach((name, rg) -> {
            if (ARENA_PATTERN.matcher(name).matches()) {
                playable_regions.add(rg);
            }
        });

        if (playable_regions.isEmpty()) {
            sender.sendMessage("No regions matching 'thimble*' defined !");
            return false;
        }

        ProtectedRegion region = playable_regions.get(ThimblePlugin.random_source.nextInt(playable_regions.size()));

//        Arena area = new Arena();

        return true;
    }
}
