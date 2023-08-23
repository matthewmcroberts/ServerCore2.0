package com.matthew.plugin.core.punish.apis.types;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.apis.dbquerys.ActivePunishments;
import com.matthew.plugin.core.utils.MessageUtils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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


        } else if(ActivePunishments.getAmount(target) == 1) {
            //check if punishment is chat
            //issue ban
            //if ban, deny ability to ban
        } else {
            //say player is already banned
        }
    }

    public static void tempBan(Player sender, OfflinePlayer player, Calendar time, String reason) {

    }

    private static void insert(Player issuer, OfflinePlayer target, String type, String reason, boolean permanent) throws SQLException {
        PreparedStatement ps = ServerCore.preparedStatement("INSERT INTO active_punishments(ID, UUID, TYPE, SEV, REASON, ISSUED, EXPIRATION, STAFF, ACTIVE, PERMANENT) VALUES(" +
                "DEFAULT, ?, ?, ?, ?, DEFAULT, ?, ?, DEFAULT, 1);");
        ps.setString(1, target.getUniqueId().toString());
        ps.setString(2, type);
        ps.setString(3, reason);
        ps.setString(4, "DEFAULT");
    }

}
