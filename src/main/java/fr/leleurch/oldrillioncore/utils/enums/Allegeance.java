package fr.leleurch.oldrillioncore.utils.enums;

import lombok.Getter;

public enum Allegeance {
    SITH("§7[§cSith§7] §c"), JEDI("§7[§9Jedi§7] §9"), CIVIL("§7[§eCivil§7] §e");

    private @Getter String prefix;
    Allegeance(String prefix) {
        this.prefix = prefix;
    }
}
