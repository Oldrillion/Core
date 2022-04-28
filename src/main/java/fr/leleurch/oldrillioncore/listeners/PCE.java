package fr.leleurch.oldrillioncore.listeners;

import fr.leleurch.oldrillioncore.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PCE implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        PlayerData pd = PlayerData.getPlayerData(p.getUniqueId());
        e.setFormat("§6OOC §8❘ " + pd.getRank().getPrefix() + p.getName() + "§f: " + e.getMessage().replaceAll("%", "%%"));
    }
}
