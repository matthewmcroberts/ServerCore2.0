package com.matthew.plugin.core.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinLeaveListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!player.hasPlayedBefore()) {
            e.setJoinMessage(ChatColor.BLUE + "Welcome> " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " to the server!");
        } else {
            e.setJoinMessage(ChatColor.BLUE + "Join> " + ChatColor.GRAY + player.getName());
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.BLUE + "Quit> " + ChatColor.GRAY + player.getName());
    }
}
