package com.matthew.plugin.core.commands.silence.events;

import com.matthew.plugin.core.commands.silence.SilenceManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class SilenceListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        for(Player target: Bukkit.getOnlinePlayers()) {
            if(SilenceManager.getSilenceManager().contains(target) && !SilenceManager.getSilenceManager().contains(player)) {
                SilenceManager.getSilenceManager().add(player);
            }
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) throws SQLException {
        Player player = e.getPlayer();

        if(SilenceManager.getSilenceManager().contains(player)) {
            if(RankUtils.isNotStaff(player)) {
                MessageUtils.sendCustomMessage(player, "Shh... Chat is currently" + org.bukkit.ChatColor.RED + " silenced!");
                e.setCancelled(true);
            }
        }
    }
}
