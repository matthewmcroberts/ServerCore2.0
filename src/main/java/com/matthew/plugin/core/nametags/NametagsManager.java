package com.matthew.plugin.core.nametags;

import com.matthew.plugin.core.ranks.Ranks;
import com.matthew.plugin.core.ranks.apis.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.sql.SQLException;
import java.util.ArrayList;

public class NametagsManager {

    /**
     * Create scoreboard & teams
     *
     * @param player - player
     */
    public static void setNametags(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for (Ranks rank : Ranks.values()) {
            Team team = player.getScoreboard().registerNewTeam(rank.getLexicographicOrder() + rank.name());
            team.setPrefix(rank.getColor() + "[" + rank.getName() + "] ");
            team.setColor(rank.getColor());
        }
    }

    /**
     * Assign player to their team
     *
     * @param player - player
     */
    public static void newTag(Player player) throws SQLException {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getTeam(RankManager.getRank(player).getLexicographicOrder() + RankManager.getRank(player).getName()).addEntry(player.getName());
        }
    }

    /**
     * Remove player from all scoreboards
     *
     * @param player - player
     */
    public static void removeTag(Player player) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }
    }
}
