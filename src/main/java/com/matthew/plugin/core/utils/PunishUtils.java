package com.matthew.plugin.core.utils;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Timestamp;

public class PunishUtils {

    public static void sendPermBanMessage(Player sender, OfflinePlayer target, String type, String reason) {
        sender.sendMessage(ChatColor.RED + "Punish> " + ChatColor.GRAY + "Successfully banned " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " for:\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + type + "\n" +
                        ChatColor.RED + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + "4\n" +
                        ChatColor.RED + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                        ChatColor.RED + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + "Permanent");
    }

    public static void sendTempBanMessage(Player sender, OfflinePlayer target, String type, String reason, int sev, Timestamp expiration) {
        sender.sendMessage(ChatColor.RED + "Punish> " + ChatColor.GRAY + "Successfully banned " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " for:\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + type + "\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + sev + "\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + expiration.toLocalDateTime().toString());
    }

    public static void sendPermMuteMessages(Player sender, OfflinePlayer target, String reason) {
        sender.sendMessage(ChatColor.RED + "Punish> " + ChatColor.GRAY + "Successfully muted " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " for:\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + "Chat" + "\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + "4\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + "Permanent");
        if(target.isOnline()) {
            Player punished = (Player) target;
            punished.sendMessage(ChatColor.RED + "Punish> " + ChatColor.GRAY + "You have been muted by " + ChatColor.YELLOW + "a staff member" + ChatColor.GRAY + " for:\n" +
                    ChatColor.RED + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + "Chat" + "\n" +
                    ChatColor.RED + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + "4\n" +
                    ChatColor.RED + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                    ChatColor.RED + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + "Permanent");
        }
    }

    public static void sendTempMuteMessages(Player sender, OfflinePlayer target, String reason, int sev, Timestamp expiration) {
        sender.sendMessage(ChatColor.RED + "Punish> " + ChatColor.GRAY + "Successfully muted " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " for:\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + "Chat" + "\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + sev + "\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                ChatColor.RED + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + expiration);
        if(target.isOnline()) {
            Player punished = (Player) target;
            punished.sendMessage(ChatColor.RED + "Punish> " + ChatColor.GRAY + "You have been muted by " + ChatColor.YELLOW + "a staff member" + ChatColor.GRAY + " for:\n" +
                    ChatColor.RED + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + "Chat" + "\n" +
                    ChatColor.RED + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + sev + "\n" +
                    ChatColor.RED + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                    ChatColor.RED + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + expiration.toLocalDateTime().toString());
        }
    }

}
