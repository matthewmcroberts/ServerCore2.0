package com.matthew.plugin.core.utils;

import com.matthew.plugin.core.punish.Punishments;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class PunishUtils {

    public static void sendPermBanMessage(Player sender, OfflinePlayer target, String type, String reason) {
        sender.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "Successfully banned " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " for:\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + type + "\n" +
                        ChatColor.BLUE + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + "4\n" +
                        ChatColor.BLUE + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                        ChatColor.BLUE + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + "Permanent");
    }

    public static void sendTempBanMessage(Player sender, OfflinePlayer target, String type, String reason, int sev, Timestamp expiration) {
        sender.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "Successfully banned " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " for:\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + type + "\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + sev + "\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(expiration));

    }

    public static void sendPermMuteMessages(Player sender, OfflinePlayer target, String reason) {
        sender.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "Successfully muted " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " for:\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + "Chat" + "\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + "4\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + "Permanent");
        if(target.isOnline()) {
            Player punished = (Player) target;
            punished.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "You have been muted by " + ChatColor.YELLOW + "a staff member" + ChatColor.GRAY + " for:\n" +
                    ChatColor.BLUE + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + "Chat" + "\n" +
                    ChatColor.BLUE + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + "4\n" +
                    ChatColor.BLUE + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                    ChatColor.BLUE + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + "Permanent");
        }
    }

    public static void sendTempMuteMessages(Player sender, OfflinePlayer target, String reason, int sev, Timestamp expiration) {
        String time =  new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(expiration);
        sender.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "Successfully muted " + ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " for:\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + "Chat" + "\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + sev + "\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                ChatColor.BLUE + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW +  time);
        if(target.isOnline()) {
            Player punished = (Player) target;
            punished.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "You have been muted by " + ChatColor.YELLOW + "a staff member" + ChatColor.GRAY + " for:\n" +
                    ChatColor.BLUE + "> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW + "Chat" + "\n" +
                    ChatColor.BLUE + "> " + ChatColor.GRAY + "Severity: " + ChatColor.YELLOW + sev + "\n" +
                    ChatColor.BLUE + "> " + ChatColor.GRAY + "Reason: " + ChatColor.YELLOW + reason + "\n" +
                    ChatColor.BLUE + "> " + ChatColor.GRAY + "Expires: " + ChatColor.YELLOW + time);
        }
    }

    public static void sendRemoveMessages(Player sender, OfflinePlayer target, Punishments type) {
        if(type == Punishments.CHAT) {
            sender.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "Successfully removed " + ChatColor.YELLOW + target.getName() + "'s" + ChatColor.GRAY + " mute.");
            if(target.isOnline()) {
                Player punished = (Player) target;
                punished.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "You are no longer muted.");
            }
        } else {
            sender.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "Successfully removed " + ChatColor.YELLOW + target.getName() + "'s" + ChatColor.GRAY + " ban.");
            if(target.isOnline()) {
                Player punished = (Player) target;
                punished.sendMessage(ChatColor.BLUE + "Punish> " + ChatColor.GRAY + "You are no longer banned.");
            }
        }

    }

    public static void sendRemovalMessages(Player sender, OfflinePlayer target, String type) {

    }

}
