package com.matthew.plugin.core.commands.adminhelp;

import com.matthew.plugin.core.ranks.apis.RankManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class AdminHelpReplyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender instanceof Player) {

            try {
                if (RankUtils.isJrMod(player)) {
                    if (args.length >= 2) {
                        Player target = Bukkit.getPlayerExact(args[0]);

                        StringBuilder message = new StringBuilder();
                        for (int i = 1; i < args.length; i++) {
                            message.append(args[i]).append(" ");
                        }
                        if (target != null) {
                            player.sendMessage(ChatColor.BOLD + ChatColor.RED.toString() + ChatColor.DARK_PURPLE + "-> "
                                    + RankManager.getRank(target).getColor() + "[" + RankManager.getRank(target).getName() + "] " + target.getName() + ChatColor.GOLD + " " + message);
                            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1F);

                            target.sendMessage(ChatColor.BOLD + ChatColor.RED.toString() + ChatColor.DARK_PURPLE + "<- "
                                    + RankManager.getRank(player).getColor() + "[" + RankManager.getRank(player).getName() + "] " + player.getName() + ChatColor.GOLD + " " + message);
                            target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 2F);
                        } else {
                            MessageUtils.playerNotFound(player);
                        }
                    } else if (args[0].equalsIgnoreCase("usage")) {
                            MessageUtils.commandUsage(player, "Admin Help Reply");
                            MessageUtils.addToList(player, "/ma (message)");
                    } else {
                        MessageUtils.incorrectUsage(player, "/ma (message)");
                    }
                } else {
                    MessageUtils.jrModRank(player);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


            //Send message to the rest of the staff members online showing what the sender (staff member) said to the target (player)
            for (Player allPlayers : Bukkit.getOnlinePlayers()) {
                try {
                    if (RankUtils.isJrMod(allPlayers)) {
                        if (args.length >= 2) {
                            StringBuilder message = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                message.append(args[i]).append(" ");
                            }

                            if (allPlayers != player && allPlayers != Bukkit.getPlayerExact(args[0])) {
                                allPlayers.sendMessage(RankManager.getRank(player).getColor() + "[" + RankManager.getRank(player).getName() + "] "
                                        + player.getName() + ChatColor.DARK_PURPLE + " -> " + RankManager.getRank(Bukkit.getPlayerExact(args[0])).getColor()
                                        + "[" + RankManager.getRank(Bukkit.getPlayerExact(args[0])) + "] " + Bukkit.getPlayerExact(args[0]).getName() + " " + ChatColor.GOLD + message);
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }
}
