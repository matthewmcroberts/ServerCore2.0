package com.matthew.plugin.core.commands.playermsg;


import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.commands.vanish.VanishManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class ReplyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        try {
            if(sender instanceof Player) {
                if (RankUtils.isMember(player)) {
                    if (args.length > 0) {
                        if(!args[0].equalsIgnoreCase("usage")) {
                            if (ServerCore.recentlyMessaged.containsKey(player)) {
                                if (ServerCore.recentlyMessaged.get(player).getPlayer().isOnline() && !VanishManager.getIfVanished(ServerCore.recentlyMessaged.get(player))) {
                                    Player target = ServerCore.recentlyMessaged.get(player);

                                    StringBuilder message = new StringBuilder();
                                    for (int i = 0; i < args.length; i++) {
                                        message.append(args[i]).append(" ");
                                    }

                                    player.sendMessage(ChatColor.BLUE + player.getName() + " > " + target.getName() + ChatColor.GRAY + " " + message.toString());
                                    target.sendMessage(ChatColor.BLUE + player.getName() + " > " + target.getName() + ChatColor.GRAY + " " + message.toString());
                                    target.playSound(target.getLocation(), Sound.ITEM_CROSSBOW_HIT, 1.0F, 1F);

                                } else {
                                    player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "The player has logged out.");
                                }
                            } else {
                                player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You have not messaged anyone recently.");
                            }
                        } else {
                            MessageUtils.commandUsage(player, "Reply");
                            MessageUtils.addToList(player, "/reply (message)");
                        }
                    } else {
                        MessageUtils.incorrectUsage(player, "/reply (message)");
                    }
                } else {
                    player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "You must have" + ChatColor.DARK_GRAY + " " + ChatColor.BOLD + "MEMBER " + ChatColor.GRAY + "rank or higher to use this command.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
