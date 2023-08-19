package com.matthew.plugin.core.punish.events;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.apis.types.PunishBan;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Calendar;

public class PunishListener implements Listener {

    @SuppressWarnings("DuplicateBranchesInSwitch")
    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        Calendar cal = Calendar.getInstance();

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).contains(ChatColor.GOLD + "Punish - ") && e.getCurrentItem() != null) {
            e.setCancelled(true);
            if (Bukkit.getOfflinePlayer(e.getView().getTitle().substring(11)) != null) {
                OfflinePlayer target = Bukkit.getOfflinePlayer(e.getView().getTitle().substring(11));

                switch (e.getRawSlot()) {
                    case 20: //mute sev 1
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;
                    case 29: //mute sev 2
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;
                    case 38: //mute sev 3
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;
                    case 22: //gameplay ban sev 1
                        cal.add(Calendar.HOUR, 1);

                        PunishBan.tempBan(player, target, cal, ServerCore.punishReason.get(player));
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;
                    case 31: //gameplay ban sev 2
                    case 24: //hacking ban sev 1
                        cal.add(Calendar.HOUR, 12);

                        PunishBan.tempBan(player, target, cal, ServerCore.punishReason.get(player));
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;
                    case 40: //gameplay ban sev 3
                        cal.add(Calendar.DAY_OF_WEEK, 1);

                        PunishBan.tempBan(player, target, cal, ServerCore.punishReason.get(player));
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;

                    case 33: //hacking ban sev 2
                        cal.add(Calendar.DAY_OF_WEEK, 7);

                        PunishBan.tempBan(player, target, cal, ServerCore.punishReason.get(player));
                        player.closeInventory();
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;
                    case 42: //hacking ban sev 3
                        cal.add(Calendar.MONTH, 1);

                        PunishBan.tempBan(player, target, cal, ServerCore.punishReason.get(player));
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;
                    case 27: //perm mute
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;
                    case 36: //perm ban
                        PunishBan.permBan(player, target, ServerCore.punishReason.get(player));
                        player.closeInventory();
                        ServerCore.punishReason.remove(player);

                        break;
                    default:
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).contains(ChatColor.GOLD + "Punish - ")) {
            ServerCore.punishReason.remove(e.getPlayer());
        }
    }

   /* @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!e.getPlayer().hasPlayedBefore()) {
            try {
                ResultSet rs = ServerCore.getInstance().preparedStatement("SELECT COUNT(UUID) FROM player_punishment WHERE UUID = '" + player.getUniqueId().toString() + "';").executeQuery();
                rs.next();
                if (rs.getInt(1) == 0) { //Meaning 0 records match the players UUID, so player doesn't exist in the Database
                    ServerCore.getInstance().preparedStatement("INSERT INTO player_punishment(UUID, MUTED, PERM, TEMP) VALUES ('" + player.getUniqueId() + "'," + "DEFAULT, DEFAULT, DEFAULT);").executeUpdate();

                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    */
}
