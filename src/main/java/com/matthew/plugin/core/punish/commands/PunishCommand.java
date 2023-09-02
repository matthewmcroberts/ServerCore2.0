package com.matthew.plugin.core.punish.commands;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.Punishments;
import com.matthew.plugin.core.punish.apis.types.PunishBan;
import com.matthew.plugin.core.punish.apis.types.PunishRemove;
import com.matthew.plugin.core.punish.ui.PunishUI;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
                            Bukkit.getOfflinePlayer(args[0]);
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                            StringBuilder reason = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                reason.append(args[i]).append(" ");
                            }
                            ServerCore.punishReason.put(player, reason.toString());
                            PunishUI.openPunishUI(player, target);
                        } else if(args.length >= 3){
                            Bukkit.getOfflinePlayer(args[1]);
                            if(args[2].equalsIgnoreCase(Punishments.CHAT.getName()) || args[2].equalsIgnoreCase(Punishments.HACKING.getName()) || args[2].equalsIgnoreCase(Punishments.GAMEPLAY.getName())) {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                String type = args[2];
                                PunishRemove.removePunishment(player, target, Punishments.valueOf(type.toUpperCase()));
                            } else {
                                MessageUtils.sendCustomMessage(player, "Invalid type. Valid punishment types are:");
                                MessageUtils.addToList(player, "Hacking, Chat, Gameplay");
                            }
                        } else {
                            incorrectUsage(player);
                        }
                    } else if (args.length == 1 && args[0].equalsIgnoreCase("usage")) {
                        MessageUtils.commandUsage(player, "Punish");
                        MessageUtils.addToList(player, "/punish (player) (reason)");
                        MessageUtils.addToList(player, "/punish remove (player) (punishment_type)");
                        player.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + "Types:" + ChatColor.YELLOW + " Hacking, Chat, Gameplay");
                    } else {
                        incorrectUsage(player);
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

    private void incorrectUsage(Player player) {
        MessageUtils.incorrectUsage(player, "/punish (player) (reason)");
        MessageUtils.addToList(player, "/punish remove (player) (punishment_type)");
        player.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + "Types:" + ChatColor.YELLOW + " Hacking, Chat, Gameplay");
    }
}
