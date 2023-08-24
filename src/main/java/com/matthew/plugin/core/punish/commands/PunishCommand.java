package com.matthew.plugin.core.punish.commands;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.apis.types.PunishRemove;
import com.matthew.plugin.core.punish.ui.PunishUI;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class PunishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            try {
                if (RankUtils.isMod(player)) {
                    if (args.length >= 2) {
                        if (!args[0].equalsIgnoreCase("remove")) {
                            if (Bukkit.getOfflinePlayer(args[0]) != null) {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                                StringBuilder reason = new StringBuilder();
                                for (int i = 1; i < args.length; i++) {
                                    reason.append(args[i]).append(" ");
                                }
                                ServerCore.punishReason.put(player, reason.toString());
                                PunishUI.openPunishUI(player, target);
                            } else {
                                MessageUtils.playerNotFound(player);
                            }
                        } else {
                                if (Bukkit.getOfflinePlayer(args[1]) != null) {
                                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                    PunishRemove.removePunishment(player, target);
                                } else {
                                    MessageUtils.playerNotFound(player);
                                }
                        }
                    } else if (args.length == 1 && args[0].equalsIgnoreCase("usage")) {
                        MessageUtils.commandUsage(player, "Punish");
                        MessageUtils.addToList(player, "/punish (player) (reason)");
                        MessageUtils.addToList(player, "/punish remove (player)");
                    } else {
                        MessageUtils.incorrectUsage(player, "/punish (player) (reason)");
                        MessageUtils.addToList(player, "/punish remove (player)");
                    }
                } else {
                    MessageUtils.modRank(player);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
