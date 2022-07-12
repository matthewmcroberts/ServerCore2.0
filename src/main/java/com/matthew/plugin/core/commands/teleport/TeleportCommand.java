package com.matthew.plugin.core.commands.teleport;

import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import com.matthew.plugin.core.utils.TeleportUtils;
import org.bukkit.Bukkit;
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
            if(RankUtils.isMod(player)) {
                if(!(args.length > 2)) {
                    if(args.length == 1) {
                        switch(args[0]) {
                            case "all":
                                TeleportUtils.tpAll(player);
                                break;
                            default:
                                TeleportUtils.tp(player, Bukkit.getPlayerExact(args[0]));
                                break;
                        }
                    } else if(args.length == 2) {
                        switch(args[0]) {
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
                MessageUtils.modRank(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
