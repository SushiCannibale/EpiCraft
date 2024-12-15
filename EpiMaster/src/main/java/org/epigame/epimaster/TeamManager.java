package org.epigame.epimaster;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TeamManager {
    private static TeamManager instance;

    public static synchronized TeamManager getInstance() {
        if (instance == null) {
            instance = new TeamManager();
        }
        return instance;
    }

    private final List<Team> teams;
    private TeamManager() {
        teams = new ArrayList<Team>();
    }

    public void loadTeamsFromConfig(FileConfiguration config) {
        ConfigurationSection sect = config.getConfigurationSection("teams");
        Set<String> names = sect.getKeys(false);
        names.forEach(name -> {
            String color = config.getString("teams." + name + ".color");
            int limit = config.getInt("teams." + name + ".limit");
            Team team = new Team(name, "", "", TextColor.fromHexString(color), new ArrayList<>(limit));

            this.teams.add(team);
        });
    }
}
