package fr.leleurch.oldrillioncore.listeners;

import fr.leleurch.oldrillioncore.player.PlayerData;
import fr.leleurch.oldrillioncore.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PJE implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PlayerData pd = PlayerData.getPlayerData(p.getUniqueId());
        pd.setPlayer(p);
        pd.loadPerms();
        e.setJoinMessage("§a+ §8❘ " + pd.getRank().getPrefix() + p.getName() + " §fa rejoint le serveur");
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent e) {
        UUID u = e.getUniqueId();
        new Thread(() -> {
            PlayerData pd = PlayerManager.loadAccount(u);

            if (pd != null) {
                pd.setUuid(u);
                pd.putInMap();
            } else {
                e.setKickMessage("Une erreur de base de donnée est survenue.");
            }
        }).start();
    }
}
