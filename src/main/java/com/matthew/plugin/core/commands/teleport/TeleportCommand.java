package com.matthew.plugin.core.commands.teleport;

import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import com.matthew.plugin.core.utils.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        try {
            if(sender instanceof Player) {
                if (RankUtils.isJrMod(player)) {
                    if(!args[0].equalsIgnoreCase("usage")) {
                        if (!(args.length > 2)) {
                            if (args.length == 1) {
                                switch (args[0]) {
                                    case "all":
                                        TeleportUtils.tpAll(player);
                                        break;
                                    default:
                                        TeleportUtils.tp(player, Bukkit.getPlayerExact(args[0]));
                                        break;
                                }
                            } else if (args.length == 2) {
                                switch (args[0]) {
                                    case "here":
                                        TeleportUtils.tpHere(player, Bukkit.getPlayerExact(args[1]));
                                        break;
                                    default:
                                        TeleportUtils.tpTarget(player, Bukkit.getPlayerExact(args[0]), Bukkit.getPlayerExact(args[1]));
                                        break;
                                }
                            } else {
                                TeleportUtils.incorrectUsage(player);
                            }
                        } else {
                            TeleportUtils.incorrectUsage(player);
                        }
                    } else {
                        MessageUtils.commandUsage(player, "Teleport");
                        MessageUtils.addToList(player, "/tp (player)");
                        MessageUtils.addToList(player, ChatColor.DARK_RED + "/tp here (player)");
                        MessageUtils.addToList(player, ChatColor.DARK_RED + "/tp (player) (target)");
                        MessageUtils.addToList(player, ChatColor.DARK_RED + "/tp all");
                    }
                } else {
                    MessageUtils.jrModRank(player);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
