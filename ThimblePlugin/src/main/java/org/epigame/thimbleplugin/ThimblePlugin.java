package org.epigame.thimbleplugin;

import org.bukkit.NamespacedKey;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public final class ThimblePlugin extends JavaPlugin {

//    public static NamespacedKey NAMESPACE;
//    public static Random random_source;

    public static ThimblePlugin getInstance() {
        return getPlugin(ThimblePlugin.class);
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(), this);

        PluginCommand cmd = getCommand("start");
        assert(cmd != null);
        cmd.setExecutor(new StartCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
