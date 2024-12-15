package org.epigame.epimaster;

import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;

import java.util.List;

public final class Team {
    private String name;
    private String prefix;
    private String suffix;
    private TextColor color;
    private List<Player> members;

    public Team(String name, String prefix, String suffix, TextColor color, List<Player> members) {
        this.name = name;
        this.prefix = prefix;
        this.suffix = suffix;
        this.color = color;
        this.members = members;
    }

    public void addPlayer(Player player) {
        members.add(player);
    }

    public void register() {
        
    }
}
