package com.matthew.plugin.core;

import com.matthew.plugin.core.commands.GiveCommand;
import com.matthew.plugin.core.commands.HealCommand;
import com.matthew.plugin.core.commands.adminhelp.AdminHelpCommand;
import com.matthew.plugin.core.commands.adminhelp.AdminHelpReplyCommand;
import com.matthew.plugin.core.commands.gamemode.command.GamemodeCommand;
import com.matthew.plugin.core.commands.gamemode.events.GamemodeListener;
import com.matthew.plugin.core.commands.help.commands.HelpCommand;
import com.matthew.plugin.core.commands.inventory.commands.InventoryCommand;
import com.matthew.plugin.core.commands.inventory.events.InventoryListener;
import com.matthew.plugin.core.commands.playermsg.MessageCommand;
import com.matthew.plugin.core.commands.playermsg.ReplyCommand;
import com.matthew.plugin.core.commands.silence.commands.SilenceCommand;
import com.matthew.plugin.core.commands.silence.events.SilenceListener;
import com.matthew.plugin.core.commands.teleport.TeleportCommand;
import com.matthew.plugin.core.commands.vanish.commands.VanishCommand;
import com.matthew.plugin.core.commands.vanish.events.NonVanishedPlayerJoinEvent;
import com.matthew.plugin.core.commands.vanish.events.VanishedPlayerJoinEvent;
import com.matthew.plugin.core.commands.vanish.events.VanishedPlayerQuitEvent;
import com.matthew.plugin.core.events.PlayerChatListener;
import com.matthew.plugin.core.events.PlayerJoinLeaveListener;
import com.matthew.plugin.core.events.ServerListPingListener;
import com.matthew.plugin.core.events.disables.BuildingListener;
import com.matthew.plugin.core.events.disables.FallDamageListener;
import com.matthew.plugin.core.events.disables.WeatherChangeListener;
import com.matthew.plugin.core.nametags.events.NametagsListener;
import com.matthew.plugin.core.punish.commands.PunishCommand;
import com.matthew.plugin.core.punish.events.PunishListener;
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
    public static HashMap<Player, String> punishReason = new HashMap<>();


    private static Connection connection;

    private static ServerCore instance;


    public void onEnable() {

        instance = this;

        runConstructors();
        registerCommands();
        registerListeners();

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "\n\nServerCore has been enabled\n");

        try {
            openConnection();
            getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Connected to database");
        } catch (SQLException x) {
            getServer().getConsoleSender().sendMessage(ChatColor.RED + "Failed to connect to database");
        }

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
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("give").setExecutor(new GiveCommand());
        getCommand("inventory").setExecutor(new InventoryCommand());
        getCommand("silence").setExecutor(new SilenceCommand());
        getCommand("help").setExecutor(new HelpCommand());
        getCommand("punish").setExecutor(new PunishCommand());

    }

    public void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new RanksListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
        Bukkit.getPluginManager().registerEvents(new GamemodeListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinLeaveListener(), this);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), this);
        //Bukkit.getPluginManager().registerEvents(new OutOfBoundsListener(), this);
        Bukkit.getPluginManager().registerEvents(new VanishedPlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new NonVanishedPlayerJoinEvent(), this);
        Bukkit.getPluginManager().registerEvents(new VanishedPlayerQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
        Bukkit.getPluginManager().registerEvents(new SilenceListener(), this);
        Bukkit.getPluginManager().registerEvents(new NametagsListener(), this);
        Bukkit.getPluginManager().registerEvents(new BuildingListener(), this);
        Bukkit.getPluginManager().registerEvents(new FallDamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(), this);
        Bukkit.getPluginManager().registerEvents(new PunishListener(), this);

    }

    public void runConstructors() {

    }

    private void openConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return;
        }
        String database = "mcserver";
        String host = "localhost";
        String username = "root";
        String password = "root";
        int port = 3306;
        connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
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
