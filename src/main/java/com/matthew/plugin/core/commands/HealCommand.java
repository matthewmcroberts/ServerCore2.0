package com.matthew.plugin.core.commands;

import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class HealCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        try {
            if (RankUtils.isMod(player)) {
                if (sender instanceof Player)
                    if (args.length == 1) {
                        if(!args[0].equalsIgnoreCase("usage")) {
                            if (Bukkit.getPlayerExact(args[0]) != null) {
                                if (Bukkit.getPlayerExact(args[0]).getGameMode() != GameMode.CREATIVE) {
                                    Player target = Bukkit.getPlayerExact(args[0]);
                                    target.setHealth(20.0);
                                    target.setFoodLevel(20);
                                    if (target != player) {
                                        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " has been" +
                                                ChatColor.GREEN + " healed.");
                                        target.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You have been" +
                                                ChatColor.GREEN + " healed.");
                                    } else {
                                        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GOLD + target.getName() + ChatColor.GRAY + " has been" +
                                                ChatColor.GREEN + " healed.");
                                    }
                                } else {
                                    player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You cannot heal a player in" + ChatColor.GOLD +
                                            " creative");
                                }
                            } else {
                                MessageUtils.playerNotFound(player);
                            }
                        } else {
                            MessageUtils.commandUsage(player, "Heal");
                            MessageUtils.addToList(player, "/heal");
                            MessageUtils.addToList(player, "/heal (player)");

                        }
                    } else if (args.length == 0) {
                        if (player.getGameMode() != GameMode.CREATIVE) {
                            player.setHealth(20.0);
                            player.setFoodLevel(20);
                            player.sendMessage(
                                    ChatColor.BLUE + ">> " + ChatColor.GRAY + "You have been" + ChatColor.GREEN + " healed.");
                        } else {
                            player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You cannot heal while in" + ChatColor.GOLD +
                                    " creative.");
                        }
                    } else if (args.length > 1) {
                        MessageUtils.incorrectUsage(player, "/heal");
                        MessageUtils.addToList(player, "/heal (player)");
                    }
            } else {
                MessageUtils.modRank(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

