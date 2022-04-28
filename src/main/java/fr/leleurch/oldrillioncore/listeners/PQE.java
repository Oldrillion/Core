package fr.leleurch.oldrillioncore.listeners;

import fr.leleurch.oldrillioncore.player.PlayerData;
import fr.leleurch.oldrillioncore.player.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PQE implements Listener {
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        PlayerData pd = PlayerData.getPlayerData(p.getUniqueId());
        e.setQuitMessage("§c- §8❘ " + pd.getRank().getPrefix() + p.getName() + " §fa quitté le serveur");
        new Thread(() -> {
            PlayerManager.storeAccount(pd);
        }).start();
        pd.unloadPerms();
        pd.removeFromMap();
    }
}
