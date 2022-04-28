package fr.leleurch.oldrillioncore.utils.enums;

public class AllegeanceRanks {
    ;

    private Allegeance allegeance;
    private int rankId;
    private String modName;
    private String fullName;
    private int life;
    AllegeanceRanks(Allegeance allegeance, int rankId, String modName, String fullName, int life) {
        this.allegeance = allegeance;
        this.rankId = rankId;
        this.modName = modName;
        this.fullName = fullName;
        this.life = life;
    }
}
