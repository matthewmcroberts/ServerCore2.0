package com.matthew.plugin.core.ranks;

import org.bukkit.ChatColor;

public enum Ranks {

    OWNER("OWNER", ChatColor.RED, 'a'),
    DEV("DEV", ChatColor.RED, 'b'),
    QAT("QAT", ChatColor.BLUE, 'c'),
    ADMIN("ADMIN", ChatColor.RED, 'd'),
    SRMOD("SRMOD", ChatColor.GOLD, 'e'),
    MOD("MOD", ChatColor.GOLD, 'f'),
    JRMOD("JRMOD", ChatColor.DARK_AQUA, 'g'),
    BUILDER("BUILDER", ChatColor.BLUE, 'h'),
    GOD("GOD", ChatColor.AQUA, 'i'),
    SLAYER("SLAYER", ChatColor.DARK_PURPLE, 'j'),
    KNOWN("KNOWN", ChatColor.GREEN, 'k'),
    MEMBER("MEMBER", ChatColor.GRAY, 'l');

    private final String name;

    private final ChatColor color;

    private final char lexicographicOrder;

    Ranks(String name, ChatColor color, char lexicographicOrder) {
        this.name = name;
        this.color = color;
        this.lexicographicOrder = lexicographicOrder;
    }

    public String getName() {

        return this.name;
    }

    public ChatColor getColor() {

        return this.color;
    }

    public char getLexicographicOrder() {
        return lexicographicOrder;
    }
}
