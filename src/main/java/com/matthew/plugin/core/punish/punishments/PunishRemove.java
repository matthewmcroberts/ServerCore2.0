package com.matthew.plugin.core.punish.punishments;

import com.matthew.plugin.core.utils.MessageUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PunishRemove {

    public static void removePunishment(Player sender, OfflinePlayer t) {

        if(Bukkit.getBanList(BanList.Type.NAME).isBanned(t.getName())) {
            Bukkit.getBanList(BanList.Type.NAME).pardon(t.getName());
            MessageUtils.sendCustomMessage(sender,"Successfully removed " + ChatColor.YELLOW + t.getName() + "'s" + ChatColor.GRAY + " punishment.");

        } else {
            sender.sendMessage(ChatColor.BLUE + "> " + ChatColor.GRAY + "Player is not currently punished");
        }
    }

}
