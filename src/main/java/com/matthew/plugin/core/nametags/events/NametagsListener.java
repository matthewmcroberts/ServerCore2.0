package com.matthew.plugin.core.nametags.events;

import com.matthew.plugin.core.nametags.NametagsManager;
import com.matthew.plugin.core.ranks.apis.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class NametagsListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) throws SQLException {

        NametagsManager.setNametags(e.getPlayer());
        NametagsManager.newTag(e.getPlayer());

        for(Player target: Bukkit.getOnlinePlayers()) {
            e.getPlayer().getScoreboard().getTeam(RankManager.getRank(target).getLexicographicOrder() + RankManager.getRank(target).getName()).addEntry(target.getName());
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        NametagsManager.removeTag(e.getPlayer());

    }
}
