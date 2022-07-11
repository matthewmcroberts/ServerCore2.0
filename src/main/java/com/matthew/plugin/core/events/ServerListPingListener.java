package com.matthew.plugin.core.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ServerListPingListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMotd(ChatColor.BLUE + ">>" + ChatColor.GOLD + " GoofIt's" + ChatColor.GRAY + " Private Testing Server" + ChatColor.WHITE + " [1.8.9]");
    }
}
