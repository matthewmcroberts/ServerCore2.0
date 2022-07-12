package com.matthew.plugin.core.commands.gamemode.events;

import com.matthew.plugin.core.commands.gamemode.api.GamemodeManager;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class GamemodeListener implements Listener {

    /**
     * If the player that left has given anyone gamemode, remove the gamemode from anyone that has been given gamemode from the player that left
     */

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent e) {

        Player player = e.getPlayer();

        if (GamemodeManager.getGamemodeToggle().containsKey(player)) {
            Player target = GamemodeManager.getGamemodeToggle().get(player);
            if (target != null && target.getGameMode() == GameMode.CREATIVE) {
                target.setGameMode(GameMode.SURVIVAL);
                target.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " left. GameMode creative:" + ChatColor.RED + " false.");
                GamemodeManager.getGamemodeToggle().remove(player);
            }
        }
        player.setGameMode(GameMode.SURVIVAL);
    }
}
