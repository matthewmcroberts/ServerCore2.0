package com.matthew.plugin.core.punish.apis.types;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.apis.dbquerys.ActivePunishments;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.PunishUtils;
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

public class PunishMute {

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

    public static void permMute(Player issuer, OfflinePlayer target, String reason) throws SQLException {
        if(ActivePunishments.getAmount(target) == 0) {
            insert(issuer, target, "Chat", 4, reason, new Timestamp(System.currentTimeMillis()), true);
            PunishUtils.sendPermMuteMessages(issuer, target, reason);
        } else if(ActivePunishments.getAmount(target) == 1) {
            if(ActivePunishments.getType(target).get(0).equalsIgnoreCase("Hacking") ||
                    ActivePunishments.getType(target).get(0).equalsIgnoreCase("Gameplay")) {
                insert(issuer, target, "Chat", 4, reason, new Timestamp(System.currentTimeMillis()), true);
                PunishUtils.sendPermMuteMessages(issuer, target, reason);
            } else {
                MessageUtils.sendCustomMessage(issuer, ChatColor.GOLD + target.getName() + ChatColor.GRAY + "is already muted.");
            }
        } else {
            MessageUtils.sendCustomMessage(issuer, ChatColor.GOLD + target.getName() + ChatColor.GRAY + "is already muted.");
        }
    }

    public static void tempMute(Player issuer, OfflinePlayer target, int sev, String reason, Timestamp expiration) throws SQLException {
        if(ActivePunishments.getAmount(target) == 0) {
            insert(issuer, target, "Chat", sev, reason, expiration, false);
            PunishUtils.sendTempMuteMessages(issuer, target, reason, sev, expiration);
        } else if(ActivePunishments.getAmount(target) == 1) {
            if(ActivePunishments.getType(target).get(0).equalsIgnoreCase("Hacking") ||
                    ActivePunishments.getType(target).get(0).equalsIgnoreCase("Gameplay")) {
                insert(issuer, target, "Chat", 4, reason, expiration, false);
                PunishUtils.sendTempMuteMessages(issuer, target, reason, sev, expiration);
            } else {
                MessageUtils.sendCustomMessage(issuer, ChatColor.GOLD + target.getName() + ChatColor.GRAY + "is already muted.");
            }
        } else {
            MessageUtils.sendCustomMessage(issuer, ChatColor.GOLD + target.getName() + ChatColor.GRAY + "is already muted.");
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
