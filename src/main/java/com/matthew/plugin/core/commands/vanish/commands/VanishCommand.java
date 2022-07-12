package com.matthew.plugin.core.commands.vanish.commands;

import com.matthew.plugin.core.commands.vanish.VanishManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        try {
            if (RankUtils.isJrMod(player)) {
                if (args.length == 0) {
                    if (VanishManager.getIfVanished(player) == true) {
                        VanishManager.setVanished(player, false);
                        VanishManager.showToPlayers(player);
                    } else {
                        VanishManager.hideFromPlayers(player);
                    }
                } else {
                    MessageUtils.incorrectUsage(player, "/vanish");
                }

            } else {
                MessageUtils.jrModRank(player);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}