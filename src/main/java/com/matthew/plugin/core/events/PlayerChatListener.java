package com.matthew.plugin.core.events;

import com.matthew.plugin.core.commands.silence.SilenceManager;
import com.matthew.plugin.core.ranks.Ranks;
import com.matthew.plugin.core.ranks.apis.RankManager;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.sql.SQLException;

public class PlayerChatListener implements Listener {

    /**
     * Note:
     *  JRMOD and SRMOD have their own code to put the period
     *
     * @throws SQLException - must occur due to accessing the sql database
     */
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) throws SQLException {
        Player player = e.getPlayer();

        Ranks rank = RankManager.getRank(player);

        if(RankManager.getRank(player).equals(Ranks.SRMOD)) {
            for (Player onlinePlayers : e.getRecipients()) {
                onlinePlayers.sendMessage(rank.getColor() + ChatColor.BOLD.toString() + "SR.MOD " + rank.getColor() + player.getName() + " " + ChatColor.WHITE + e.getMessage());
            }
            e.setCancelled(true);
        } else if(RankManager.getRank(player).equals(Ranks.JRMOD)) {
            for (Player onlinePlayers : e.getRecipients()) {
                onlinePlayers.sendMessage(rank.getColor() + ChatColor.BOLD.toString() + "JR.MOD " + rank.getColor() + player.getName() + " " + ChatColor.WHITE + e.getMessage());
            }
            e.setCancelled(true);
        } else {
            if(RankUtils.isNotStaff(player)) {
                if(!SilenceManager.getSilenceManager().contains(player)) {
                    for (Player onlinePlayers : e.getRecipients()) {
                        onlinePlayers.sendMessage(rank.getColor() + ChatColor.BOLD.toString() + rank.getName() + " " + rank.getColor() + player.getName() + " " + ChatColor.WHITE + e.getMessage());
                    }
                }
            } else {
                for (Player onlinePlayers : e.getRecipients()) {
                    onlinePlayers.sendMessage(rank.getColor() + ChatColor.BOLD.toString() + rank.getName() + " " + rank.getColor() + player.getName() + " " + ChatColor.WHITE + e.getMessage());
                }
            }
            e.setCancelled(true);
        }
    }
}
