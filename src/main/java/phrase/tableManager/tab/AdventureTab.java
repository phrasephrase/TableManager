package phrase.tableManager.tab;

import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;
import phrase.tableManager.Plugin;

import java.util.ArrayList;
import java.util.List;

public class AdventureTab {

    private static List<String> linesHeader = new ArrayList<>();
    private static List<String> linesFooter = new ArrayList<>();

    public AdventureTab() {

        long cooldownUpdates;
        try {
            cooldownUpdates = Plugin.getInstance().getConfig().getLong("settings.scoreboard.cooldownUpdates");
        } catch (NumberFormatException e) {
            Plugin.getInstance().getLogger().severe("В конфигурационном файле в каталоге settings параметр cooldownUpdates должен иметь long формат");
            cooldownUpdates = 20L;
            return;
        }

        linesHeader = Plugin.getInstance().getConfig().getStringList("settings.scoreboard.linesHeader");
        linesFooter = Plugin.getInstance().getConfig().getStringList("settings.scoreboard.linesFooter");


        new BukkitRunnable() {

            @Override
            public void run() {

                init();

            }
        }.runTaskTimer(Plugin.getInstance(), 0L, cooldownUpdates);

    }

    private void init() {
        StringBuilder tabListHeader = new StringBuilder();
        StringBuilder tabListFooter = new StringBuilder();

        for(String line : linesHeader) {

            tabListHeader.append(ChatColor.translateAlternateColorCodes('&', line) + "\n");

        }

        for(String line : linesFooter) {

            tabListFooter.append(ChatColor.translateAlternateColorCodes('&', line) + "\n");

        }
    }
}
