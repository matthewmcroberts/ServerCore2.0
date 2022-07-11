package com.matthew.plugin.core.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static void incorrectUsage(Player player, String command) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Incorrect usage. Please use: \n" + ChatColor.BLUE + "    - " + ChatColor.GOLD + command);
    }

    public static void adminRank(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You must have" + ChatColor.RED + " " + ChatColor.BOLD + "ADMIN " + ChatColor.GRAY + "rank or higher to use this command.");
    }

    public static void playerNotFound(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Player not found.");
    }

}
