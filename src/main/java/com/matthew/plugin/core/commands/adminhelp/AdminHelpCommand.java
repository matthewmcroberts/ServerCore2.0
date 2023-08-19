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

public class AdminHelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender instanceof Player) {
            if (args.length >= 1) {
                if(!args[0].equalsIgnoreCase("usage")) {
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        try {
                            StringBuilder message = new StringBuilder();
                            for (int i = 0; i < args.length; i++) {
                                message.append(args[i]).append(" ");
                            }

                            if (target.equals(player)) {
                                player.sendMessage(RankManager.getRank(player).getColor() + "[" + RankManager.getRank(player).getName() + "] " + player.getName() + " " + ChatColor.GOLD + message);
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1F);
                            } else if (RankUtils.isJrMod(target)) {
                                target.sendMessage(RankManager.getRank(player).getColor() + "[" + RankManager.getRank(player).getName() + "] " + player.getName() + " " + ChatColor.GOLD + message);
                                target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1F);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    MessageUtils.commandUsage(player, "Admin Help");
                    MessageUtils.addToList(player, "/a (message)");
                }
            } else {
                MessageUtils.incorrectUsage(player, "/a (message)");
            }
        }
        return false;
    }
}
