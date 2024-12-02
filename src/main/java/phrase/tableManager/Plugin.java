package phrase.tableManager;

import org.bukkit.plugin.java.JavaPlugin;
import phrase.tableManager.commands.TableManagerCMD;
import phrase.tableManager.commands.SbCMD;
import phrase.tableManager.listeners.Join;
import phrase.tableManager.scoreboard.AdventureScoreboard;
import phrase.tableManager.tab.AdventureTab;

public final class Plugin extends JavaPlugin {

    private static Plugin instance;

    @Override
    public void onEnable() {
        instance = this;

        new AdventureScoreboard();
        new AdventureTab();
        getServer().getPluginManager().registerEvents(new Join(), this);
        getCommand("sb").setExecutor(new SbCMD());
        getCommand("reload").setExecutor(new TableManagerCMD());
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public static Plugin getInstance() {
        return instance;
    }
}
