package fr.leleurch.oldrillioncore.utils;

import org.jetbrains.annotations.Nullable;
import fr.leleurch.oldrillioncore.utils.enums.SystemPrefixs;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Set;

public class Alert {
    private @Setter String message;
    private @Nullable Set<Player> notifiedPlayers;

    public Alert(String message, @Nullable Set<Player> notifiedPlayers) {
        this.message = message;
        this.notifiedPlayers = notifiedPlayers;
    }

    public void send() {
        if(notifiedPlayers == null) {
            Bukkit.broadcastMessage(SystemPrefixs.PUBLIC_ALERT.getPrefix() + message);
        } else {
            for(Player p : notifiedPlayers) {
                p.sendMessage(SystemPrefixs.PRIVATE_ALERT.getPrefix() + message);
            }
        }
    }
}
