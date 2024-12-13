package org.epigame.thimbleplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TeamManager {
    private static TeamManager instance;

    public synchronized static TeamManager getInstance() {
        if (instance == null) {
            instance = new TeamManager();
        }
        return instance;
    }

    public final Scoreboard main_scoreboard;

    private TeamManager() {
        this.main_scoreboard = ThimblePlugin.getInstance().getServer().getScoreboardManager().getMainScoreboard();
    }

    public void registerTeam(String name, String prefix, TextColor color) {
        Team team = this.main_scoreboard.registerNewTeam(name);
        team.prefix(Component.text(prefix));
        team.color(NamedTextColor.nearestTo(color));
    }
}
