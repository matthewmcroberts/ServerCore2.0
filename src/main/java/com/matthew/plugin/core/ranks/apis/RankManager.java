package com.matthew.plugin.core.ranks.apis;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.ranks.Ranks;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class RankManager {

    /**
     * Set the rank of the player
     *
     * @param player - player that is having their rank set
     * @param rank - rank that the player is being set to
     */
    public static void setRank(Player player, Ranks rank) {
        try {
            ServerCore.preparedStatement("UPDATE player_data SET RANKS = '" + rank.getName() + "' WHERE UUID = '" + player.getUniqueId() + "';").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the rank of the player using their uuid
     *
     * @param uuid - player that is having their rank set
     * @param rank - rank that the player is being set to
     */
    public static void setRank(UUID uuid, Ranks rank) {
        try {
            ServerCore.preparedStatement("UPDATE player_data SET RANKS = '" + rank.getName() + "' WHERE UUID = '" + uuid + "';").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the rank of the player if the player is offline and in the database
     *
     * @param player - player that is having their rank set
     * @param rank - rank that the player is being set to
     */
    public static void setRank(OfflinePlayer player, Ranks rank) {
        try {
            ServerCore.preparedStatement("UPDATE player_data SET RANKS = '" + rank.getName() + "' WHERE UUID = '" + player.getUniqueId() + "';").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Get the rank of the player
     *
     * @param player - player whose rank we are getting
     * @return the rank of the player
     * @throws SQLException - must be used due to accessing the sql database
     */
    public static Ranks getRank(Player player) throws SQLException {

        ResultSet rs2 = ServerCore.preparedStatement("SELECT * FROM player_data WHERE UUID = '" + player.getUniqueId() + "';").executeQuery();
        rs2.next();
        String rank2 = rs2.getString("RANKS");

        return Ranks.valueOf(rank2);
    }

    /**
     * Get the rank of the player using their uuid
     *
     * @param uuid - uuid of player whose rank we are getting
     * @return the rank of the player
     * @throws SQLException - must be used due to accessing the sql database
     */
    public static Ranks getRank(UUID uuid) throws SQLException {

        ResultSet rs2 = ServerCore.preparedStatement("SELECT * FROM player_data WHERE UUID = '" + uuid + "';").executeQuery();
        rs2.next();
        String rank2 = rs2.getString("RANKS");

        return Ranks.valueOf(rank2);
    }
}
