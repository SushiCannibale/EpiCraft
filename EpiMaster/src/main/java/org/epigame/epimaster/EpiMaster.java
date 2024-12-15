package org.epigame.epimaster;

import org.bukkit.plugin.java.JavaPlugin;

public final class EpiMaster extends JavaPlugin {

    @Override
    public void onEnable() {
        saveResource("config.yml", false);
        saveDefaultConfig();

        TeamManager.getInstance().loadTeamsFromConfig(this.getConfig());
    }
}
