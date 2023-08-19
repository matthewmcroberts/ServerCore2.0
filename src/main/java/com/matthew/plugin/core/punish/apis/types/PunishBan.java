package com.matthew.plugin.core.punish.apis.types;

import com.matthew.plugin.core.utils.MessageUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class PunishBan {

    public static void permBan(Player sender, OfflinePlayer player, String reason) {
        Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.RED.toString() + ChatColor.BOLD +"You are banned for Permanent\n" + reason, null, null);
        MessageUtils.sendCustomMessage(sender, "Successfully banned " + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " for: ");
        MessageUtils.addToList(sender, ChatColor.GRAY + reason);
        MessageUtils.addToList(sender, ChatColor.GRAY + "Until: " + ChatColor.GOLD + "Permanent");
        if(player.isOnline()) {
            player.getPlayer().kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "You have been banned for " + reason);
        }
    }

    public static void tempBan(Player sender, OfflinePlayer player, Calendar time, String reason) {
        Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.RED.toString() + ChatColor.BOLD +"You are banned for\n" + reason, time.getTime(), null);
        MessageUtils.sendCustomMessage(sender, "Successfully banned " + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " for: ");
        MessageUtils.addToList(sender, ChatColor.GRAY + reason);
        MessageUtils.addToList(sender, ChatColor.GRAY + "Until: " + ChatColor.GOLD + time.getTime());
        if(player.isOnline()) {
            player.getPlayer().kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + "You have been banned for " + reason);
        }
    }

}
