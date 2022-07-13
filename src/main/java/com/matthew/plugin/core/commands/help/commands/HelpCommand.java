package com.matthew.plugin.core.commands.help.commands;

import com.matthew.plugin.core.commands.help.utils.HelpUtils;
import com.matthew.plugin.core.ranks.apis.RankManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;


public class HelpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {

                try {
                    switch (RankManager.getRank(player).getName()) {
                        case "OWNER":
                        case "DEV":
                        case "ADMIN":
                        case "QAT":
                            HelpUtils.adminHelp(player);
                            break;

                        case "SRMOD":
                            HelpUtils.srModHelp(player);
                            break;

                        case "MOD":
                            HelpUtils.modHelp(player);
                            break;

                        case "JRMOD":
                            HelpUtils.jrModHelp(player);
                            break;

                        default:
                            HelpUtils.nonStaffHelp(player);
                            break;

                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;

    }
}
