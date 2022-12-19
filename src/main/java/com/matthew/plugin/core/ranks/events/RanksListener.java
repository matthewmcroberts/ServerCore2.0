package com.matthew.plugin.core.ranks.events;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.ranks.Ranks;
import com.matthew.plugin.core.ranks.apis.RankManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RanksListener implements Listener {

    /**
     * Once a player joins, they are set into the player_data table in the database.
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!e.getPlayer().hasPlayedBefore()) {
            try {
                ResultSet rs = ServerCore.getInstance().preparedStatement("SELECT COUNT(UUID) FROM player_data WHERE UUID = '" + player.getUniqueId().toString() + "';").executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) { //Meaning 0 records match the players UUID, so player doesn't exist in the Database
                    ServerCore.getInstance().preparedStatement("INSERT INTO player_data(UUID, RANK) VALUES ('" + player.getUniqueId().toString() + "'," + "'MEMBER');").executeUpdate();
                    RankManager.setRank(player.getUniqueId(), Ranks.MEMBER);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

