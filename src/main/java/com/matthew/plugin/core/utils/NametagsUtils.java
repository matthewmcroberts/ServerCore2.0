package com.matthew.plugin.core.utils;

import com.matthew.plugin.core.nametags.NametagsManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagsUtils {

    public static void addToOwnerTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.RED + "[OWNER] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team owner");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team owner");
    }

    public static void addToAdminTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.RED + "[ADMIN] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team admin");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team admin");
    }

    public static void addToDevTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.RED + "[DEV] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team dev");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team dev");
    }

    public static void addToSrModTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.GOLD + "[SR.MOD] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team srmod");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team srmod");
    }

    public static void addToModTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.GOLD + "[MOD] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team mod");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team mod");
    }

    public static void addToJrModTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.DARK_AQUA + "[JR.MOD] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team jrmod");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team jrmod");
    }

    public static void addToQatTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.BLUE + "[QAT] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team qat");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team qat");

    }

    public static void addToBuilderTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.BLUE + "[BUILDER] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team builder");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team builder");
    }

    public static void addToGodTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.AQUA + "[GOD] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team god");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team god");
    }

    public static void addToSlayerTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.DARK_PURPLE + "[SLAYER] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team slayer");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team slayer");
    }

    public static void addToKnownTeam(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.GREEN + "[KNOWN] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team known");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team known");
    }

    public static void addToTeamMember(Player player) {
        for(Team team: NametagsManager.getTeamsList()) {
            if(team.getPrefix().equalsIgnoreCase(ChatColor.GRAY + "[MEMBER] ")) {
                team.addEntry(player.getName());
                System.out.println("Added " + player.getName() + " to team member");
                return;
            }
        }
        System.out.println("Failed to add " + player.getName() + " to team member");
    }
}
