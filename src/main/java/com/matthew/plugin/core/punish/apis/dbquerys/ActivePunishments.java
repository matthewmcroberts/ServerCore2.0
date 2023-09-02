package com.matthew.plugin.core.punish.apis.dbquerys;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.Punishments;
import com.matthew.plugin.core.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

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
    private static boolean exists(OfflinePlayer player) throws SQLException {
        PreparedStatement ps = ServerCore.preparedStatement("SELECT COUNT(UUID) FROM active_punishments WHERE UUID = ?;");
        ps.setString(1, player.getUniqueId().toString());
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1) != 0;
    }

    /**
     * Check if punishment ID currently exists in the 'active_punishments' table
     *
     * @param id - Primary key that is being queried
     * @return condition stating if the id exists or not in the table
     */
    private static boolean exists(int id) throws SQLException {
        PreparedStatement ps = ServerCore.preparedStatement("SELECT COUNT(UUID) FROM active_punishments WHERE ID = ?;");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1) != 0;
    }

    /**
     * Get the time remaining on a player's punishment
     * The String in 'remaining' is formatted as follows:
     * - [Years, Days, Hours, Minutes, Seconds]
     *
     * @param player - Player whose UUID is being queried
     * @param type type of punishment being checked (hacking, gameplay, chat)
     * @return a string showing the time remaining in the format above
     */
    public static String getTimeRemaining(OfflinePlayer player, Punishments type) throws SQLException {
        if (exists(player)) {
            String remaining;

            PreparedStatement ps = ServerCore.preparedStatement("SELECT ISSUED FROM active_punishments WHERE UUID = ? AND TYPE = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, type.getName());
            ResultSet rs = ps.executeQuery();

            PreparedStatement ps2 = ServerCore.preparedStatement("SELECT EXPIRATION FROM active_punishments WHERE UUID = ? AND TYPE = ?");
            ps2.setString(1, player.getUniqueId().toString());
            ps2.setString(2, type.getName());
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();

            //Convert issued date to string
            Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("ISSUED").toLocalDateTime());
            String issued = timestamp.toString();
            issued = issued.substring(0, 19);

            //Convert expiration date to string
            Timestamp timestamp2 = Timestamp.valueOf(rs2.getTimestamp("EXPIRATION").toLocalDateTime());
            String expiration = timestamp2.toString();
            expiration = expiration.substring(0, 19);

            remaining = Utils.findTimeDifference(issued, expiration);


            //Return time remaining
            return remaining;
        }
        return null;
    }

    /**
     * Get the staff member who issued the specific punishment type on a player in the 'active_punishments' table
     *
     * @param player - Player whose UUID is being queried
     * @param type type of punishment being checked (hacking, gameplay, chat)
     * @return Staff as type Player that issued the punishment
     */
    public static OfflinePlayer issuer(OfflinePlayer player, Punishments type) throws SQLException {
        if (exists(player)) {
            OfflinePlayer staff;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT STAFF FROM active_punishments WHERE UUID = ? AND TYPE = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, type.getName());
            ResultSet rs = ps.executeQuery();
            rs.next();
            UUID uuid = UUID.fromString(rs.getString("STAFF"));

            staff = Bukkit.getPlayer(uuid);

            return staff;
        }
        return null;
    }

    /**
     * Get when the punishment that is currently active in the 'active_punishments' table expires
     *
     * @param player - Player whose UUID is being queried
     * @param type type of punishment being checked (hacking, gameplay, chat)
     * @return the expiration date(s) as a string in the format of:
     * 'yyyy-MM-dd HH:mm:ss'
     */
    public static String getExpirationDate(OfflinePlayer player, Punishments type) throws SQLException {
        if (exists(player)) {
            String expirationDate;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT EXPIRATION FROM active_punishments WHERE UUID = ? AND TYPE = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, type.getName());
            ResultSet rs = ps.executeQuery();
            rs.next();
            Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("EXPIRATION").toLocalDateTime());
            String expiration = timestamp.toString();
            expirationDate = expiration.substring(0, 19);

            return expirationDate;
        }
        return null;
    }

    /**
     * Get when the punishment that is currently active in the 'active_punishments' table expires
     *
     * @param id - Primary key being queried
     * @return the expiration date as a string in the format of:
     * 'yyyy-MM-dd HH:mm:ss'
     */
    public static ArrayList<String> getExpirationDate(int id) throws SQLException {
        if (exists(id)) {
            ArrayList<String> expirations = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT EXPIRATION FROM active_punishments WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
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
     * Get when the punishment that is currently active in the 'active_punishments' table was issued
     *
     * @param player - Player whose UUID is being queried
     * @param type type of punishment being checked (hacking, gameplay, chat)
     * @return the issued date as a string in the format of:
     * 'yyyy-MM-dd HH:mm:ss'
     */
    public static String getIssuedDate(OfflinePlayer player, Punishments type) throws SQLException {
        if (exists(player)) {
            String issuedDate;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT ISSUED FROM active_punishments WHERE UUID = ? AND TYPE = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, type.getName());
            ResultSet rs = ps.executeQuery();
            rs.next();

            Timestamp timestamp = Timestamp.valueOf(rs.getTimestamp("ISSUED").toLocalDateTime());
            String issued2 = timestamp.toString();
            issuedDate = issued2.substring(0, 19);

            return issuedDate;
        }
        return null;
    }

    /**
     * Get the type of punishment that was issued to the player in the 'active_punishments' table
     * Possible punishment types:
     * - 'Hacking'
     * - 'Gameplay'
     * - 'Chat'
     *
     * @param player - Player whose UUID is being queried
     * @return the type of punishment as an enumerated type
     */
    public static Punishments getBanType(OfflinePlayer player) throws SQLException {
        if (exists(player)) {
            String type;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT TYPE FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("TYPE").equalsIgnoreCase(Punishments.HACKING.getName()) || rs.getString("TYPE").equalsIgnoreCase(Punishments.GAMEPLAY.getName())) {
                    type = rs.getString("TYPE");
                    return Punishments.valueOf(type.toUpperCase());
                }
            }
        }
        return null;
    }

    /**
     * Get the severity of the punishment issued
     * Severities possible range from 1-4
     *
     * @param player - Player whose UUID is being queried
     * @param type type of punishment being checked (hacking, gameplay, chat)
     * @return an integer that refers to the severity level applied
     */
    public static int getSeverity(OfflinePlayer player, Punishments type) throws SQLException {
        if (exists(player)) {
            int sev;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT SEV FROM active_punishments WHERE UUID = ? AND TYPE = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, type.getName());
            ResultSet rs = ps.executeQuery();
            rs.next();
            sev = rs.getInt("SEV");

            return sev;
        }
        return 0;
    }

    /**
     * Get the reason the player was punished. Reasons are TINYTEXT that are up to 255 chars
     *
     * @param player - Player whose UUID is being queried
     * @param type type of punishment being checked (hacking, gameplay, chat)
     * @return the reason as a string
     */
    public static String getReason(OfflinePlayer player, Punishments type) throws SQLException {
        if (exists(player)) {
            String reason;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT REASON FROM active_punishments WHERE UUID = ? AND TYPE = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, type.getName());
            ResultSet rs = ps.executeQuery();
            rs.next();
            reason = rs.getString("REASON");

            return reason;
        }
        return null;
    }

    /**
     * Get the reason in the REASON column under the primary key id. Reasons are TINYTEXT that are up to 255 chars
     *
     * @param id - Primary key for punishment in table
     * @return the reason as a string
     */
    public static ArrayList<String> getReason(int id) throws SQLException {
        if (exists(id)) {
            ArrayList<String> reasons = new ArrayList<>();
            PreparedStatement ps = ServerCore.preparedStatement("SELECT REASON FROM active_punishments WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                reasons.add(rs.getString("REASON"));
            }
            return reasons;
        }
        return null;
    }

    /**
     * Get if the punishment issued to the player is permanent
     *
     * @param player - Player whose UUID is being queried
     * @param type type of punishment being checked (hacking, gameplay, chat)
     * @return condition whether punishment is permanent or not
     */
    public static boolean isPermanent(OfflinePlayer player, Punishments type) throws SQLException {
        if (exists(player)) {
            boolean perm;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT PERMANENT FROM active_punishments WHERE UUID = ? AND TYPE = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, type.getName());
            ResultSet rs = ps.executeQuery();
            rs.next();
            perm = rs.getBoolean("PERMANENT");

            return perm;
        }
        return false;
    }

    /**
     * Get the amount of punishments found for a specific player in the 'active_punishments' table
     *  2 - means the player has a 'Chat' and 'Hacking' or 'Gameplay' punishment
     *  1 - means they have either a 'Chat' 'Hacking' or 'Gameplay' punishment
     *  0 - means no punishments found
     *
     * @param player - Player whose UUID is being queried
     * @return int representing amount of times a player appears in the table
     */
    public static int getAmount(OfflinePlayer player) throws SQLException {
        if (exists(player)) {
            int count = 0;
            PreparedStatement ps = ServerCore.preparedStatement("SELECT PERMANENT FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count++;
            }
            return count;
        }
        return 0;
    }

    /**
     * Get the punishment id/primary key in 'active_punishments' table for a specified punishment type for a player
     *
     * @param player Player whose UUID is being queried
     * @param type the type of punishment the player has whose primary key id is being returned
     * @return the primary key id representing a specific punishment a player has
     */
    public static int getPunishmentId(OfflinePlayer player, Punishments type) throws SQLException {
        if (exists(player)) {
            System.out.println(type.getName());
            PreparedStatement ps = ServerCore.preparedStatement("SELECT ID FROM active_punishments WHERE UUID = ? AND TYPE = ?");
            ps.setString(1, player.getUniqueId().toString());
            ps.setString(2, type.getName());
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("ID");
            }
        }
        return 0;
    }

    /**
     * Check if player currently has a punishment of type "hacking" or "gameplay"
     * meaning that the player is currently banned from the server
     *
     * @param player Player whose UUID is being queried
     * @return condition whether player is banned or not
     */
    public static boolean isBanned(OfflinePlayer player) throws SQLException {
        if (exists(player)) {
            return getBanType(player) != null;
        }
        return false;
    }

    /**
     * Check if player currently has a punishment of type chat
     * meaning that the player is currently muted from chat
     *
     * @param player Player whose UUID is being queried
     * @return condition whether player is muted or not
     */
    public static boolean isMuted(OfflinePlayer player) throws SQLException {
        if (exists(player)) {
            PreparedStatement ps = ServerCore.preparedStatement("SELECT TYPE FROM active_punishments WHERE UUID = ?");
            ps.setString(1, player.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("TYPE").equalsIgnoreCase(Punishments.CHAT.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
