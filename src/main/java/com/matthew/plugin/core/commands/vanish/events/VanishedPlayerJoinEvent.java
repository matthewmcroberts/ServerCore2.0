package com.matthew.plugin.core.commands.vanish.events;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.commands.vanish.VanishManager;
import com.matthew.plugin.core.commands.vanish.utils.VanishUtils;
import com.matthew.plugin.core.ranks.Ranks;
import com.matthew.plugin.core.ranks.apis.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VanishedPlayerJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        try {
            ResultSet rs = ServerCore.preparedStatement("SELECT COUNT(UUID) FROM player_vanish WHERE UUID = '" + p.getUniqueId() + "';").executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) { //Meaning 0 records match the players UUID, in other words player UUID doesn't exist in the Database
                ServerCore.preparedStatement("INSERT INTO player_vanish(UUID, VANISH) VALUES ('" + p.getUniqueId() + "'," + false + ");").executeUpdate();
            } else {
                ResultSet rs2 = ServerCore.preparedStatement("SELECT * FROM player_vanish WHERE UUID = '" + p.getUniqueId() + "';").executeQuery();
                rs2.next();
                Boolean vanish = rs2.getBoolean("VANISH");
                VanishManager.setVanished(p, vanish);

                if (VanishManager.getIfVanished(p)) {
                    VanishManager.vanished.add(p);
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        Ranks targetRank = RankManager.getRank(target);
                        switch (RankManager.getRank(p).getName()) {
                            case "DEV":
                                if (targetRank.getName().equalsIgnoreCase("owner")) {
                                    target.showPlayer(p);
                                    e.setJoinMessage(null);
                                } else {
                                    target.hidePlayer(p);
                                    e.setJoinMessage(null);
                                }
                                break;
                            case "ADMIN":
                                if (VanishUtils.isAdmin(target)) {
                                    target.showPlayer(p);
                                    e.setJoinMessage(null);
                                } else {
                                    target.hidePlayer(p);
                                    e.setJoinMessage(null);
                                }
                                break;
                            case "SR.MOD":
                                if (VanishUtils.isSrMod(target)) {
                                    target.showPlayer(p);
                                    e.setJoinMessage(null);
                                } else {
                                    target.hidePlayer(p);
                                    e.setJoinMessage(null);
                                }
                                break;
                            case "MOD":
                                if (VanishUtils.isMod(target)) {
                                    target.showPlayer(p);
                                    e.setJoinMessage(null);
                                } else {
                                    target.hidePlayer(p);
                                    e.setJoinMessage(null);
                                }
                                break;
                            case "JR.MOD":
                                if (VanishUtils.isJrMod(target)) {
                                    target.showPlayer(p);
                                    e.setJoinMessage(null);
                                } else {
                                    target.hidePlayer(p);
                                    e.setJoinMessage(null);
                                }
                                break;
                            default:
                                target.hidePlayer(p);
                                e.setJoinMessage(null);
                                break;
                        }
                    }
                    p.sendMessage(ChatColor.BLUE + ">>-------------------------------<<");
                    p.sendMessage(ChatColor.GRAY + " Vanish mode is currently " + ChatColor.GREEN + "enabled");
                    p.sendMessage(ChatColor.BLUE + ">>-------------------------------<<");

                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
