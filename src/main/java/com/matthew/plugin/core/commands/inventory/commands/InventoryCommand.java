package com.matthew.plugin.core.commands.inventory.commands;

import com.matthew.plugin.core.commands.inventory.apis.InventoryManager;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

                if (RankUtils.isAdmin(player)) {
                    if (args.length == 2) {
                        switch (args[0]) {
                            case "clear":
                                Player target = Bukkit.getPlayerExact(args[1]);
                                if (target != null) {
                                    InventoryManager.inventoryClear(player, target);
                                } else {
                                    MessageUtils.playerNotFound(player);
                                }

                                break;
                            case "restore":
                                Player target2 = Bukkit.getPlayerExact(args[1]);
                                if (target2 != null) {
                                    InventoryManager.inventoryRestore(player, target2);
                                } else {
                                    MessageUtils.playerNotFound(player);
                                }

                                break;
                            default:
                                MessageUtils.incorrectUsage(player, "/inventory clear");
                                player.sendMessage(ChatColor.BLUE + "    - " + ChatColor.GOLD + "/inventory clear (player)");
                                player.sendMessage(ChatColor.BLUE + "    - " + ChatColor.GOLD + "/inventory restore (player)");

                                break;
                        }

                    } else if ((args.length == 1) && args[0].equalsIgnoreCase("clear")) {
                        InventoryManager.inventoryClear(player);

                    } else if ((args.length == 1) && args[0].equalsIgnoreCase("usage")) {
                        MessageUtils.commandUsage(player, "Inventory");
                        MessageUtils.addToList(player, "/inventory clear (player)");
                        MessageUtils.addToList(player, "/inventory restore (player)");
                        MessageUtils.addToList(player, "/inventory clear");

                    } else {
                        MessageUtils.incorrectUsage(player, "/inventory clear");
                        MessageUtils.addToList(player, "/inventory clear (player)");
                        MessageUtils.addToList(player, "/inventory restore (player)");
                        MessageUtils.addToList(player, "/inventory clear");
                    }
                } else {
                    MessageUtils.adminRank(player);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
