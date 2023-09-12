package com.matthew.plugin.core.punish.apis.types;

import com.matthew.plugin.core.ServerCore;
import com.matthew.plugin.core.punish.Punishments;
import com.matthew.plugin.core.punish.apis.dbquerys.ActivePunishments;
import com.matthew.plugin.core.utils.MessageUtils;
import com.matthew.plugin.core.utils.PunishUtils;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PunishRemove {

    public static void removePunishment(Player sender, OfflinePlayer target, Punishments type) throws SQLException {
        if(ActivePunishments.getAmount(target) != 0) {
            if (type == Punishments.HACKING || type == Punishments.CHAT || type == Punishments.GAMEPLAY) {
                System.out.println(type.getName());
                int id = ActivePunishments.getPunishmentId(target, type);
                if(id != 0) {
                    PreparedStatement ps1 = ServerCore.preparedStatement("INSERT INTO expired_punishments SELECT ID, UUID, TYPE, SEV, REASON, ISSUED, EXPIRATION, STAFF, ACTIVE, PERMANENT FROM active_punishments WHERE ID = ?");
                    ps1.setInt(1, id);
                    ps1.execute();

                    PreparedStatement ps2 = ServerCore.preparedStatement("UPDATE expired_punishments SET ACTIVE=0 WHERE ID = ?");
                    ps2.setInt(1, id);
                    ps2.execute();

                    PreparedStatement ps3 = ServerCore.preparedStatement("DELETE FROM active_punishments WHERE ID = ?");
                    ps3.setInt(1, id);
                    ps3.execute();

                    PunishUtils.sendRemoveMessages(sender, target, type);
                } else {
                    MessageUtils.sendCustomMessage(sender, ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " has" +ChatColor.YELLOW + " 0" + ChatColor.GRAY + " active punishments found for type: " + ChatColor.YELLOW + type.getName());
                }
            } else {
                MessageUtils.sendCustomMessage(sender, "Invalid type. Valid punishment types are:");
                MessageUtils.addToList(sender, "Hacking, Chat, Gameplay");
            }
        } else {
            MessageUtils.sendCustomMessage(sender, ChatColor.YELLOW + target.getName() + ChatColor.GRAY + " has" +ChatColor.YELLOW + " 0" + ChatColor.GRAY + " active punishments found for type: " + ChatColor.YELLOW + type.getName());
        }
    }

}
