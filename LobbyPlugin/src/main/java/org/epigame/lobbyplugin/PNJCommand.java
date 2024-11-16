package org.epigame.lobbyplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

/**
 * Boilerplate. Spawns custom PNJ at player location
 */
public class PNJCommand implements CommandExecutor, TabExecutor {
    private Villager.Profession getRandomProfession() {
        Villager.Profession[] professions = Villager.Profession.values();
        return professions[LobbyPlugin.random_source.nextInt(professions.length)-1];
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (! (sender instanceof Player player)) {
            return false;
        }

        if (args.length != 1) {
            return false;
        }

        String servername = args[0];
        Location loc = player.getLocation().toBlockLocation().add(0.5D, 0.5D, 0.5D);
        Villager villager = (Villager) player.getWorld().spawnEntity(loc, EntityType.VILLAGER);
        villager.setAI(false);
        villager.lookAt(player);
        villager.setProfession(getRandomProfession());
        villager.customName(Component.text(servername, TextColor.color(1.0F, 1.0F, 1.0F), TextDecoration.BOLD));

        /* Saves the server name in the villager data container for it to be persistent through reloads */
        villager.getPersistentDataContainer().set(LobbyPlugin.NAMESPACE, PersistentDataType.STRING, servername);
        villager.setMetadata("McGames", new FixedMetadataValue(LobbyPlugin.getInstance(), servername));

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1) {

            return List.of("lobby", "parkour", "thimble", "uhc");
        }
        return List.of();
    }
}
