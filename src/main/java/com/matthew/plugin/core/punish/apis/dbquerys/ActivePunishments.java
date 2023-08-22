package com.matthew.plugin.core.punish.apis.dbquerys;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

public class ActivePunishments {

    /**
     * The length of each ArrayList returned by each utility method is 2 as the maximum number of punishments that will be returned from
     * the 'active_punishments' table is 2 due to a player only being able to have two active punishments at a time. (Chat + Ban/Gameplay) punishment
     * -------------------------------------------------------
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
     * Get the time remaining on a player's punishment(s)
     * The Strings in the 'remaining' ArrayList are formatted as follows:
     *  - [Years, Days, Hours, Minutes, Seconds]
     *
     * @param player - Player whose UUID is being queried
     * @return arraylist of times as type String
     */
    public static ArrayList<String> getTimeRemaining(Player player) throws SQLException {
        if (exists(player)) {
            ArrayList<String> remaining = new ArrayList<>();

            PreparedStatement ps = ServerCore.preparedStatement("SELECT ISSUED FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();

            PreparedStatement ps2 = ServerCore.preparedStatement("SELECT EXPIRATION FROM active_punishments WHERE UUID = ?");
            ps2.setString(1, player.getUniqueId().toString());
            ResultSet rs2 = ps2.executeQuery();

            while(rs.next()) {
                rs2.next();

                //Convert issued date to string
                Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("ISSUED").toLocalDateTime());
                String issued = timestamp.toString();
                issued = issued.substring(0, 19);

                //Convert expiration date to string
                Timestamp timestamp2 = Timestamp.valueOf(rs2.getTimestamp("EXPIRATION").toLocalDateTime());
                String expiration = timestamp2.toString();
                expiration = expiration.substring(0, 19);

                remaining.add(Utils.findTimeDifference(issued, expiration));
            }

            //Return time remaining
            return remaining;
        }
        return null;
    }

    /**
     * Get the staff member(s) who issued the punishment(s) in the 'active_punishments' table
     *
     * @param player - Player whose UUID is being queried
     * @return Staff(s) as type Player that issued the punishment
     */
    public static ArrayList<Player> issuer(Player player) throws SQLException {
        if (exists(player)) {
            ArrayList<Player> list = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT STAFF FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UUID uuid = UUID.fromString(rs.getString("STAFF"));

                list.add(Bukkit.getPlayer(uuid));
            }
            return list;
        }
        return null;
    }

    /**
     * Get when the punishment(s) that are currently active in the 'active_punishments' table expires
     *
     * @param player - Player whose UUID is being queried
     * @return the expiration date(s) as a string in the format of:
     *         'yyyy-MM-dd HH:mm:ss'
     */
    public static ArrayList<String> getExpirationDate(Player player) throws SQLException {
        if (exists(player)) {
            ArrayList<String> expirations = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT EXPIRATION FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("EXPIRATION").toLocalDateTime());
                String expiration = timestamp.toString();
                expiration = expiration.substring(0, 19);
                expirations.add(expiration);
            }

            return expirations;
        }
        return null;
    }

    /**
     * Get when the punishment(s) that are currently active in the 'active_punishments' table were issued
     *
     * @param player - Player whose UUID is being queried
     * @return the issued date(s) as a string in the format of:
     *         'yyyy-MM-dd HH:mm:ss'
     */
    public static ArrayList<String> getIssuedDate(Player player) throws SQLException {
        if(exists(player)) {
            ArrayList<String> issues = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT ISSUED FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("ISSUED").toLocalDateTime());
                String issued = timestamp.toString();
                issued = issued.substring(0, 19);
                issues.add(issued);
            }
            return issues;
        }
        return null;
    }

    /**
     * Get the type of punishment(s) that were issued to the player in the 'active_punishments' table
     * Possible punishment types:
     *  - 'Hacking'
     *  - 'Gameplay'
     *  - 'Chat'
     *
     * @param player - Player whose UUID is being queried
     * @return the type of punishment(s) as a string
     */
    public static ArrayList<String> getType(Player player) throws SQLException {
        if(exists(player)) {
            ArrayList<String> types = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT TYPE FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                types.add(rs.getString("TYPE"));
            }
            return types;
        }
        return null;
    }

    /**
     * Get the severity of the punishment(s) issued
     * Severities possible range from 1-4
     *
     * @param player - Player whose UUID is being queried
     * @return an integer that refers to the severity level(s) applied
     */
    public static ArrayList<Integer> getSeverity(Player player) throws SQLException {
        if(exists(player)) {
            ArrayList<Integer> sevs = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT SEV FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                sevs.add(rs.getInt("SEV"));
            }
            return sevs;
        }
        return null;
    }

    /**
     * Get the reason(s) the player was punished. Reasons are TINYTEXT that are up to 255 chars
     *
     * @param player - Player whose UUID is being queried
     * @return the reason(s) as a string
     */
    public static ArrayList<String> getReason(Player player) throws SQLException {
        if(exists(player)) {
            ArrayList<String> reasons = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT REASON FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                reasons.add(rs.getString("REASON"));
            }
            return reasons;
        }
        return null;
    }

    /**
     * Get if the punishment(s) issued to the player is permanent
     *
     * @param player - Player whose UUID is being queried
     * @return condition whether punishment(s) are permanent or not
     */
    public static ArrayList<Boolean> isPermanent(Player player) throws SQLException {
        if(exists(player)) {
            ArrayList<Boolean> perms = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT PERMANENT FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                perms.add(rs.getBoolean("PERMANENT"));
            }
            return perms;
        }
        return null;
    }

    /**
     * Get the amount of punishments found for a specific player in the 'active_punishments' table
     *
     * @param player - Player whose UUID is being queried
     * @return int representing amount of times a player appears in the table
     */
    public static int getAmount(Player player) throws SQLException {
        if(exists(player)) {
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT PERMANENT FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                count++;
            }
            return count;
        }
        return 0;
    }
}
