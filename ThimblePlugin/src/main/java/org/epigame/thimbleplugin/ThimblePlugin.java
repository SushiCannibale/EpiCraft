package org.epigame.thimbleplugin;

import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import java.util.Random;

public final class ThimblePlugin extends JavaPlugin {

//    public static NamespacedKey NAMESPACE;
    public static Random random_source;

    public static ThimblePlugin getInstance() {
        return getPlugin(ThimblePlugin.class);
    }

    @Override
    public void onLoad() {
        this.saveDefaultConfig();
    }
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        random_source = new Random();

        PluginCommand cmd = getCommand("start");
        assert(cmd != null);
        cmd.setExecutor(new StartCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
