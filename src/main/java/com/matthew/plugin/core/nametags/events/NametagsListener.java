package com.matthew.plugin.core.nametags.events;

import com.matthew.plugin.core.nametags.NametagsManager;
import com.matthew.plugin.core.ranks.apis.RankManager;
import com.matthew.plugin.core.utils.NametagsUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class NametagsListener implements Listener {

    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent e) throws SQLException {
        Player player = e.getPlayer();

        player.setScoreboard(NametagsManager.tabBoard);
        switch (RankManager.getRank(player).getName()) {
            case "OWNER":

                NametagsUtils.addToOwnerTeam(player);
                break;
            case "DEV":

                NametagsUtils.addToDevTeam(player);
                break;
            case "QAT":

                NametagsUtils.addToQatTeam(player);
                break;
            case "ADMIN":

                NametagsUtils.addToAdminTeam(player);
                break;
            case "SRMOD":

                NametagsUtils.addToSrModTeam(player);
                break;
            case "MOD":

                NametagsUtils.addToModTeam(player);
                break;
            case "JRMOD":

                NametagsUtils.addToJrModTeam(player);
                break;
            case "BUILDER":

                NametagsUtils.addToBuilderTeam(player);
                break;
            case "GOD":

                NametagsUtils.addToGodTeam(player);
                break;
            case "SLAYER":

                NametagsUtils.addToSlayerTeam(player);
                break;
            case "KNOWN":

                NametagsUtils.addToKnownTeam(player);
                break;
            case "MEMBER":

                NametagsUtils.addToTeamMember(player);
                break;

        }
    }
}
