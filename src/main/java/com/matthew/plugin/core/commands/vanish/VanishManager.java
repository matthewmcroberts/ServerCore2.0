package com.matthew.plugin.core.commands.vanish;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.ranks.Ranks;
import com.matthew.plugin.core.ranks.apis.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VanishManager {

    public static ArrayList<Player> vanished = new ArrayList<>();

    public static void setVanished(Player player, Boolean vanished) {

        Player p = player.getPlayer();

        try {
            ResultSet rs = ServerCore.preparedStatement("SELECT COUNT(UUID) FROM player_vanish WHERE UUID = '" + player.getUniqueId() + "';").executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) { //Meaning 0 records match the players UUID, in other words player UUID doesn't exist in the Database
                ServerCore.preparedStatement("INSERT INTO player_vanish(UUID, VANISH) VALUES ('" + player.getUniqueId() + "'," + vanished + ");").executeUpdate();
            } else {
                Boolean vanish = vanished;
                ServerCore.preparedStatement("UPDATE player_vanish SET VANISH = " + vanish.booleanValue() + " WHERE UUID = '" + player.getUniqueId() + "';").executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean getIfVanished(Player player) throws SQLException {

        ResultSet rs2 = ServerCore.preparedStatement("SELECT * FROM player_vanish WHERE UUID = '" + player.getUniqueId() + "';").executeQuery();
        rs2.next();
        Boolean vanished = rs2.getBoolean("VANISH");

        return vanished;
    }

    public static void hideFromPlayers(Player player) throws SQLException {
        vanished.add(player);
        setVanished(player, true);
        for (Player target : Bukkit.getOnlinePlayers()) {
            Ranks targetRank = RankManager.getRank(target);
            switch (RankManager.getRank(player).getName()) {
                case "DEV":
                    if (targetRank.getName().equalsIgnoreCase("owner")) {
                        target.showPlayer(player);
                    } else {
                        target.hidePlayer(player);
                    }
                    break;
                case "ADMIN":
                    if (targetRank.getName().equalsIgnoreCase("owner")
                            || targetRank.getName().equalsIgnoreCase("dev")
                            || targetRank.getName().equalsIgnoreCase("admin")) {
                        target.showPlayer(player);
                    } else {
                        target.hidePlayer(player);
                    }
                    break;
                case "SR.MOD":
                    if (targetRank.getName().equalsIgnoreCase("owner")
                            || targetRank.getName().equalsIgnoreCase("dev")
                            || targetRank.getName().equalsIgnoreCase("admin")
                            || targetRank.getName().equalsIgnoreCase("srmod")) {
                        target.showPlayer(player);
                    } else {
                        target.hidePlayer(player);
                    }
                    break;
                case "MOD":
                    if (targetRank.getName().equalsIgnoreCase("owner")
                            || targetRank.getName().equalsIgnoreCase("dev")
                            || targetRank.getName().equalsIgnoreCase("admin")
                            || targetRank.getName().equalsIgnoreCase("srmod")
                            || targetRank.getName().equalsIgnoreCase("mod")) {
                        target.showPlayer(player);
                    } else {
                        target.hidePlayer(player);
                    }
                    break;
                case "JR.MOD":
                    if (targetRank.getName().equalsIgnoreCase("owner")
                            || targetRank.getName().equalsIgnoreCase("dev")
                            || targetRank.getName().equalsIgnoreCase("admin")
                            || targetRank.getName().equalsIgnoreCase("srmod")
                            || targetRank.getName().equalsIgnoreCase("mod")
                            || targetRank.getName().equalsIgnoreCase("jrmod")) {
                        target.showPlayer(player);
                    } else {
                        target.hidePlayer(player);
                    }
                    break;
                default:
                    target.hidePlayer(player);
                    break;
            }
        }
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Vanish mode: " + ChatColor.GREEN + "true");
        Bukkit.broadcastMessage(ChatColor.BLUE + "Quit> " + ChatColor.GRAY + player.getName());
    }

    public static void showToPlayers(Player player) {
        vanished.remove(player);
        for (Player target : Bukkit.getOnlinePlayers()) {
            target.showPlayer(player);
        }
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Vanish mode: " + ChatColor.RED + "false");
        Bukkit.broadcastMessage(ChatColor.BLUE + "Join> " + ChatColor.GRAY + player.getName());
    }
}
