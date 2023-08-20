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

    /**
     * Get the time remaining on a player's punishment
     * The one-dimensional array is formatted as follows:
     *  - {Years, Days, Hours, Minutes, Seconds}
     *
     * @param player - Player whose UUID is being queried
     * @return 1D array of times as type long
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

            //Return time remaining
            return Utils.findTimeDifference(issued, expiration);
        }
        return null;
    }

    /**
     * Get the staff member who issued the punishment in the 'active_punishments' table
     *
     * @param player - Player whose UUID is being queried
     * @return player that issued the punishment
     */
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

    /**
     * Get when the punishment that is currently active in the 'active_punishments' table expires
     *
     * @param player - Player whose UUID is being queried
     * @return the expiration date as a string in the format of:
     *         'yyyy-MM-dd HH:mm:ss'
     */
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

    /**
     * Get when the punishment that is currently active in the 'active_punishments' table was issued
     *
     * @param player - Player whose UUID is being queried
     * @return the issued date as a string in the format of:
     *         'yyyy-MM-dd HH:mm:ss'
     */
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

    /**
     * Get the type of punishment that was issued to the player in the 'active_punishments' table
     * Possible punishment types:
     *  - 'Hacking'
     *  - 'Gameplay'
     *  - 'Chat'
     *
     * @param player - Player whose UUID is being queried
     * @return the type of punishment as a string
     */
    public static String getType(Player player) throws SQLException {
        if(exists(player)) {
            PreparedStatement ps = ServerCore.preparedStatement("SELECT TYPE FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("TYPE");
        }
        return null;
    }

    /**
     * Get the severity of the punishment issued
     * Severities possible range from 1-4
     *
     * @param player - Player whose UUID is being queried
     * @return an integer that refers to the severity level applied
     */
    public static int getSeverity(Player player) throws SQLException {
        if(exists(player)) {
            PreparedStatement ps = ServerCore.preparedStatement("SELECT SEV FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt("SEV");
        }
        return 0;
    }

    /**
     * Get the reason the player was punished. Reasons are TINYTEXT that are up to 255 chars
     *
     * @param player - Player whose UUID is being queried
     * @return the reason as a string
     */
    public static String getReason(Player player) throws SQLException {
        if(exists(player)) {
            PreparedStatement ps = ServerCore.preparedStatement("SELECT REASON FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getString("REASON");
        }
        return null;
    }

    /**
     * Get if the punishment issued to the player is permanent
     *
     * @param player - Player whose UUID is being queried
     * @return condition whether punishment is permanent or not
     */
    public static boolean isPermanent(Player player) throws SQLException {
        if(exists(player)) {
            PreparedStatement ps = ServerCore.preparedStatement("SELECT PERMANENT FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getBoolean("PERMANENT");
        }
        return false;
    }
}
