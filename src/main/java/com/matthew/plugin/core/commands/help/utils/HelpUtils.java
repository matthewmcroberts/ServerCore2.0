package com.matthew.plugin.core.commands.help.utils;

import com.matthew.plugin.core.utils.MessageUtils;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class HelpUtils {

    public static void adminHelp(Player player) throws SQLException {
        MessageUtils.helpMessageHeader(player);

        TextComponent gamemode = new TextComponent("        §9- §eGameMode");
        gamemode.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gm usage"));
        player.spigot().sendMessage(gamemode);

        TextComponent inventory = new TextComponent("        §9- §eInventory");
        inventory.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/inv usage"));
        player.spigot().sendMessage(inventory);

        TextComponent msg = new TextComponent("        §9- §eMessage");
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/message usage"));
        player.spigot().sendMessage(msg);

        TextComponent reply = new TextComponent("        §9- §eReply");
        reply.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reply usage"));
        player.spigot().sendMessage(reply);

        TextComponent silence = new TextComponent("        §9- §eSilence");
        silence.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/silence usage"));
        player.spigot().sendMessage(silence);

        TextComponent teleport = new TextComponent("        §9- §eTeleport");
        teleport.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp usage"));
        player.spigot().sendMessage(teleport);

        TextComponent vanish = new TextComponent("        §9- §eVanish");
        vanish.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/v usage"));
        player.spigot().sendMessage(vanish);

        TextComponent punish = new TextComponent("        §9- §ePunish");
        punish.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p usage"));
        player.spigot().sendMessage(punish);

        TextComponent setrank = new TextComponent("        §9- §eSet Rank");
        setrank.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/setrank usage"));
        player.spigot().sendMessage(setrank);

        TextComponent adminHelp = new TextComponent("        §9- §eAdmin Help");
        adminHelp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/a usage"));
        player.spigot().sendMessage(adminHelp);

        TextComponent adminHelpReply = new TextComponent("        §9- §eAdmin Help Reply");
        adminHelpReply.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ma usage"));
        player.spigot().sendMessage(adminHelpReply);

        TextComponent give = new TextComponent("        §9- §eGive Item");
        give.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/give usage"));
        player.spigot().sendMessage(give);

        TextComponent effect = new TextComponent("        §9- §eEffect");
        effect.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/effect usage"));
        player.spigot().sendMessage(effect);

        TextComponent heal = new TextComponent("        §9- §eHeal");
        heal.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/heal usage"));
        player.spigot().sendMessage(heal);

        MessageUtils.helpMessageFooter(player);
    }

    public static void srModHelp(Player player) throws SQLException {
        MessageUtils.helpMessageHeader(player);

        TextComponent inventory = new TextComponent("        §9- §eInventory");
        inventory.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/inv usage"));
        player.spigot().sendMessage(inventory);

        TextComponent msg = new TextComponent("        §9- §eMessage");
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/message usage"));
        player.spigot().sendMessage(msg);

        TextComponent reply = new TextComponent("        §9- §eReply");
        reply.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reply usage"));
        player.spigot().sendMessage(reply);

        TextComponent teleport = new TextComponent("        §9- §eTeleport");
        teleport.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp usage"));
        player.spigot().sendMessage(teleport);

        TextComponent vanish = new TextComponent("        §9- §eVanish");
        vanish.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/v usage"));
        player.spigot().sendMessage(vanish);

        TextComponent punish = new TextComponent("        §9- §ePunish");
        punish.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p usage"));
        player.spigot().sendMessage(punish);

        TextComponent adminHelp = new TextComponent("        §9- §eAdmin Help");
        adminHelp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/a usage"));
        player.spigot().sendMessage(adminHelp);

        TextComponent adminHelpReply = new TextComponent("        §9- §eAdmin Help Reply");
        adminHelpReply.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ma usage"));
        player.spigot().sendMessage(adminHelpReply);

        TextComponent give = new TextComponent("        §9- §eGive Item");
        give.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/give usage"));
        player.spigot().sendMessage(give);

        TextComponent effect = new TextComponent("        §9- §eEffect");
        effect.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/effect usage"));
        player.spigot().sendMessage(effect);

        TextComponent heal = new TextComponent("        §9- §eHeal");
        heal.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/heal usage"));
        player.spigot().sendMessage(heal);

        MessageUtils.helpMessageFooter(player);
    }

    public static void modHelp(Player player) throws SQLException {
        MessageUtils.helpMessageHeader(player);

        TextComponent inventory = new TextComponent("        §9- §eInventory");
        inventory.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/inv usage"));
        player.spigot().sendMessage(inventory);

        TextComponent msg = new TextComponent("        §9- §eMessage");
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/message usage"));
        player.spigot().sendMessage(msg);

        TextComponent reply = new TextComponent("        §9- §eReply");
        reply.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reply usage"));
        player.spigot().sendMessage(reply);

        TextComponent teleport = new TextComponent("        §9- §eTeleport");
        teleport.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp usage"));
        player.spigot().sendMessage(teleport);

        TextComponent vanish = new TextComponent("        §9- §eVanish");
        vanish.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/v usage"));
        player.spigot().sendMessage(vanish);

        TextComponent punish = new TextComponent("        §9- §ePunish");
        punish.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p usage"));
        player.spigot().sendMessage(punish);

        TextComponent adminHelp = new TextComponent("        §9- §eAdmin Help");
        adminHelp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/a usage"));
        player.spigot().sendMessage(adminHelp);

        TextComponent adminHelpReply = new TextComponent("        §9- §eAdmin Help Reply");
        adminHelpReply.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ma usage"));
        player.spigot().sendMessage(adminHelpReply);

        TextComponent effect = new TextComponent("        §9- §eEffect");
        effect.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/effect usage"));
        player.spigot().sendMessage(effect);

        TextComponent heal = new TextComponent("        §9- §eHeal");
        heal.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/heal usage"));
        player.spigot().sendMessage(heal);

        MessageUtils.helpMessageFooter(player);
    }

    public static void jrModHelp(Player player) throws SQLException {
        MessageUtils.helpMessageHeader(player);

        TextComponent msg = new TextComponent("        §9- §eMessage");
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/message usage"));
        player.spigot().sendMessage(msg);

        TextComponent reply = new TextComponent("        §9- §eReply");
        reply.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reply usage"));
        player.spigot().sendMessage(reply);

        TextComponent teleport = new TextComponent("        §9- §eTeleport");
        teleport.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp usage"));
        player.spigot().sendMessage(teleport);

        TextComponent vanish = new TextComponent("        §9- §eVanish");
        vanish.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/v usage"));
        player.spigot().sendMessage(vanish);

        TextComponent adminHelp = new TextComponent("        §9- §eAdmin Help");
        adminHelp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/a usage"));
        player.spigot().sendMessage(adminHelp);

        TextComponent adminHelpReply = new TextComponent("        §9- §eAdmin Help Reply");
        adminHelpReply.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/ma usage"));
        player.spigot().sendMessage(adminHelpReply);

        TextComponent effect = new TextComponent("        §9- §eEffect");
        effect.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/effect usage"));
        player.spigot().sendMessage(effect);

        MessageUtils.helpMessageFooter(player);
    }

    public static void nonStaffHelp(Player player) throws SQLException {
        MessageUtils.helpMessageHeader(player);

        TextComponent msg = new TextComponent("        §9- §eMessage");
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/message usage"));
        player.spigot().sendMessage(msg);

        TextComponent reply = new TextComponent("        §9- §eReply");
        reply.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/reply usage"));
        player.spigot().sendMessage(reply);

        TextComponent adminHelp = new TextComponent("        §9- §eAdmin Help");
        adminHelp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/a usage"));
        player.spigot().sendMessage(adminHelp);


        MessageUtils.helpMessageFooter(player);
    }
}
