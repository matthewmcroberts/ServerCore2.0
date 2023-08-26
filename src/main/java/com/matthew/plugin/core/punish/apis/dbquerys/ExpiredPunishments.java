package com.matthew.plugin.core.punish.apis.dbquerys;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

public class ExpiredPunishments {

    /**
     * Each ArrayList has the ability to return as many punishments as required as the 'expired_punishments' table holds all
     * previous punishments for that individual player
     * -------------------------------------------------------
     *  Database Columns for 'expired_punishments' table:
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
     * Check if player UUID currently exists in the 'expired_punishments' table
     *
     * @param player - player whose UUID is being queried
     * @return condition stating if the player exists or not in the table
     */
    private static boolean exists(OfflinePlayer player) throws SQLException {
        PreparedStatement ps = ServerCore.preparedStatement("SELECT COUNT(UUID) FROM expired_punishments WHERE UUID = ?;");
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
    public static ArrayList<String> getTimeRemaining(OfflinePlayer player) throws SQLException {
        if (exists(player)) {
            ArrayList<String> remaining = new ArrayList<>();

            PreparedStatement ps = ServerCore.preparedStatement("SELECT ISSUED FROM expired_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();

            PreparedStatement ps2 = ServerCore.preparedStatement("SELECT EXPIRATION FROM expired_punishments WHERE UUID = ?");
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
     * Get the staff member(s) who issued the punishment(s) in the 'expired_punishments' table
     *
     * @param player - Player whose UUID is being queried
     * @return Staff(s) as type Player that issued the punishment
     */
    public static ArrayList<OfflinePlayer> issuer(OfflinePlayer player) throws SQLException {
        if (exists(player)) {
            ArrayList<OfflinePlayer> list = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT STAFF FROM expired_punishments WHERE UUID = ?");
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
     * Get when the punishment(s) that are currently expired in the 'expired_punishments' table expires
     *
     * @param player - Player whose UUID is being queried
     * @return the expiration date(s) as a string in the format of:
     *         'yyyy-MM-dd HH:mm:ss'
     */
    public static ArrayList<String> getExpirationDate(OfflinePlayer player) throws SQLException {
        if (exists(player)) {
            ArrayList<String> expirations = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT EXPIRATION FROM expired_punishments WHERE UUID = ?");
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
     * Get when the punishment(s) that are currently expired in the 'expired_punishments' table were issued
     *
     * @param player - Player whose UUID is being queried
     * @return the issued date(s) as a string in the format of:
     *         'yyyy-MM-dd HH:mm:ss'
     */
    public static ArrayList<String> getIssuedDate(OfflinePlayer player) throws SQLException {
        if(exists(player)) {
            ArrayList<String> issues = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT ISSUED FROM expired_punishments WHERE UUID = ?");
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
     * Get the type of punishment(s) that were issued to the player in the 'expired_punishments' table
     * Possible punishment types:
     *  - 'Hacking'
     *  - 'Gameplay'
     *  - 'Chat'
     *
     * @param player - Player whose UUID is being queried
     * @return the type of punishment(s) as a string
     */
    public static ArrayList<String> getType(OfflinePlayer player) throws SQLException {
        if(exists(player)) {
            ArrayList<String> types = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT TYPE FROM expired_punishments WHERE UUID = ?");
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
    public static ArrayList<Integer> getSeverity(OfflinePlayer player) throws SQLException {
        if(exists(player)) {
            ArrayList<Integer> sevs = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT SEV FROM expired_punishments WHERE UUID = ?");
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
    public static ArrayList<String> getReason(OfflinePlayer player) throws SQLException {
        if(exists(player)) {
            ArrayList<String> reasons = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT REASON FROM expired_punishments WHERE UUID = ?");
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
    public static ArrayList<Boolean> isPermanent(OfflinePlayer player) throws SQLException {
        if(exists(player)) {
            ArrayList<Boolean> perms = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT PERMANENT FROM expired_punishments WHERE UUID = ?");
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
     * Get the amount of punishments found for a specific player in the 'expired_punishments' table
     *
     * @param player - Player whose UUID is being queried
     * @return int representing amount of times a player appears in the table
     */
    public static int getAmount(OfflinePlayer player) throws SQLException {
        if(exists(player)) {
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT PERMANENT FROM expired_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                count++;
            }
            return count;
        }
        return 0;
    }

    /**
     * Get the punishment id/primary key in 'expired_punishments' table for a specified punishment type for a player
     *
     * @param player Player whose UUID is being queried
     * @param type the type of punishment the player has whose primary key id is being returned
     * @return the primary key id representing a specific punishment a player has
     */
    public static int getPunishmentId(OfflinePlayer player, String type) throws SQLException {
        if(exists(player)) {
            if(type.equalsIgnoreCase("hacking") || type.equalsIgnoreCase("chat") || type.equalsIgnoreCase("gameplay")) {
                PreparedStatement ps = ServerCore.preparedStatement("SELECT ID FROM expired_punishments WHERE UUID = ? AND TYPE = ?");
                ps.setString(1, player.getUniqueId().toString());
                type = type.toLowerCase();
                String body = type.substring(1);
                String firstLetter = type.substring(0, 1).toUpperCase();
                String newType = firstLetter + body;
                ps.setString(1, newType);
                ResultSet rs = ps.executeQuery();
                return rs.getInt("ID");
            }
        }
        return 0;
    }
}
