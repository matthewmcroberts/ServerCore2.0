package com.matthew.plugin.core.commands;

import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.RankUtils;
import com.matthew.plugin.core.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;

public class GiveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        try {
            if (RankUtils.isSrMod(player)) {
                    if (args.length == 3) {
                        if (!Utils.isNumeric(args[1])) {
                            Material itemType = Material.matchMaterial(args[1]);
                            if (args[0].equalsIgnoreCase("all")) {
                                if (itemType != null) {
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        giveAll(player, all, itemType, Integer.parseInt(args[2]));
                                    }
                                } else {
                                    MessageUtils.sendCustomMessage(player, ChatColor.GOLD + args[1] + ChatColor.GRAY + " does not exist.");
                                }
                            } else {
                                Player target = Bukkit.getPlayerExact(args[0]);
                                if (target != null) {
                                    Material itemType2 = Material.matchMaterial(args[1]);
                                    if (itemType2 != null) {
                                        give(player, target, itemType2, Integer.parseInt(args[2]));
                                    } else {
                                        MessageUtils.sendCustomMessage(player, ChatColor.GOLD + args[1] + ChatColor.GRAY + " does not exist.");

                                    }
                                } else {
                                    MessageUtils.playerNotFound(player);
                                }
                            }
                        } else {
                            MessageUtils.sendCustomMessage(player, ChatColor.GOLD + args[1] + ChatColor.GRAY + " does not exist.");
                        }
                    } else if(args.length == 1 && args[0].equalsIgnoreCase("usage")){
                        MessageUtils.commandUsage(player, "Give item");
                        MessageUtils.addToList(player, "/give (player) (item) (amount)");
                        MessageUtils.addToList(player, "/give all (item) (amount)");
                    } else {
                        MessageUtils.incorrectUsage(player, "/give (player) (item) (amount)");
                        MessageUtils.addToList(player, "/give all (item) (amount)");
                    }
            } else {
                MessageUtils.srModRank(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    private void giveAll(Player sender, Player all, Material item, int amount) {
        ItemStack itemStack = new ItemStack(item, amount);
        all.getInventory().addItem(itemStack);

        if (all != sender) {
            MessageUtils.sendCustomMessage(all, ChatColor.GOLD + sender.getName() + ChatColor.GRAY + " gave you " + ChatColor.GOLD + amount + " " + ChatColor.GOLD + item.toString().toLowerCase());

        } else {
            MessageUtils.sendCustomMessage(sender, "You gave " + ChatColor.GOLD + amount + " " + item.toString().toLowerCase() + ChatColor.GRAY + " to" + ChatColor.GOLD + " ALL");
        }
    }

    private void give(Player sender, Player target, Material item, int amount) {
        ItemStack itemStack2 = new ItemStack(item);
        itemStack2.setAmount(amount);
        target.getInventory().addItem(itemStack2);
        if (target != sender) {
            MessageUtils.sendCustomMessage(target, ChatColor.GOLD + sender.getName() + ChatColor.GRAY + " gave you " + ChatColor.GOLD + amount + " " + ChatColor.GOLD + item);
            MessageUtils.sendCustomMessage(sender, "You gave " + ChatColor.GOLD + amount + " " + item.toString().toLowerCase() + ChatColor.GRAY + " to" + ChatColor.GOLD + " " + target.getName());

        } else {
            MessageUtils.sendCustomMessage(sender, "You gave " + ChatColor.GOLD + amount + " " + item.toString().toLowerCase() + ChatColor.GRAY + " to" + ChatColor.GOLD + " " + target.getName());
        }
    }
}
