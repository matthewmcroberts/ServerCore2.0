package com.matthew.plugin.core.commands.gamemode.api;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class GamemodeManager {

    /**
     * The purpose of the hashmap is to keep track of who a player has given gamemode to. The Key is the player giving gamemode,
     * the value is the player receiving gamemode
     */

    private static HashMap<Player, Player> gamemodeToggle = new HashMap<>();

    /**
     * Give gamemode to the target
     *
     * @param player - player assigning gamemode to the target
     * @param target - player receiving gamemode
     */
    public static void giveGamemode(Player player, Player target) {
        target.setGameMode(GameMode.CREATIVE);
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Set " + ChatColor.GOLD +
                target.getName() + "'s " + ChatColor.GRAY +
                "GameMode creative:" + ChatColor.GREEN + " True.");
        if (target != player) {
            target.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY +
                    "GameMode creative:" + ChatColor.GREEN + " True.");
        }
        gamemodeToggle.put(player, target);
    }

    /**
     * Give gamemode to the player
     *
     * @param player - player receiving gamemode
     */
    public static void giveGamemode(Player player) {
        player.setGameMode(GameMode.CREATIVE);
        player.sendMessage(ChatColor.BLUE + ">>" + ChatColor.GRAY + " GameMode creative: " +
                ChatColor.GREEN + "True.");
    }

    /**
     * Remove gamemode to the target
     *
     * @param player - player removing gamemode from the target
     * @param target - player losing gamemode
     */
    public static void removeGamemode(Player player, Player target) {
        target.setGameMode(GameMode.SURVIVAL);
        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Set " + ChatColor.GOLD +
                target.getName() + "'s " + ChatColor.GRAY + "GameMode creative:" + ChatColor.RED +
                " False.");
        if (target != player) {
            target.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY +
                    "GameMode creative:" + ChatColor.RED + " False.");
        }
        gamemodeToggle.remove(player);
    }

    /**
     * Remove gamemode from the player
     *
     * @param player - player receiving gamemode
     */
    public static void removeGamemode(Player player) {
        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage(ChatColor.BLUE + ">>" + ChatColor.GRAY + " GameMode creative: " +
                ChatColor.RED + "False.");
    }


    /**
     * Get the gamemodeToggle hashmap containing who all a player (key) has given gamemode to
     *
     * @return the gamemodeToggle hashmap
     */
    public static HashMap<Player, Player> getGamemodeToggle() {
        return gamemodeToggle;
    }

}
