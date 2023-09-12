package com.matthew.plugin.core.punish.events;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.Punishments;
import com.matthew.plugin.core.punish.apis.dbquerys.ActivePunishments;
import com.matthew.plugin.core.punish.apis.types.PunishBan;
import com.matthew.plugin.core.punish.apis.types.PunishMute;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.sql.SQLException;
import java.sql.Timestamp;

public class PunishListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) throws SQLException {

        Player player = (Player) e.getWhoClicked();

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).contains(ChatColor.BLUE + "Punish - ") && e.getCurrentItem() != null) {
            e.setCancelled(true);
            Bukkit.getOfflinePlayer(e.getView().getTitle().substring(11));
            OfflinePlayer target = Bukkit.getOfflinePlayer(e.getView().getTitle().substring(11));

            switch (e.getRawSlot()) {
                case 20: //mute sev 1
                    Timestamp time = new Timestamp(System.currentTimeMillis());
                    time.setTime((long) (time.getTime() + 8.64e7)); //1 Day
                    player.closeInventory();
                    PunishMute.tempMute(player, target, 1, ServerCore.punishReason.get(player), time);

                    break;
                case 29: //mute sev 2
                    time = new Timestamp(System.currentTimeMillis());
                    time.setTime((long) (time.getTime() + 6.048e8)); //7 Days
                    player.closeInventory();
                    PunishMute.tempMute(player, target, 2, ServerCore.punishReason.get(player), time);

                    break;
                case 38: //mute sev 3
                    time = new Timestamp(System.currentTimeMillis());
                    time.setTime((long) (time.getTime() + 2.592e9)); //30 Days
                    player.closeInventory();
                    PunishMute.tempMute(player, target, 3, ServerCore.punishReason.get(player), time);

                    break;
                case 22: //gameplay ban sev 1
                    time = new Timestamp(System.currentTimeMillis());
                    time.setTime((long) (time.getTime() + 3.6e6)); // 1hr
                    player.closeInventory();
                    PunishBan.tempBan(player, target, Punishments.GAMEPLAY, 1, ServerCore.punishReason.get(player), time);

                    break;
                case 31: //gameplay ban sev 2
                    time = new Timestamp(System.currentTimeMillis());
                    time.setTime((long) (time.getTime() + 8.64e7)); //1 Day
                    player.closeInventory();
                    PunishBan.tempBan(player, target, Punishments.GAMEPLAY, 2, ServerCore.punishReason.get(player), time);

                    break;
                case 40: //gameplay ban sev 3
                    time = new Timestamp(System.currentTimeMillis());
                    time.setTime((long) (time.getTime() + 6.048e8)); //7 Days
                    player.closeInventory();
                    PunishBan.tempBan(player, target, Punishments.GAMEPLAY, 3, ServerCore.punishReason.get(player), time);

                    break;
                case 24: //hacking ban sev 1
                    time = new Timestamp(System.currentTimeMillis());
                    time.setTime((long) (time.getTime() + 8.64e7)); //1 Day
                    player.closeInventory();
                    PunishBan.tempBan(player, target, Punishments.HACKING, 1, ServerCore.punishReason.get(player), time);

                    break;

                case 33: //hacking ban sev 2
                    time = new Timestamp(System.currentTimeMillis());
                    time.setTime((long) (time.getTime() + 6.048e8)); //7 Days
                    player.closeInventory();
                    PunishBan.tempBan(player, target, Punishments.HACKING, 2, ServerCore.punishReason.get(player), time);

                    break;
                case 42: //hacking ban sev 3
                    time = new Timestamp(System.currentTimeMillis());
                    time.setTime((long) (time.getTime() + 2.592e9)); //30 Days
                    player.closeInventory();
                    PunishBan.tempBan(player, target, Punishments.HACKING, 3, ServerCore.punishReason.get(player), time);

                    break;
                case 27: //perm mute
                    player.closeInventory();
                    PunishMute.permMute(player, target, ServerCore.punishReason.get(player));

                    break;
                case 36: //perm ban
                    player.closeInventory();
                    PunishBan.permBan(player, target, Punishments.GAMEPLAY, ServerCore.punishReason.get(player));

                    break;
                default:
                    break;
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerLoginEvent e) throws SQLException {
        Player player = e.getPlayer();
        if(ActivePunishments.isBanned(player)) {
            Punishments type = ActivePunishments.getBanType(player);
            String reason = ActivePunishments.getReason(player, type);
            String expiration = ActivePunishments.getExpirationDate(player, type);
            if(ActivePunishments.isPermanent(player, type)) {
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.translateAlternateColorCodes('&', "§c§lYou are Permanently banned\n§7Reason:§f "
                        + reason));
            } else {
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, ChatColor.translateAlternateColorCodes('&', "§c§lYou are banned\n§7Reason:§f " + reason + "\n§7Expires: §f" +  expiration));
            }
        }
    }

}
