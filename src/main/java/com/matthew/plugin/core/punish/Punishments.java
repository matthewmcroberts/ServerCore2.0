package com.matthew.plugin.core.punish;

public enum Punishments {

    HACKING("Hacking"),
    GAMEPLAY("Gameplay"),
    CHAT("Chat");

    private final String name;

    Punishments(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
