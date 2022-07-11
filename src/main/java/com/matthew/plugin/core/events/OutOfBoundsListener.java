package com.matthew.plugin.core.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OutOfBoundsListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {

        Player player = e.getPlayer();
        World w = player.getWorld();

        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();

        if(x >= 416.0 || x <= 278.0 || y >= 137.0 || y <= -7.0 || z <= -78.0 || z>= 59.0) {
            player.teleport(new Location(player.getWorld(), 347.511, 32.0, -9.613));
        }
    }
}
