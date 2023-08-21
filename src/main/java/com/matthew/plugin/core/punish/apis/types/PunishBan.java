package com.matthew.plugin.core.punish.apis.types;

import com.matthew.plugin.core.utils.MessageUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Calendar;

public class PunishBan {

    // ID INTEGER(PRIMARY)(AI)
    // uuid VARCHAR
    // type VARCHAR
    // sev INTEGER
    // reason TINYTEXT
    // issued TIMESTAMP
    // expiration TIMESTAMP
    // staff VARCHAR
    // active BOOLEAN
    // permanent BOOLEAN

    public static void permBan(Player issuer, OfflinePlayer target, String type, String reason, boolean permanent) {

    }

    public static void tempBan(Player sender, OfflinePlayer player, Calendar time, String reason) {

    }

}
