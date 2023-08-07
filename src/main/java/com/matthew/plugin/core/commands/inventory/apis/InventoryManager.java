package com.matthew.plugin.core.commands.inventory.apis;

import com.matthew.plugin.core.utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class InventoryManager {

    private static HashMap<Player, ItemStack[]> playerInv = new HashMap<>();
    private static HashMap<Player, ItemStack[]> playerArmor = new HashMap<>();


    public static ItemStack[] getInv(Player player) {
        return playerInv.get(player);
    }

    public static void setInv(Player player) {
        playerInv.put(player, player.getInventory().getContents());
    }

    public static ItemStack[] getArmor(Player player) {
        return playerArmor.get(player);
    }

    public static void setArmor(Player player) {
        playerArmor.put(player, player.getInventory().getArmorContents());
    }

    public static HashMap<Player, ItemStack[]> getPlayerInv() {
        return playerInv;
    }

    public static HashMap<Player, ItemStack[]> getPlayerArmor() {
        return playerArmor;
    }

    public static void inventoryClear(Player player) {
        Inventory targetInv = player.getInventory();
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        for (int inv = 0; inv < targetInv.getSize(); inv++) {
            if (targetInv.getItem(inv) != null) {
                targetInv.setItem(inv, new ItemStack(Material.AIR));

            }
        }
        MessageUtils.sendCustomMessage(player, "Your inventory has been" + ChatColor.GOLD + " cleared.");
    }

    public static void inventoryClear(Player player, Player target) {

        Inventory targetInv = target.getInventory();
        target.getInventory().setChestplate(new ItemStack(Material.AIR));
        target.getInventory().setLeggings(new ItemStack(Material.AIR));
        target.getInventory().setBoots(new ItemStack(Material.AIR));
        target.getInventory().setHelmet(new ItemStack(Material.AIR));

        for (int inv = 0; inv < targetInv.getSize(); inv++) {
            if (targetInv.getItem(inv) != null) {
                targetInv.setItem(inv, new ItemStack(Material.AIR));
            }
        }

        if(target != player) {
            MessageUtils.sendCustomMessage(player, ChatColor.GOLD + target.getName() + ChatColor.GOLD + "'s " + ChatColor.GRAY + "inventory has been" + ChatColor.GOLD + " cleared.");
            MessageUtils.sendCustomMessage(target, "Your inventory has been" + ChatColor.GOLD + " cleared.");
        } else {
            MessageUtils.sendCustomMessage(player, ChatColor.GOLD + target.getName() + ChatColor.GOLD + "'s " + ChatColor.GRAY + "inventory has been" + ChatColor.GOLD + " cleared.");
        }
    }

    public static void inventoryRestore(Player player, Player target) {
        Inventory targetInv = target.getInventory();
        if (getPlayerInv().containsKey(target)) {
            targetInv.setContents(getInv(target));
            target.getInventory().setArmorContents(getArmor(target));
            if(target != player) {
                MessageUtils.sendCustomMessage(player, ChatColor.GOLD + target.getName() + ChatColor.GOLD + "'s" + ChatColor.GRAY + " inventory has been " + ChatColor.GOLD + "restored.");
                MessageUtils.sendCustomMessage(target, ChatColor.GRAY + "Your inventory has been successfully " + ChatColor.GOLD + "restored.");
            } else {
                MessageUtils.sendCustomMessage(player, ChatColor.GOLD + target.getName() + ChatColor.GOLD + "'s" + ChatColor.GRAY + " inventory has been " + ChatColor.GOLD + "restored.");
            }
            getPlayerInv().remove(target);
            getPlayerArmor().remove(target);
        } else {
            MessageUtils.sendCustomMessage(player, ChatColor.GOLD + target.getName() + ChatColor.GOLD + ChatColor.GRAY + " has not been killed.");
        }
    }

    public static void inventoryCopy(Player player, Player target) {
        Inventory playerInv = player.getInventory();
        if(target != player) {
            if (target.getPlayer() != null) {
                Inventory targetInv = target.getInventory();

                //Clear Player's Inventory//
                player.getInventory().setChestplate(new ItemStack(Material.AIR));
                player.getInventory().setLeggings(new ItemStack(Material.AIR));
                player.getInventory().setBoots(new ItemStack(Material.AIR));
                player.getInventory().setHelmet(new ItemStack(Material.AIR));

                for (int inv = 0; inv < playerInv.getSize(); inv++) {
                    if (playerInv.getItem(inv) != null) {
                        playerInv.setItem(inv, new ItemStack(Material.AIR));
                    }
                }
                /////////

                playerInv.setContents(targetInv.getContents());
                player.getInventory().setArmorContents(target.getInventory().getArmorContents());
                MessageUtils.sendCustomMessage(player, ChatColor.GRAY + "Successfully copied " + ChatColor.GOLD + target.getName() + "'s" + ChatColor.GRAY + " inventory.");

            } else {
                MessageUtils.playerNotFound(player);
            }
        } else {
            MessageUtils.sendCustomMessage(player, "You cannot copy your own inventory.");
        }
    }
}
