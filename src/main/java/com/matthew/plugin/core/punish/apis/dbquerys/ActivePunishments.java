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

    /**
     * All one dimensional arrays that are returned are initialized with a size of 2 as the maximum number of punishments for one player in the
     * 'active_punishments' table is 2
     * <p>
     *  Database Columns for 'active_punishments' table:
     *      - ID INTEGER(PRIMARY)(AI)
     *      - UUID VARCHAR
     *      - TYPE VARCHAR
     *      - SEV INTEGER
     *      - REASONS TINYTEXT
     *      - ISSUED TIMESTAMP
     *      - EXPIRATION TIMESTAMP
     *      - STAFF VARCHAR
     *      - ACTIVE BOOLEAN
     *      - PERMANENT BOOLEAN
     * </p>
     */

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
    public static Player[] issuer(Player player) throws SQLException {
        if (exists(player)) {
            Player[] list = new Player[2];
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT STAFF FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("STAFF"));

                list[count] = Bukkit.getPlayer(uuid);
                count++;
            }
            return list;
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
    public static String[] getExpirationDate(Player player) throws SQLException {
        if (exists(player)) {
            String[] expirations = new String[2];
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT EXPIRATION FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("EXPIRATION").toLocalDateTime());
                String expiration = timestamp.toString();
                expiration = expiration.substring(0, 19);
                expirations[count] = expiration;
                count++;
            }

            return expirations;
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
    public static String[] getIssuedDate(Player player) throws SQLException {
        if(exists(player)) {
            String[] issues = new String[2];
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT ISSUED FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("ISSUED").toLocalDateTime());
                String issued = timestamp.toString();
                issued = issued.substring(0, 19);
                issues[count] = issued;
                count++;
            }
            return issues;
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
    public static String[] getType(Player player) throws SQLException {
        if(exists(player)) {
            String[] types = new String[2];
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT TYPE FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                types[count] = rs.getString("TYPE");
                count++;
            }
            return types;
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
    public static int[] getSeverity(Player player) throws SQLException {
        if(exists(player)) {
            int[] sevs = new int[2];
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT SEV FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                sevs[count] = rs.getInt("SEV");
                count++;
            }
            return sevs;
        }
        return null;
    }

    /**
     * Get the reason the player was punished. Reasons are TINYTEXT that are up to 255 chars
     *
     * @param player - Player whose UUID is being queried
     * @return the reason as a string
     */
    public static String[] getReason(Player player) throws SQLException {
        if(exists(player)) {
            String[] reasons = new String[2];
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT REASON FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                reasons[count] = rs.getString("REASON");
                count++;
            }
            return reasons;
        }
        return null;
    }

    /**
     * Get if the punishment issued to the player is permanent
     *
     * @param player - Player whose UUID is being queried
     * @return condition whether punishment is permanent or not
     */
    public static boolean[] isPermanent(Player player) throws SQLException {
        if(exists(player)) {
            boolean[] perms = new boolean[2];
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT PERMANENT FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                perms[count] = rs.getBoolean("PERMANENT");
            }
            return perms;
        }
        return null;
    }
}
