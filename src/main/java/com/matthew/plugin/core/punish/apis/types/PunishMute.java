package com.matthew.plugin.core.punish.apis.types;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.Punishments;
import com.matthew.plugin.core.punish.apis.dbquerys.ActivePunishments;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.PunishUtils;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

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
            insert(issuer, target, 4, reason, new Timestamp(System.currentTimeMillis()), true);
            PunishUtils.sendPermMuteMessages(issuer, target, reason);
            ServerCore.punishReason.remove(issuer);
        } else if(ActivePunishments.getAmount(target) == 1) {
            if(ActivePunishments.getBanType(target) != null) { //meaning the one active punishment is a ban, proceed
                insert(issuer, target, 4, reason, new Timestamp(System.currentTimeMillis()), true);
                PunishUtils.sendPermMuteMessages(issuer, target, reason);
                ServerCore.punishReason.remove(issuer);
            } else {
                MessageUtils.sendCustomMessage(issuer, ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " is already muted.");
            }
        } else {
            MessageUtils.sendCustomMessage(issuer, ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " is already muted.");
        }
    }

    public static void tempMute(Player issuer, OfflinePlayer target, int sev, String reason, Timestamp expiration) throws SQLException {
        if(ActivePunishments.getAmount(target) == 0) {
            insert(issuer, target, sev, reason, expiration, false);
            PunishUtils.sendTempMuteMessages(issuer, target, reason, sev, expiration);
            ServerCore.punishReason.remove(issuer);
        } else if(ActivePunishments.getAmount(target) == 1) {
            if(ActivePunishments.getBanType(target) != null) {
                insert(issuer, target, 4, reason, expiration, false);
                PunishUtils.sendTempMuteMessages(issuer, target, reason, sev, expiration);
                ServerCore.punishReason.remove(issuer);
            } else {
                MessageUtils.sendCustomMessage(issuer, ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " is already muted.");
            }
        } else {
            MessageUtils.sendCustomMessage(issuer, ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " is already muted.");
        }
    }

    private static void insert(Player issuer, OfflinePlayer target, int sev, String reason, Timestamp expiration, boolean permanent) throws SQLException {
        PreparedStatement ps = ServerCore.preparedStatement("INSERT INTO active_punishments(ID, UUID, TYPE, SEV, REASON, ISSUED, EXPIRATION, STAFF, ACTIVE, PERMANENT) VALUES(" +
                "DEFAULT, ?, ?, ?, ?, DEFAULT, ?, ?, DEFAULT, ?);");
        ps.setString(1, target.getUniqueId().toString());
        ps.setString(2, Punishments.CHAT.getName());
        ps.setInt(3, sev);
        ps.setString(4, reason);
        ps.setTimestamp(5, expiration);
        ps.setString(6, issuer.getUniqueId().toString());
        ps.setBoolean(7, permanent);

        ps.execute();

    }

}
