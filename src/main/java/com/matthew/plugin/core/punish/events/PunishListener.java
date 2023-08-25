package com.matthew.plugin.core.punish.events;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.apis.types.PunishBan;
import com.matthew.plugin.core.punish.apis.types.PunishMute;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

public class PunishListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) throws SQLException {

        Player player = (Player) e.getWhoClicked();
        Calendar cal = Calendar.getInstance();

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).contains(ChatColor.BLUE + "Punish - ") && e.getCurrentItem() != null) {
            e.setCancelled(true);
            if (Bukkit.getOfflinePlayer(e.getView().getTitle().substring(11)) != null) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(e.getView().getTitle().substring(11));

                switch (e.getRawSlot()) {
                    case 20: //mute sev 1
                        Timestamp time = new Timestamp(System.currentTimeMillis());
                        time.setTime((long) (time.getTime() + 8.64e7)); //1 Day
                        player.closeInventory();
                        PunishMute.tempMute(player, target, 1, ServerCore.punishReason.get(player), time);
                        ServerCore.punishReason.remove(player);

                        break;
                    case 29: //mute sev 2
                        time = new Timestamp(System.currentTimeMillis());
                        time.setTime((long) (time.getTime() + 6.048e8)); //7 Days
                        player.closeInventory();
                        PunishMute.tempMute(player, target, 2, ServerCore.punishReason.get(player), time);
                        ServerCore.punishReason.remove(player);

                        break;
                    case 38: //mute sev 3
                        time = new Timestamp(System.currentTimeMillis());
                        time.setTime((long) (time.getTime() + 2.592e9)); //30 Days
                        player.closeInventory();
                        PunishMute.tempMute(player, target, 3, ServerCore.punishReason.get(player), time);
                        ServerCore.punishReason.remove(player);

                        break;
                    case 22: //gameplay ban sev 1
                        time = new Timestamp(System.currentTimeMillis());
                        time.setTime((long) (time.getTime() + 3.6e6)); // 1hr
                        player.closeInventory();
                        PunishBan.tempBan(player, target, "Gameplay", 1, ServerCore.punishReason.get(player), time);
                        ServerCore.punishReason.remove(player);

                        break;
                    case 31: //gameplay ban sev 2
                        time = new Timestamp(System.currentTimeMillis());
                        time.setTime((long) (time.getTime() + 8.64e7)); //1 Day
                        player.closeInventory();
                        PunishBan.tempBan(player, target, "Gameplay", 2, ServerCore.punishReason.get(player), time);
                        ServerCore.punishReason.remove(player);

                        break;
                    case 40: //gameplay ban sev 3
                        time = new Timestamp(System.currentTimeMillis());
                        time.setTime((long) (time.getTime() + 6.048e8)); //7 Days
                        player.closeInventory();
                        PunishBan.tempBan(player, target, "Gameplay", 3, ServerCore.punishReason.get(player), time);
                        ServerCore.punishReason.remove(player);

                        break;
                    case 24: //hacking ban sev 1
                        time = new Timestamp(System.currentTimeMillis());
                        time.setTime((long) (time.getTime() + 8.64e7)); //1 Day
                        player.closeInventory();
                        PunishBan.tempBan(player, target, "Hacking", 1, ServerCore.punishReason.get(player), time);
                        ServerCore.punishReason.remove(player);

                        break;

                    case 33: //hacking ban sev 2
                        time = new Timestamp(System.currentTimeMillis());
                        time.setTime((long) (time.getTime() + 6.048e8)); //7 Days
                        player.closeInventory();
                        PunishBan.tempBan(player, target, "Hacking", 2, ServerCore.punishReason.get(player), time);
                        ServerCore.punishReason.remove(player);

                        break;
                    case 42: //hacking ban sev 3
                        time = new Timestamp(System.currentTimeMillis());
                        time.setTime((long) (time.getTime() + 2.592e9)); //30 Days
                        player.closeInventory();
                        PunishBan.tempBan(player, target, "Hacking", 3, ServerCore.punishReason.get(player), time);
                        ServerCore.punishReason.remove(player);

                        break;
                    case 27: //perm mute
                        player.closeInventory();
                        PunishMute.permMute(player, target, ServerCore.punishReason.get(player));
                        ServerCore.punishReason.remove(player);

                        break;
                    case 36: //perm ban
                        player.closeInventory();
                        PunishBan.permBan(player, target, "Gameplay", ServerCore.punishReason.get(player));
                        ServerCore.punishReason.remove(player);

                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).contains(ChatColor.GOLD + "Punish - ")) {
            //ServerCore.punishReason.remove(e.getPlayer());
        }
    }
/*
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!e.getPlayer().hasPlayedBefore()) {
            try {
                ResultSet rs = ServerCore.getInstance().preparedStatement("SELECT COUNT(UUID) FROM player_punishment WHERE UUID = '" + player.getUniqueId().toString() + "';").executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) { //Meaning 0 records match the players UUID, so player doesn't exist in the Database
                    ServerCore.getInstance().preparedStatement("INSERT INTO player_punishment(UUID, MUTED, PERM, TEMP) VALUES ('" + player.getUniqueId() + "'," + "DEFAULT, DEFAULT, DEFAULT);").executeUpdate();

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    */

}
