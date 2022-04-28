package fr.leleurch.oldrillioncore.utils.enums;

import lombok.Getter;

public enum Ranks {
    FONDATEUR("§4§lFondateur §4", true, 9, new String[]{"default.use", "modo.use", "premium.use", "builder.use", "animateur.use", "responsable.use", "dev.use", "admin.use"}),
    ADMIN("§4Administrateur §4", true, 8, new String[]{"default.use", "modo.use", "premium.use", "builder.use", "animateur.use", "responsable.use", "dev.use", "admin.use"}),
    DEV("§cDéveloppeur §c", true, 7, new String[]{"default.use", "modo.use", "premium.use", "builder.use", "animateur.use", "responsable.use", "dev.use", "admin.use"}),
    RESP("§5Responsable §5", true, 6, new String[]{"default.use", "modo.use", "premium.use", "builder.use", "animateur.use", "responsable.use"}),
    BUILDER("§3Builder §3", true, 5, new String[]{"default.use", "builder.use"}),
    ANIMATEUR("§bAnimateur §b", true, 4, new String[]{"default.use", "animateur.use"}),
    MODO("§2Modérateur §2", true, 3, new String[]{"default.use", "modo.use"}),

    PREMIUM("§ePremium §e", false, 2, new String[]{"default.use", "premium.use"}),
    JOUEUR("§7Joueur §7", false, 1, new String[]{"default.use"});


    private @Getter String prefix;
    private @Getter boolean staff;
    private @Getter int rankLVL;
    private @Getter String[] permissions;
    Ranks(String prefix, boolean staff, int rankLVL, String[] permissions) {
        this.prefix = prefix;
        this.staff = staff;
        this.rankLVL = rankLVL;
        this.permissions = permissions;
    }
}
