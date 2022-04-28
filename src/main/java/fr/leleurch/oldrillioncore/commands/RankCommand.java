package fr.leleurch.oldrillioncore.commands;

import fr.leleurch.oldrillioncore.player.PlayerData;
import fr.leleurch.oldrillioncore.player.PlayerManager;
import fr.leleurch.oldrillioncore.utils.Alert;
import fr.leleurch.oldrillioncore.utils.enums.Ranks;
import fr.leleurch.oldrillioncore.utils.enums.SystemPrefixs;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;

public class RankCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;
        if(args.length == 0) {
            p.sendMessage(SystemPrefixs.ADM.getPrefix() + "Syntaxe: §c/" + label + " <joueur>");
            return false;
        }
        Player target = Bukkit.getPlayer(args[0]);
        if(target == null || !target.isOnline()) {
            p.sendMessage(SystemPrefixs.ADM.getPrefix() + "§cCe joueur est hors-ligne");
            return false;
        }
        if(args.length == 1) {
            p.sendMessage(SystemPrefixs.ADM.getPrefix() + "Définir un grade à §c" + target.getName() + "\n");
            p.sendMessage(" ");
            for(Ranks rank : Ranks.values()) {
                BaseComponent tc = new TextComponent("§a(Appliquer)");
                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aCliquez pour appliquer le grade "+rank.getPrefix()+"§aau joueur").create()));
                tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/grade " + target.getName() + " " + rank.name()));
                p.spigot().sendMessage(new TextComponent(rank.getPrefix() + target.getName() + "§8 ❘ "), tc);
            }
            return false;
        } else {
            try {
                Ranks rank = Ranks.valueOf(args[1]);

                PlayerData pd = PlayerData.getPlayerData(target.getUniqueId());
                pd.setRank(rank);
                p.sendMessage(SystemPrefixs.ADM.getPrefix() + "Le joueur §c" + target.getName() + "§f a reçu le grade " + rank.getPrefix());
                target.sendMessage(SystemPrefixs.ADM.getPrefix() + "Vous êtes désormais " + rank.getPrefix());
                pd.loadPerms();

                return true;
            } catch (IllegalArgumentException e) {
                p.sendMessage(SystemPrefixs.ADM.getPrefix() + "Syntaxe: §c/" + label + " <joueur>");
                return false;
            }
        }
    }
}
