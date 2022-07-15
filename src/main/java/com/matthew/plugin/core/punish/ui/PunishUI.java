package com.matthew.plugin.core.punish.ui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PunishUI {

    public static void openPunishUI(Player player, Player target) {

        Inventory ui = Bukkit.createInventory(player, 54, ChatColor.GOLD + "Punish - " + target.getName());

        // Chat Offense Category

        ArrayList<String> muteCatLore = new ArrayList<>();
        muteCatLore.add(ChatColor.GRAY + "Use this category if a player has committed a Chat Offense");
        ItemStack muteCategory = new ItemStack(Material.BOOK);
        ItemMeta muteCategoryMeta = muteCategory.getItemMeta();
        muteCategoryMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Chat Offense");
        muteCategoryMeta.setLore(muteCatLore);
        muteCategory.setItemMeta(muteCategoryMeta);
        ui.setItem(11, muteCategory);

        ArrayList<String> muteSev1Lore = new ArrayList<>();
        muteSev1Lore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "0");
        ItemStack muteSev1 = new ItemStack(Material.WOOL, 1, (byte) 5);
        ItemMeta muteSev1Meta = muteSev1.getItemMeta();
        muteSev1Meta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Severity 1");
        muteSev1Meta.setLore(muteSev1Lore);
        muteSev1.setItemMeta(muteSev1Meta);
        ui.setItem(20, muteSev1);

        ArrayList<String> muteSev2Lore = new ArrayList<>();
        muteSev2Lore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "0");
        ItemStack muteSev2 = new ItemStack(Material.WOOL, 1, (byte) 4);
        ItemMeta muteSev2Meta = muteSev2.getItemMeta();
        muteSev2Meta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Severity 2");
        muteSev2Meta.setLore(muteSev2Lore);
        muteSev2.setItemMeta(muteSev2Meta);
        ui.setItem(29, muteSev2);

        ArrayList<String> muteSev3Lore = new ArrayList<>();
        muteSev3Lore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "0");
        ItemStack muteSev3 = new ItemStack(Material.WOOL, 1, (byte) 14);
        ItemMeta muteSev3Meta = muteSev3.getItemMeta();
        muteSev3Meta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Severity 3");
        muteSev3Meta.setLore(muteSev3Lore);
        muteSev3.setItemMeta(muteSev3Meta);
        ui.setItem(38, muteSev3);


        // Gameplay Offense Category

        ArrayList<String> gameCatLore = new ArrayList<>();
        gameCatLore.add(ChatColor.GRAY + "Use this category if a player has committed a Gameplay Offense");
        ItemStack gameCategory = new ItemStack(Material.ANVIL);
        ItemMeta gameCategoryMeta = gameCategory.getItemMeta();
        gameCategoryMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Gameplay Offense");
        gameCategoryMeta.setLore(gameCatLore);
        gameCategory.setItemMeta(gameCategoryMeta);
        ui.setItem(13, gameCategory);

        ArrayList<String> gameSev1Lore = new ArrayList<>();
        gameSev1Lore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "1 hour");
        ItemStack gameSev1 = new ItemStack(Material.WOOL, 1, (byte) 5);
        ItemMeta gameSev1Meta = gameSev1.getItemMeta();
        gameSev1Meta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Severity 1");
        gameSev1Meta.setLore(gameSev1Lore);
        gameSev1.setItemMeta(gameSev1Meta);
        ui.setItem(22, gameSev1);

        ArrayList<String> gameSev2Lore = new ArrayList<>();
        gameSev2Lore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "12 hours");
        ItemStack gameSev2 = new ItemStack(Material.WOOL, 1, (byte) 4);
        ItemMeta gameSev2Meta = gameSev2.getItemMeta();
        gameSev2Meta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Severity 2");
        gameSev2Meta.setLore(gameSev2Lore);
        gameSev2.setItemMeta(gameSev2Meta);
        ui.setItem(31, gameSev2);

        ArrayList<String> gameSev3Lore = new ArrayList<>();
        gameSev3Lore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "1 day");
        ItemStack gameSev3 = new ItemStack(Material.WOOL, 1, (byte) 14);
        ItemMeta gameSev3Meta = gameSev3.getItemMeta();
        gameSev3Meta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Severity 3");
        gameSev3Meta.setLore(gameSev3Lore);
        gameSev3.setItemMeta(gameSev3Meta);
        ui.setItem(40, gameSev3);


        // Hacking Offense Category

        ArrayList<String> hackCatLore = new ArrayList<>();
        hackCatLore.add(ChatColor.GRAY + "Use this category if a player has committed a Hacking Offense");
        ItemStack hackCategory = new ItemStack(Material.IRON_SWORD);
        ItemMeta hackCategoryMeta = hackCategory.getItemMeta();
        hackCategoryMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        hackCategoryMeta.setDisplayName(ChatColor.GOLD.toString() + ChatColor.BOLD + "Hacking Offense");
        hackCategoryMeta.setLore(hackCatLore);
        hackCategory.setItemMeta(hackCategoryMeta);
        ui.setItem(15, hackCategory);

        ArrayList<String> hackSev1Lore = new ArrayList<>();
        hackSev1Lore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "12 hours");
        ItemStack hackSev1 = new ItemStack(Material.WOOL, 1, (byte) 5);
        ItemMeta hackSev1Meta = hackSev1.getItemMeta();
        hackSev1Meta.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Severity 1");
        hackSev1Meta.setLore(hackSev1Lore);
        hackSev1.setItemMeta(hackSev1Meta);
        ui.setItem(24, hackSev1);

        ArrayList<String> hackSev2Lore = new ArrayList<>();
        hackSev2Lore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "7 days");
        ItemStack hackSev2 = new ItemStack(Material.WOOL, 1, (byte) 4);
        ItemMeta hackSev2Meta = hackSev2.getItemMeta();
        hackSev2Meta.setDisplayName(ChatColor.YELLOW.toString() + ChatColor.BOLD + "Severity 2");
        hackSev2Meta.setLore(hackSev2Lore);
        hackSev2.setItemMeta(hackSev2Meta);
        ui.setItem(33, hackSev2);

        ArrayList<String> hackSev3Lore = new ArrayList<>();
        hackSev3Lore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "1 month");
        ItemStack hackSev3 = new ItemStack(Material.WOOL, 1, (byte) 14);
        ItemMeta hackSev3Meta = hackSev3.getItemMeta();
        hackSev3Meta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Severity 3");
        hackSev3Meta.setLore(hackSev3Lore);
        hackSev3.setItemMeta(hackSev3Meta);
        ui.setItem(42, hackSev3);

        // Perm Mute
        ArrayList<String> permMuteLore = new ArrayList<>();
        permMuteLore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "Permanent");
        ItemStack permMute = new ItemStack(Material.BOOK_AND_QUILL);
        ItemMeta permMuteMeta = permMute.getItemMeta();
        permMuteMeta.setDisplayName(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Permanent Mute");
        permMuteMeta.setLore(permMuteLore);
        permMute.setItemMeta(permMuteMeta);
        ui.setItem(27, permMute);

        // Perm Ban
        ArrayList<String> permBanLore = new ArrayList<>();
        permBanLore.add(ChatColor.GRAY + "Duration: " + ChatColor.YELLOW + "Permanent");
        ItemStack permBan = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta permBanMeta = permBan.getItemMeta();
        permBanMeta.setDisplayName(ChatColor.DARK_RED.toString() + ChatColor.BOLD + "Permanent Ban");
        permBanMeta.setLore(permBanLore);
        permBan.setItemMeta(permBanMeta);
        ui.setItem(36, permBan);

        // End of GUI build

        player.openInventory(ui);

    }
}
