package fr.leleurch.oldrillioncore.character;

import fr.leleurch.oldrillioncore.utils.enums.Allegeance;
import fr.leleurch.oldrillioncore.utils.enums.AllegeanceRanks;
import lombok.Data;
import org.bukkit.inventory.Inventory;

@Data
public class Character {
    private String name;
    private MCSkin skin;
    private Allegeance allegeance;
    private AllegeanceRanks allegeanceRank;
    private Inventory inventory;
}
