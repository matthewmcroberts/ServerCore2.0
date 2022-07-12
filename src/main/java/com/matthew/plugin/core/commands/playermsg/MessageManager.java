package com.matthew.plugin.core.commands.playermsg;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MessageManager {

    /**
     * Once a player uses /msg, they will be placed as the key in the hashmap along with the person they sent the message to as the value
     */
    private static HashMap<Player, Player> recentlyMessaged = new HashMap<>();

    /**
     * Get the HashMap that contains the recently messaged players and their messenger
     *
     * @return the recently messaged hashmap
     */
    public static HashMap<Player, Player> getRecentlyMessaged() {
        return recentlyMessaged;
    }

}
