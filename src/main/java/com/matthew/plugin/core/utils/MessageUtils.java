package com.matthew.plugin.core.utils;

import com.matthew.plugin.core.ranks.apis.RankManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class MessageUtils {

    public static void incorrectUsage(Player player, String command) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Incorrect usage. Please use: \n" + ChatColor.BLUE + "    - " + ChatColor.YELLOW + command);
    }

    public static void adminRank(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You must have" + ChatColor.RED + " " + ChatColor.BOLD + "ADMIN " + ChatColor.GRAY + "rank or higher to use this command.");
    }

    public static void jrModRank(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You must have" + ChatColor.DARK_AQUA + " " + ChatColor.BOLD + "JR.MOD " + ChatColor.GRAY + "rank or higher to use this command.");
    }

    public static void modRank(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You must have" + ChatColor.GOLD + " " + ChatColor.BOLD + "MOD " + ChatColor.GRAY + "rank or higher to use this command.");
    }

    public static void srModRank(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You must have" + ChatColor.GOLD + " " + ChatColor.BOLD + "SR.MOD " + ChatColor.GRAY + "rank or higher to use this command.");
    }

    public static void playerNotFound(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Player not found.");
    }

    public static void sendCustomMessage(Player player, String message) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + message);
    }

    public static void commandUsage(Player player, String command) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + command + " command usage:");
    }

    public static void addToList(Player player, String message) {
        player.sendMessage(ChatColor.BLUE + "    - " + ChatColor.YELLOW + message);
    }

    public static void helpMessageHeader(Player player) throws SQLException {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + " List of permissions for " + RankManager.getRank(player).getColor().toString() + ChatColor.BOLD + RankManager.getRank(player).getName() + ChatColor.GRAY + " rank:");
        player.sendMessage(ChatColor.BLUE + "    - " + ChatColor.GRAY + ChatColor.BOLD + "Note: " + ChatColor.GRAY + "click on the permission to view the usage");
    }

    public static void helpMessageFooter(Player player) {
        player.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + "For additional help feel free to message a staff member!");
    }

}
