package com.matthew.plugin.core.commands.vanish.commands;

import com.matthew.plugin.core.commands.vanish.VanishManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
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
            if (sender instanceof Player) {
                if (RankUtils.isJrMod(player)) {
                    if (args.length == 0) {
                        if (VanishManager.getIfVanished(player)) {
                            VanishManager.setVanished(player, false);
                            VanishManager.showToPlayers(player);
                        } else {
                            VanishManager.hideFromPlayers(player);
                        }
                    } else if (args.length == 1 && args[0].equalsIgnoreCase("usage")) {
                        MessageUtils.commandUsage(player, "Vanish");
                        MessageUtils.addToList(player, "/vanish");
                    } else {
                        MessageUtils.incorrectUsage(player, "/vanish");
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
