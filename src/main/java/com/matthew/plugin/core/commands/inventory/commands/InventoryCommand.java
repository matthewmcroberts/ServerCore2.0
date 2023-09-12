package com.matthew.plugin.core.commands.inventory.commands;

import com.matthew.plugin.core.commands.inventory.apis.InventoryManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class InventoryCommand implements CommandExecutor {

    //todo add an inventory copy command

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        try {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                if (RankUtils.isMod(player)) {
                    if (args.length == 2) {
                        Player target = Bukkit.getPlayerExact(args[1]);
                        if (target != null) {
                            switch (args[0]) {
                                case "clear":
                                    InventoryManager.inventoryClear(player, target);
                                    break;
                                case "restore":
                                    InventoryManager.inventoryRestore(player, target);
                                    break;
                                case "copy":
                                    InventoryManager.inventoryCopy(player, target);
                                    break;
                                default:
                                    MessageUtils.incorrectUsage(player, "/inventory clear");
                                    MessageUtils.addToList(player, "/inventory clear (player)");
                                    MessageUtils.addToList(player, "/inventory restore (player)");
                                    MessageUtils.addToList(player, "/inventory copy (player)");
                            }

                        } else {
                            MessageUtils.playerNotFound(player);
                        }
                    } else if ((args.length == 1) && args[0].equalsIgnoreCase("clear")) {
                        InventoryManager.inventoryClear(player);

                    } else if ((args.length == 1) && args[0].equalsIgnoreCase("usage")) {
                        MessageUtils.commandUsage(player, "Inventory");
                        MessageUtils.addToList(player, "/inventory clear");
                        MessageUtils.addToList(player, "/inventory clear (player)");
                        MessageUtils.addToList(player, "/inventory restore (player)");
                        MessageUtils.addToList(player, "/inventory copy (player)");

                    } else {
                        MessageUtils.incorrectUsage(player, "/inventory clear");
                        MessageUtils.addToList(player, "/inventory clear (player)");
                        MessageUtils.addToList(player, "/inventory restore (player)");
                        MessageUtils.addToList(player, "/inventory copy (player)");

                    }
                } else {
                    MessageUtils.modRank(player);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
