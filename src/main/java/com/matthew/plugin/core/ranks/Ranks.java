package com.matthew.plugin.core.ranks;

import org.bukkit.ChatColor;

public enum Ranks {

    OWNER("OWNER", ChatColor.RED),
    DEV("DEV", ChatColor.RED),
    QAT("QAT", ChatColor.BLUE),
    ADMIN("ADMIN", ChatColor.RED),
    SRMOD("SRMOD", ChatColor.GOLD),
    MOD("MOD", ChatColor.GOLD),
    JRMOD("JRMOD", ChatColor.DARK_AQUA),
    BUILDER("BUILDER", ChatColor.BLUE),
    GOD("GOD", ChatColor.AQUA),
    SLAYER("SLAYER", ChatColor.DARK_PURPLE),
    KNOWN("KNOWN", ChatColor.GREEN),
    MEMBER("MEMBER", ChatColor.GRAY);

    private String name;

    private ChatColor color;

    Ranks(String name, ChatColor color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {

        return this.name;
    }

    public ChatColor getColor() {

        return this.color;
    }
}
