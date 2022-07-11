package com.matthew.plugin.core.commands.gamemode.command;

import com.matthew.plugin.core.commands.gamemode.api.GamemodeManager;
import com.matthew.plugin.core.ranks.apis.RankManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import net.md_5.bungee.api.ChatColor;
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
                    MessageUtils.incorrectUsage(player, "/gm");
                    player.sendMessage(ChatColor.BLUE + "    - " + ChatColor.GOLD + "/gm (player)");
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
