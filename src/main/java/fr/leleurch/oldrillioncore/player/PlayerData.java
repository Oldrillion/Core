package fr.leleurch.oldrillioncore.player;

import fr.leleurch.oldrillioncore.Main;
import fr.leleurch.oldrillioncore.utils.enums.Ranks;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerData {
    public static Map<UUID, PlayerData> playerDataMap = new ConcurrentHashMap<>();

    private @Getter Player player;
    private @Getter @Setter Ranks rank;
    private @Getter @Setter Set<Character> characters;
    private @Getter @Setter int characterLimit;
    private @Getter @Setter PermissionAttachment permissionAttachment;
    private @Setter @Getter UUID uuid;

    public void setPlayer(Player player) {
        this.player = player;
        permissionAttachment = player.addAttachment(Main.instance);
    }
    public void loadPerms() {
        for (String perm : rank.getPermissions()) {
            permissionAttachment.unsetPermission(perm);
            permissionAttachment.setPermission(perm, true);
        }
    }
    public void unloadPerms() {
        for (String perm : permissionAttachment.getPermissions().keySet()) {
            if(permissionAttachment.getPermissions().get(perm)) {
                permissionAttachment.unsetPermission(perm);
            }
        }
    }

    public void putInMap() {
        playerDataMap.put(uuid, this);
    }
    public void removeFromMap() {
        playerDataMap.remove(player.getUniqueId());
    }

    public static PlayerData getPlayerData(UUID player) {
        return playerDataMap.get(player);
    }
}
