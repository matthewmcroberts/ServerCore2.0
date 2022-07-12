package com.matthew.plugin.core.commands.vanish.events;

import com.matthew.plugin.core.commands.vanish.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class VanishedPlayerQuitEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent e) throws SQLException {
        Player p = e.getPlayer();

        if (VanishManager.getIfVanished(p)) {
            for (Player target : Bukkit.getOnlinePlayers()) {
                target.hidePlayer(p);
                e.setQuitMessage(null);
                if (VanishManager.vanished.contains(p)) {
                    VanishManager.vanished.remove(p);
                }
            }
        }
    }
}
