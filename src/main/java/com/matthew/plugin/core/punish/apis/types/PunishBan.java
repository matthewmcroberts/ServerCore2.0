package com.matthew.plugin.core.punish.apis.types;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.apis.dbquerys.ActivePunishments;
import com.matthew.plugin.core.utils.MessageUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
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

    public static void permBan(Player issuer, OfflinePlayer target, String type, String reason, boolean permanent) throws SQLException {
        if(ActivePunishments.getAmount(target) == 0) {
            insert(issuer, target, type, 4, reason, new Timestamp(System.currentTimeMillis()), permanent);
        } else if(ActivePunishments.getAmount(target) == 1) {
            if(ActivePunishments.getType(target).get(0).equals("Chat")) {
                insert(issuer, target, type, 4, reason, new Timestamp(System.currentTimeMillis()), permanent);
            } else {
                MessageUtils.sendCustomMessage(issuer, ChatColor.GOLD + target.getName() + ChatColor.GRAY + "is already banned.");
            }
        } else {
            MessageUtils.sendCustomMessage(issuer, ChatColor.GOLD + target.getName() + ChatColor.GRAY + "is already banned.");
        }
    }

    public static void tempBan(Player issuer, OfflinePlayer target, String type, String reason, Timestamp expiration, boolean permanent) throws SQLException {
        if(ActivePunishments.getAmount(target) == 0) {
            insert(issuer, target, type, 4, reason, expiration, permanent);
        } else if(ActivePunishments.getAmount(target) == 1) {
            if(ActivePunishments.getType(target).get(0).equals("Chat")) {
                insert(issuer, target, type, 4, reason, expiration, permanent);
            } else {
                MessageUtils.sendCustomMessage(issuer, ChatColor.GOLD + target.getName() + ChatColor.GRAY + "is already banned.");
            }
        } else {
            MessageUtils.sendCustomMessage(issuer, ChatColor.GOLD + target.getName() + ChatColor.GRAY + "is already banned.");
        }
    }

    private static void insert(Player issuer, OfflinePlayer target, String type, int sev, String reason, Timestamp expiration, boolean permanent) throws SQLException {
        PreparedStatement ps = ServerCore.preparedStatement("INSERT INTO active_punishments(ID, UUID, TYPE, SEV, REASON, ISSUED, EXPIRATION, STAFF, ACTIVE, PERMANENT) VALUES(" +
                "DEFAULT, ?, ?, ?, ?, DEFAULT, ?, ?, DEFAULT, ?);");
        ps.setString(1, target.getUniqueId().toString());
        ps.setString(2, type);
        ps.setInt(3, sev);
        ps.setString(4, reason);
        ps.setTimestamp(5, expiration);
        ps.setString(6, issuer.getUniqueId().toString());
        ps.setBoolean(7, permanent);

        ps.execute();

    }

}
