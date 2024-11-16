package org.epigame.mcGames;

import org.bukkit.NamespacedKey;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class McGames extends JavaPlugin {

    public static NamespacedKey NAMESPACE;

    public static McGames getInstance() {
        return getPlugin(McGames.class);
    }

    @Override
    public void onEnable() {
        /* Register messaging channels between proxy and server */
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        /* Register event listeners */
        getServer().getPluginManager().registerEvents(new EntityListener(), this);

        NAMESPACE = new NamespacedKey(McGames.getInstance(), "mcgames");

        /* Set command executors */
        PluginCommand cmd = getCommand("pnj");
        assert(cmd != null);
        cmd.setExecutor(new PNJCommand());
    }

    @Override
    public void onDisable() {
        getServer().getMessenger().unregisterOutgoingPluginChannel(this);
    }
}
