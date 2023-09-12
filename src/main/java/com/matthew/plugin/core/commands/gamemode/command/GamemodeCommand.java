package com.matthew.plugin.core.commands.gamemode.command;

import com.matthew.plugin.core.commands.gamemode.api.GamemodeManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class GamemodeCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;
        try {
            if (RankUtils.isAdmin(player)) {
                if (args.length == 0) {
                    if (sender instanceof Player)
                        if (player.getGameMode() == GameMode.CREATIVE) {
                            GamemodeManager.removeGamemode(player);
                        } else if (player.getGameMode() == GameMode.SURVIVAL) {
                            GamemodeManager.giveGamemode(player);
                        }
                } else if (args.length == 1) {
                    if(!args[0].equalsIgnoreCase("usage")) {
                        Player target = Bukkit.getPlayerExact(args[0]);
                        if (target != null) {
                            if (target.getGameMode() == GameMode.CREATIVE) {
                                GamemodeManager.removeGamemode(player, target);
                            } else {
                                GamemodeManager.giveGamemode(player, target);
                            }
                        } else {
                            MessageUtils.playerNotFound(player);
                        }
                    } else {
                        MessageUtils.commandUsage(player, "GameMode");
                        MessageUtils.addToList(player, "/gm (player)");
                    }
                } else {
                    MessageUtils.incorrectUsage(player, "/gm");
                    MessageUtils.addToList(player, "/gm (player)");
                }
            } else {
                MessageUtils.adminRank(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
