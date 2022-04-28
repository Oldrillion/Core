package fr.leleurch.oldrillioncore.utils.enums;

import lombok.Getter;

public enum SystemPrefixs {
    SYSTEM("§aSystème §7» §f"),
    STAFF("§eStaff §7» §f"),
    MOD("§2Modération §7» §f"),
    DEV("§6Développement §7» §f"),
    ADM("§cAdministration §7» §f"),
    PROMOTE("§bPromote §7» §f"),
    BUILD("§3Construction §7» §f"),

    PUBLIC_ALERT("§c( ! )§f "),
    PRIVATE_ALERT("§4( ! )§f "),

    JEDI("§9Jedi §7» §f"),
    SITH("§4Sith §7» §f"),
    CIVIL("§eCivil §7» §f");

    private @Getter String prefix;
    SystemPrefixs(String prefix) {
        this.prefix = prefix;
    }
}
