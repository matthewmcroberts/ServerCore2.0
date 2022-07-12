package com.matthew.plugin.core.commands.vanish.utils;

import com.matthew.plugin.core.ranks.apis.RankManager;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class VanishUtils {

    public static boolean isJrMod(Player player) throws SQLException {
        if(RankManager.getRank(player).getName().equalsIgnoreCase("jrmod") || RankManager.getRank(player).getName().equalsIgnoreCase("mod")
                ||RankManager.getRank(player).getName().equalsIgnoreCase("srmod") ||RankManager.getRank(player).getName().equalsIgnoreCase("admin")
                ||RankManager.getRank(player).getName().equalsIgnoreCase("dev") || RankManager.getRank(player).getName().equalsIgnoreCase("owner")) {
            return true;
        }
        return false;
    }

    public static boolean isMod(Player player) throws SQLException {
        if(RankManager.getRank(player).getName().equalsIgnoreCase("mod") || RankManager.getRank(player).getName().equalsIgnoreCase("srmod") ||
                RankManager.getRank(player).getName().equalsIgnoreCase("admin") ||RankManager.getRank(player).getName().equalsIgnoreCase("dev")
                || RankManager.getRank(player).getName().equalsIgnoreCase("owner")) {
            return true;
        }
        return false;
    }

    public static boolean isSrMod(Player player) throws SQLException {
        if(RankManager.getRank(player).getName().equalsIgnoreCase("srmod") ||RankManager.getRank(player).getName().equalsIgnoreCase("admin")
                ||RankManager.getRank(player).getName().equalsIgnoreCase("dev") || RankManager.getRank(player).getName().equalsIgnoreCase("owner")) {
            return true;
        }
        return false;
    }

    public static boolean isAdmin(Player player) throws SQLException {
        if(RankManager.getRank(player).getName().equalsIgnoreCase("admin") ||RankManager.getRank(player).getName().equalsIgnoreCase("dev")
                || RankManager.getRank(player).getName().equalsIgnoreCase("owner")) {
            return true;
        }
        return false;
    }
}
