package com.matthew.plugin.core.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class TeleportUtils {

    public static void tpAll(Player player) throws SQLException {
        if (RankUtils.isAdmin(player)) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (!players.equals(player)) {
                    players.teleport(player.getLocation());
                    MessageUtils.sendCustomMessage(players, "You were teleported to " + ChatColor.GOLD + player.getName());
                }
            }
            MessageUtils.sendCustomMessage(player, "Successfully teleported everyone to yourself");
        } else {
            MessageUtils.adminRank(player);
        }
    }

    public static void tp(Player player, Player target) {
        if(target != null) {
            player.teleport(target.getLocation());
            MessageUtils.sendCustomMessage(player, "You teleported to " + ChatColor.GOLD + target.getName());
        } else {
            MessageUtils.playerNotFound(player);
        }
    }

    public static void tpHere(Player player, Player target) throws SQLException {
        if (RankUtils.isAdmin(player)) {
            if (target != null) {
                target.teleport(player.getLocation());
                MessageUtils.sendCustomMessage(target, "You were teleported to " + ChatColor.GOLD + player.getName());
                MessageUtils.sendCustomMessage(player, "Successfully teleported " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " to yourself");
            } else {
                MessageUtils.playerNotFound(player);
            }
        } else {
            MessageUtils.adminRank(player);
        }
    }

    public static void tpTarget(Player sender, Player player, Player target) throws SQLException {
        if(RankUtils.isAdmin(sender)) {
            if (player != null && target != null) {
                player.teleport(target.getLocation());
                MessageUtils.sendCustomMessage(player, "You were teleported to " + ChatColor.GOLD + target.getName());
                MessageUtils.sendCustomMessage(sender, "Successfully teleported " + ChatColor.GOLD + player.getName() + ChatColor.GRAY + " to " + ChatColor.GOLD + target.getName());
            } else {
                MessageUtils.playerNotFound(sender);
            }
        } else {
            MessageUtils.adminRank(sender);
        }
    }

    public static void incorrectUsage(Player player) {
        MessageUtils.incorrectUsage(player, "/tp (player)");
        player.sendMessage(ChatColor.BLUE + "    - " + ChatColor.DARK_RED + "/tp here (player)");
        player.sendMessage(ChatColor.BLUE + "    - " + ChatColor.DARK_RED + "/tp (player) (target)");
        player.sendMessage(ChatColor.BLUE + "    - " + ChatColor.DARK_RED + "/tp all");
    }
}
