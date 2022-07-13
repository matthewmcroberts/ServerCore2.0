package com.matthew.plugin.core.commands.silence.commands;

import com.matthew.plugin.core.commands.silence.SilenceManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class SilenceCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        try {
            if(sender instanceof Player) {
                if (RankUtils.isAdmin(player)) {
                    if (args.length == 0) {
                        if (SilenceManager.getSilenceManager().contains(player)) {
                            for (Player target : Bukkit.getOnlinePlayers()) {
                                SilenceManager.getSilenceManager().remove(target);
                                MessageUtils.sendCustomMessage(target, "Chat is no longer silenced.");
                            }
                        } else {
                            for (Player target : Bukkit.getOnlinePlayers()) {
                                SilenceManager.getSilenceManager().add(target);
                                MessageUtils.sendCustomMessage(target, "Chat has been " + ChatColor.RED + "silenced.");
                            }
                        }
                    } else {
                        MessageUtils.incorrectUsage(player, "/silence");
                    }
                } else {
                    MessageUtils.adminRank(player);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
