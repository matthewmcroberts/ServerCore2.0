package com.matthew.plugin.core;

import com.matthew.plugin.core.commands.HealCommand;
import com.matthew.plugin.core.commands.adminhelp.AdminHelpCommand;
import com.matthew.plugin.core.commands.adminhelp.AdminHelpReplyCommand;
import com.matthew.plugin.core.commands.gamemode.command.GamemodeCommand;
import com.matthew.plugin.core.commands.gamemode.events.GamemodeListener;
import com.matthew.plugin.core.commands.playermsg.MessageCommand;
import com.matthew.plugin.core.commands.playermsg.ReplyCommand;
import com.matthew.plugin.core.commands.playermsg.events.MessagesListener;
import com.matthew.plugin.core.commands.teleport.TeleportCommand;
import com.matthew.plugin.core.events.OutOfBoundsListener;
import com.matthew.plugin.core.events.PlayerChatListener;
import com.matthew.plugin.core.events.PlayerJoinLeaveListener;
import com.matthew.plugin.core.events.ServerListPingListener;
import com.matthew.plugin.core.ranks.commands.RankCommand;
import com.matthew.plugin.core.ranks.events.RanksListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;


public final class ServerCore extends JavaPlugin {

    public static HashMap<Player, Player> recentlyMessaged = new HashMap<>();

    private static Connection connection;
    private final String host = "localhost";
    private final String database = "testserver";
    private final String username = "root";
    private final String password = "";
    private final int port = 3306;

    private static ServerCore instance;


    public void onEnable() {

        instance = this;

        try {
            openConnection();
            System.out.println("Connected to database");
        } catch (SQLException x) {
            System.out.println("Failed to connect to mysql database");
        }

        registerCommands();
        registerListeners();
        runConstructors();

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nServerCore has been enabled\n");

    }

    public void onDisable() {

    }

    public void registerCommands() {
        getCommand("setrank").setExecutor(new RankCommand());
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("a").setExecutor(new AdminHelpCommand());
        getCommand("ma").setExecutor(new AdminHelpReplyCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("reply").setExecutor(new ReplyCommand());
        getCommand("tp").setExecutor(new TeleportCommand());
    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new RanksListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new GamemodeListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinLeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), this);
        Bukkit.getPluginManager().registerEvents(new OutOfBoundsListener(), this);
        Bukkit.getPluginManager().registerEvents(new MessagesListener(), this);
    }

    public void runConstructors() {

    }

    private void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password);
    }

    public static PreparedStatement preparedStatement(String query) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static ServerCore getInstance() {
        return instance;
    }



}
