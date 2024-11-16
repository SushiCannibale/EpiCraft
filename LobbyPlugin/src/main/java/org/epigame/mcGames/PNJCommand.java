package org.epigame.mcGames;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.*;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.TestOnly;

import java.util.List;
import java.util.Random;

/**
 * Boilerplate. Spawns custom PNJ at player location
 */
public class PNJCommand implements CommandExecutor, TabExecutor {
    private Villager.Profession getRandomProfession(Random random) {
        Villager.Profession[] professions = Villager.Profession.values();
        return professions[random.nextInt(professions.length)-1];
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
        Villager villager = (Villager) player.getWorld().spawnEntity(player.getLocation().toBlockLocation(), EntityType.VILLAGER);

        villager.setNoPhysics(true);
        villager.setGravity(false);
        villager.setProfession(this.getRandomProfession(new Random()));
        villager.customName(Component.text(servername, TextColor.color(1.0F, 1.0F, 1.0F), TextDecoration.BOLD));

        /* Saves the server name in the villager data container for it to be persistent through reloads */
        villager.getPersistentDataContainer().set(McGames.NAMESPACE, PersistentDataType.STRING, servername);
        villager.setMetadata("McGames", new FixedMetadataValue(McGames.getInstance(), servername));

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
