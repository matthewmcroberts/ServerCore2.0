package com.matthew.plugin.core.commands.playermsg.commands;

import com.avaje.ebeaninternal.server.core.Message;
import com.matthew.plugin.core.commands.playermsg.MessageManager;
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

public class MessageCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        try {
            if (RankUtils.isMember(player)) {
                if (args.length >= 2) {
                    if (Bukkit.getPlayerExact(args[0]) != null) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                       // if (Main.getVanishManager().getVanishedState(target) != true) {
                            //if (!main.getMessageManager().pmToggle.contains(target.getUniqueId())) {
                                StringBuilder message = new StringBuilder();
                                for (int i = 1; i < args.length; i++) {
                                    message.append(args[i]).append(" ");
                                }

                                player.sendMessage(ChatColor.BLUE + player.getName() + " > " +
                                        target.getName() + ChatColor.GRAY + " " + message);
                                target.sendMessage(ChatColor.BLUE + player.getName() + " > " +
                                        target.getName() + ChatColor.GRAY + " " + message);
                                target.playSound(target.getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, 1F);

                                MessageManager.getRecentlyMessaged().put(player, target);
                           // } else {
                            //    player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GOLD + target.getName() + ChatColor.GOLD + "'s" +ChatColor.GRAY + " messages are disabled.");

                          //  }
                        //} else {
                        //    player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Player not found.");
                      //  }
                    } else {
                        MessageUtils.playerNotFound(player);
                    }
                } else {
                    MessageUtils.incorrectUsage(player, "/message (player) (message)");
                }
            } else {
                player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You must have" + ChatColor.DARK_GRAY + " " +
                        ChatColor.BOLD + "MEMBER " + ChatColor.GRAY + "rank or higher to use this command.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
