package com.matthew.plugin.core.commands;

import com.matthew.plugin.core.utils.MessageUtils;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class EffectCommand implements CommandExecutor {

    //todo add effects

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 1) {
                switch(args[0]) {
                    case "tornado":
                        drawTornado(player.getLocation(), 0f);
                        break;

                    case "circle":
                        drawCircle(player.getLocation(), 1f);
                        break;

                    default:
                        MessageUtils.sendCustomMessage(player, "Unknown effect");
                        player.sendMessage(ChatColor.BLUE + "   -" + ChatColor.GRAY + " Effect Types: " + ChatColor.YELLOW + "circle, tornado");
                        break;
                }

            } else {
                MessageUtils.incorrectUsage(player, "/effect (effect_type)");
                player.sendMessage(ChatColor.BLUE + "   -" + ChatColor.GRAY + " Effect Types: " + ChatColor.YELLOW + "circle, tornado");

            }
        }

        return false;
    }

    public void drawTornado(Location loc, float radius) {

        for(double t = 0; t<50; t+=0.5){
            float x = radius*(float)Math.sin(t);
            float z = radius*(float)Math.cos(t);
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FLAME, true,(float) loc.getX() + x, (float) loc.getY() + (float) t/5,(float) loc.getZ() + z, 0, 0, 0, 0, 1);
            for(Player player : Bukkit.getOnlinePlayers()){
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
            }
            radius = radius + (float) 0.05;
        }

    }
    public void drawCircle(Location loc, float radius) {

        for(double t = 0; t<50; t+=0.5){
            float x = radius*(float)Math.sin(t);
            float z = radius*(float)Math.cos(t);
            PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FLAME, true,(float) loc.getX() + x, (float) loc.getY(),(float) loc.getZ() + z, 0, 0, 0, 0, 1);
            for(Player player : Bukkit.getOnlinePlayers()){
                ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
            }

        }

    }
}
