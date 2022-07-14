package com.matthew.plugin.core.nametags;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;

public class NametagsManager {

    private static ArrayList<Team> teamsList;
    public static final Scoreboard tabBoard = Bukkit.getScoreboardManager().getMainScoreboard();
    private static Team owner, dev, qat, admin, srmod, mod, jrmod, builder, god, slayer, known, member;

    public NametagsManager() {
        teamsList = new ArrayList<>();
        registerTeams();
        addTeamsToList();
    }

    private static void registerTeams() {
        owner = tabBoard.getTeam("a");
        dev = tabBoard.getTeam("b");
        qat = tabBoard.getTeam("c");
        admin = tabBoard.getTeam("d");
        srmod = tabBoard.getTeam("e");
        mod = tabBoard.getTeam("f");
        jrmod = tabBoard.getTeam("g");
        builder = tabBoard.getTeam("h");
        god = tabBoard.getTeam("i");
        slayer = tabBoard.getTeam("j");
        known = tabBoard.getTeam("k");
        member = tabBoard.getTeam("A");
        owner.setPrefix(ChatColor.RED + "[OWNER] ");
        dev.setPrefix(ChatColor.RED + "[DEV] ");
        qat.setPrefix(ChatColor.BLUE + "[QAT] ");
        admin.setPrefix(ChatColor.RED + "[ADMIN] ");
        srmod.setPrefix(ChatColor.GOLD + "[SR.MOD] ");
        mod.setPrefix(ChatColor.GOLD + "[MOD] ");
        jrmod.setPrefix(ChatColor.DARK_AQUA + "[JR.MOD] ");
        builder.setPrefix(ChatColor.BLUE + "[BUILDER] ");
        god.setPrefix(ChatColor.AQUA + "[GOD] ");
        slayer.setPrefix(ChatColor.DARK_PURPLE + "[SLAYER] ");
        known.setPrefix(ChatColor.GREEN + "[KNOWN] ");
        member.setPrefix(ChatColor.GRAY + "[MEMBER] ");
    }

    private static void addTeamsToList() {
        teamsList.add(owner);
        teamsList.add(dev);
        teamsList.add(qat);
        teamsList.add(admin);
        teamsList.add(srmod);
        teamsList.add(mod);
        teamsList.add(jrmod);
        teamsList.add(builder);
        teamsList.add(god);
        teamsList.add(slayer);
        teamsList.add(known);
        teamsList.add(member);

    }

    public static ArrayList<Team> getTeamsList() {
        return teamsList;
    }
}
