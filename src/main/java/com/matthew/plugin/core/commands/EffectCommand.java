package com.matthew.plugin.core.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EffectCommand implements CommandExecutor {

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

                    case "cylinder":
                        cylinder(player.getLocation(), Material.STONE, 12);

                    default:
                        player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Unknown effect");
                        player.sendMessage(ChatColor.BLUE + "   -" + ChatColor.GRAY + " Effect Types: " + ChatColor.YELLOW + "circle, tornado");
                        break;
                }

            } else {
                player.sendMessage(ChatColor.BLUE + ">> " + ChatColor.GRAY + "Please use " + ChatColor.GOLD + "/effect (effect_type)");
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

    public void cylinder(Location loc, Material mat, int r) {
        int cx = loc.getBlockX();
        int cy = loc.getBlockY();
        int cz = loc.getBlockZ();
        World w = loc.getWorld();
        int rSquared = r * r;
        for (int x = cx - r; x <= cx +r; x++) {
            for (int z = cz - r; z <= cz +r; z++) {
                if ((cx - x) * (cx - x) + (cz - z) * (cz - z) <= rSquared) {
                    w.getBlockAt(x, cy, z).setType(mat);
                }
            }
        }
    }
}
