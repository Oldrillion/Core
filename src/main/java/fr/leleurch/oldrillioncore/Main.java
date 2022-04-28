package fr.leleurch.oldrillioncore;

import fr.leleurch.oldrillioncore.commands.RankCommand;
import fr.leleurch.oldrillioncore.connectors.SQLConnector;
import fr.leleurch.oldrillioncore.listeners.PCE;
import fr.leleurch.oldrillioncore.listeners.PJE;
import fr.leleurch.oldrillioncore.listeners.PQE;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main instance;
    public SQLConnector sqlConnector;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        System.out.println("[OldrillionCore] Infrastructure en cours de chargement");
        Bukkit.getPluginManager().registerEvents(new PJE(), this);
        Bukkit.getPluginManager().registerEvents(new PQE(), this);
        Bukkit.getPluginManager().registerEvents(new PCE(), this);
        this.getCommand("grade").setExecutor(new RankCommand());

        sqlConnector = new SQLConnector();
        sqlConnector.connectToBase();
    }

    @Override
    public void onDisable() {
        sqlConnector.disconnectBase();
    }
}
