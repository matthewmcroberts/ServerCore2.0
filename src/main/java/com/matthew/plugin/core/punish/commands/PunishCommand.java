package com.matthew.plugin.core.punish.commands;

import com.matthew.plugin.core.punish.ui.PunishUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PunishCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;
        PunishUI.openPunishUI(player, player, "test");

        return false;
    }
}
