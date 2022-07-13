package com.matthew.plugin.core.commands.inventory.events;

import com.matthew.plugin.core.commands.inventory.apis.InventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {

        if (e.getEntity() != null) {
            Player p = e.getEntity();

            if (e.getDrops() != null) {
                InventoryManager.setInv(p);
                InventoryManager.setArmor(p);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {

        Player p = e.getPlayer();

        if (InventoryManager.getPlayerArmor().containsKey(p) || InventoryManager.getPlayerInv().containsKey(p)) {

            InventoryManager.getPlayerInv().remove(p);
            InventoryManager.getPlayerArmor().remove(p);

        }
    }
}
