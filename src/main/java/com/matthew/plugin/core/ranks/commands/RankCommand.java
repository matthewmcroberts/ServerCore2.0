package com.matthew.plugin.core.ranks.commands;

import com.matthew.plugin.core.ranks.Ranks;
import com.matthew.plugin.core.ranks.apis.RankManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class RankCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        try {
            if (RankUtils.isAdmin(player)) {
                if (args.length == 2) {

                    //if the player is online
                    if (Bukkit.getPlayerExact(args[0]) != null) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (RankUtils.isValidRank(args[1])) {
                            RankManager.setRank(target.getPlayer(), Ranks.valueOf(args[1].toUpperCase()));
                            RankUtils.assignNametag(player);
                            player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Updated " + ChatColor.GOLD + target.getName() + "'s " + ChatColor.GRAY + "rank to " + ChatColor.GOLD + args[1] + ".");
                            if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
                                if(target != player) {
                                    Bukkit.getOfflinePlayer(args[0]).getPlayer().sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Your rank has been changed to " + ChatColor.GOLD + args[1] + ".");
                                }
                            }
                        } else {
                            RankUtils.unknownRank(player);
                        }

                        //if the player is offline and is in the database
                    } else if(Bukkit.getOfflinePlayer(Bukkit.getOfflinePlayer(args[0]).getUniqueId()).hasPlayedBefore()){
                        if (RankUtils.isValidRank(args[1])) {
                            RankManager.setRank(Bukkit.getOfflinePlayer(args[0]), Ranks.valueOf(args[1].toUpperCase()));
                            player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Updated " + ChatColor.GOLD + Bukkit.getOfflinePlayer(args[0]).getName() + "'s " + ChatColor.GRAY + "rank to " + ChatColor.GOLD + args[1] + ".");

                        } else {
                            RankUtils.unknownRank(player);
                        }
                    } else {
                        MessageUtils.sendCustomMessage(player, ChatColor.YELLOW + "0" + ChatColor.GRAY + " matches for player in database.");
                    }
                } else if(args.length == 1 && args[0].equalsIgnoreCase("usage")){
                    MessageUtils.commandUsage(player, "Set rank");
                    MessageUtils.addToList(player, "/setrank (player) (rank_name)");

                } else {
                    MessageUtils.incorrectUsage(player, "/setrank (player) (rank_name)");
                }
            } else {
                MessageUtils.adminRank(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
