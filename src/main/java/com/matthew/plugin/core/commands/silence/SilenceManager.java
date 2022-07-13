package com.matthew.plugin.core.commands.silence;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SilenceManager {

    private static ArrayList<Player> silenceManager = new ArrayList<>();

    public static ArrayList<Player> getSilenceManager() {
        return silenceManager;
    }

}
