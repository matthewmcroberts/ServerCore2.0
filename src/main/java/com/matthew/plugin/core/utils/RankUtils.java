package com.matthew.plugin.core.utils;

import com.matthew.plugin.core.ranks.Ranks;
import com.matthew.plugin.core.ranks.apis.RankManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class RankUtils {

    public static boolean isValidRank(String rank) {
        if (rank.equalsIgnoreCase("owner")
                || rank.equalsIgnoreCase("dev") || rank.equalsIgnoreCase("admin") || rank.equalsIgnoreCase("srmod")
                || rank.equalsIgnoreCase("mod") || rank.equalsIgnoreCase("jrmod") || rank.equalsIgnoreCase("god")
                || rank.equalsIgnoreCase("slayer") || rank.equalsIgnoreCase("known") || rank.equalsIgnoreCase("member")
                || rank.equalsIgnoreCase("builder") || rank.equalsIgnoreCase("qat")) {
            return true;
        }
        return false;
    }

    public static void unknownRank(Player player) {
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Unknown rank. List of ranks:");
        listOfRanks(player);
    }

    public static void listOfRanks(Player player) {
        player.sendMessage(ChatColor.BLUE + "    - " + ChatColor.YELLOW + "owner, dev, admin, srmod, mod, jrmod, god, slayer, known, member, builder, qat");
    }

    public static boolean isAdmin(Player player) throws SQLException {
        if (RankManager.getRank(player).equals(Ranks.ADMIN) || RankManager.getRank(player).equals(Ranks.DEV) || RankManager.getRank(player).equals(Ranks.QAT) || player.isOp()) {
            return true;
        }
        return false;
    }

    public static boolean isMember(Player player) throws SQLException {
        if (RankManager.getRank(player).equals(Ranks.ADMIN) || RankManager.getRank(player).equals(Ranks.DEV) || RankManager.getRank(player).equals(Ranks.QAT) || player.isOp()
                || RankManager.getRank(player).equals(Ranks.SRMOD) || RankManager.getRank(player).equals(Ranks.MOD) || RankManager.getRank(player).equals(Ranks.JRMOD)
                || RankManager.getRank(player).equals(Ranks.GOD) || RankManager.getRank(player).equals(Ranks.SLAYER) || RankManager.getRank(player).equals(Ranks.BUILDER)
                || RankManager.getRank(player).equals(Ranks.KNOWN) || RankManager.getRank(player).equals(Ranks.MEMBER)) {
            return true;
        }
        return false;
    }

    public static boolean isNotStaff(Player player) throws SQLException {
        if (RankManager.getRank(player).equals(Ranks.GOD) || RankManager.getRank(player).equals(Ranks.SLAYER) || RankManager.getRank(player).equals(Ranks.BUILDER)
                || RankManager.getRank(player).equals(Ranks.KNOWN) || RankManager.getRank(player).equals(Ranks.MEMBER)) {
            return true;
        }
        return false;
    }

    public static boolean isJrMod(Player player) throws SQLException {
        if (RankManager.getRank(player).equals(Ranks.ADMIN) || RankManager.getRank(player).equals(Ranks.DEV) || RankManager.getRank(player).equals(Ranks.QAT) || player.isOp()
                || RankManager.getRank(player).equals(Ranks.SRMOD) || RankManager.getRank(player).equals(Ranks.MOD) || RankManager.getRank(player).equals(Ranks.JRMOD)) {
            return true;
        }
        return false;
    }

    public static boolean isMod(Player player) throws SQLException {
        if (RankManager.getRank(player).equals(Ranks.ADMIN) || RankManager.getRank(player).equals(Ranks.DEV) || RankManager.getRank(player).equals(Ranks.QAT) || player.isOp()
                || RankManager.getRank(player).equals(Ranks.SRMOD) || RankManager.getRank(player).equals(Ranks.MOD)) {
            return true;
        }
        return false;
    }

    public static boolean isSrMod(Player player) throws SQLException {
        if (RankManager.getRank(player).equals(Ranks.ADMIN) || RankManager.getRank(player).equals(Ranks.DEV) || RankManager.getRank(player).equals(Ranks.QAT) || player.isOp()
                || RankManager.getRank(player).equals(Ranks.SRMOD)) {
            return true;
        }
        return false;
    }

}
