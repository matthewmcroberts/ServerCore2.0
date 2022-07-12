package com.matthew.plugin.core.commands.vanish.events;

import com.matthew.plugin.core.commands.vanish.VanishManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class NonVanishedPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin2(PlayerJoinEvent e) {

        Player player = e.getPlayer();

        for (int i = 0; i < VanishManager.vanished.size(); i++) {

            Player vanished = VanishManager.vanished.get(i);

            player.hidePlayer(vanished);

        }
    }
}
