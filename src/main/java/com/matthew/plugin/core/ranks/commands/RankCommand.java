package com.matthew.plugin.core.ranks.commands;

import com.matthew.plugin.core.ServerCore;
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
                    if (Bukkit.getOfflinePlayer(args[0]).hasPlayedBefore()) {
                        if (RankUtils.isValidRank(args[1])) {
                            RankManager.setRank(Bukkit.getPlayer(args[0]), Ranks.valueOf(args[1].toUpperCase()));
                            Player target = Bukkit.getPlayerExact(args[0]);
                            player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Updated " + ChatColor.GOLD + target.getName() + "'s " + ChatColor.GRAY + "rank to " + ChatColor.GOLD + args[1] + ".");
                            if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
                                if(target != player) {
                                    Bukkit.getOfflinePlayer(args[0]).getPlayer().sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Your rank has been changed to " + ChatColor.GOLD + args[1] + ".");
                                }
                            }
                        } else {
                            RankUtils.unknownRank(player);
                        }
                    } else {
                        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GOLD + args[0] + ChatColor.GRAY + " has never played before.");
                    }
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
