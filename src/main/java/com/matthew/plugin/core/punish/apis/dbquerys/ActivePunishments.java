package com.matthew.plugin.core.punish.apis.dbquerys;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

public class ActivePunishments {

    // uuid VARCHAR(PRIMARY)
    // type VARCHAR
    // sev INTEGER
    // reason TINYTEXT
    // issued TIMESTAMP
    // expires TIMESTAMP
    // staff VARCHAR
    // active BOOLEAN

    public static boolean hasActivePunishment(Player player) throws SQLException {
        if (exists(player)) {
            PreparedStatement ps = ServerCore.preparedStatement("SELECT * FROM active_punishments WHERE UUID = ? AND ACTIVE = 1;");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1) != 0; //if a record is found return true

        }
        return false;
    }

    /**
     * Get the time remaining on a player's punishment
     *
     * @param player - Player whose UUID is being queried
     * @return 1D array of times as type long
     * - long[]{Years, Days, Hours, Minutes, Seconds}
     */
    public static long[] getTimeRemaining(Player player) throws SQLException {
        if (exists(player)) {

            //Convert issued date to string
            PreparedStatement ps = ServerCore.preparedStatement("SELECT ISSUED FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();

            Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("ISSUED").toLocalDateTime());
            String issued = timestamp.toString();
            issued = issued.substring(0, 19);

            //Convert expiration date to string
            PreparedStatement ps2 = ServerCore.preparedStatement("SELECT EXPIRATION FROM active_punishments WHERE UUID = ?");
            ps2.setString(1, player.getUniqueId().toString());
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();

            Timestamp timestamp2 = Timestamp.valueOf(rs2.getTimestamp("EXPIRATION").toLocalDateTime());
            String expiration = timestamp2.toString();
            expiration = expiration.substring(0, 19);

            //Get time remaining
            return Utils.findTimeDifference(issued, expiration);
        }
        return null;
    }

    public static Player issuer(Player player) throws SQLException {
        if (exists(player)) {
            PreparedStatement ps = ServerCore.preparedStatement("SELECT STAFF FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            UUID uuid = UUID.fromString(rs.getString("STAFF"));
            return Bukkit.getPlayer(uuid);
        }
        return null;
    }

    public static String getExpirationDate(Player player) throws SQLException {
        if (exists(player)) {
            PreparedStatement ps = ServerCore.preparedStatement("SELECT EXPIRATION FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();

            Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("EXPIRATION").toLocalDateTime());
            String expiration = timestamp.toString();
            expiration = expiration.substring(0, 19);
            return expiration;
        }
        return null;
    }

    public static String getIssuedDate(Player player) throws SQLException {
        if(exists(player)) {
            PreparedStatement ps = ServerCore.preparedStatement("SELECT ISSUED FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();

            Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("ISSUED").toLocalDateTime());
            String issued = timestamp.toString();
            issued = issued.substring(0, 19);
            return issued;
        }
        return null;
    }

    public static String getType(Player player) {
        return null;
    }

    public static int getSeverity(Player player) {
        return 0;
    }

    public static String getReason(Player player) {
        return null;
    }

    /**
     * Check if player UUID currently exists in the 'active_punishments' table
     *
     * @param player - player whose UUID is being queried
     * @return condition stating if the player exists or not in the table
     */
    private static boolean exists(Player player) throws SQLException {
        PreparedStatement ps = ServerCore.preparedStatement("SELECT COUNT(UUID) FROM active_punishments WHERE UUID = ?;");
        ps.setString(1, player.getUniqueId().toString());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1) != 0;
    }


}
