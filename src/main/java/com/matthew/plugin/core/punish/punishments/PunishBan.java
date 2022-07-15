package com.matthew.plugin.core.punish.punishments;

import com.matthew.plugin.core.utils.MessageUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class PunishBan {

    public static void permBan(Player sender, Player player, String reason) {
        Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.RED.toString() + ChatColor.BOLD +"You are banned for Permanent\n" + reason, null, null);
        player.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + reason);
        MessageUtils.sendCustomMessage(sender, "Successfully banned " + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " for: ");
        MessageUtils.addToList(sender, ChatColor.GRAY + reason);
        MessageUtils.addToList(sender, ChatColor.GRAY + "Until: " + ChatColor.GOLD + "Permanent");
    }

    public static void tempBan(Player sender, Player player, Calendar time, String reason) {
        Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(), ChatColor.RED.toString() + ChatColor.BOLD +"You are banned for\n" + reason, time.getTime(), null);
        player.kickPlayer(ChatColor.RED.toString() + ChatColor.BOLD + reason);
        MessageUtils.sendCustomMessage(sender, "Successfully banned " + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " for: ");
        MessageUtils.addToList(sender, ChatColor.GRAY + reason);
        MessageUtils.addToList(sender, ChatColor.GRAY + "Until: " + ChatColor.GOLD + time.getTime());
    }

}
