package phrase.tableManager.scoreboard;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import phrase.tableManager.Plugin;

import java.util.ArrayList;
import java.util.List;

public class AdventureScoreboard {

    private static final String name = ChatColor.translateAlternateColorCodes('&', Plugin.getInstance().getConfig().getString("settings.scoreboard.title"));
    private static final DisplaySlot displaySlot = DisplaySlot.SIDEBAR;
    public static final List<Player> players = new ArrayList<>();
    public static List<String> lines = new ArrayList<>();
    private static Scoreboard scoreboard;
    private static Objective objective;
    private static int blankLineNum = 0;

    public AdventureScoreboard() {

        long cooldownUpdates;
        try {
            cooldownUpdates = Plugin.getInstance().getConfig().getLong("settings.scoreboard.cooldownUpdates");
        } catch (NumberFormatException e) {
            Plugin.getInstance().getLogger().severe("В конфигурационном файле в каталоге settings параметр cooldownUpdates должен иметь long формат");
            cooldownUpdates = 20L;
            return;
        }

        lines = Plugin.getInstance().getConfig().getStringList("settings.scoreboard.lines");

        new BukkitRunnable() {

            @Override
            public void run() {

                init();

            }

        }.runTaskTimer(Plugin.getInstance(), 0L, cooldownUpdates);
    }

    private void init() {
        scoreboard = Plugin.getInstance().getServer().getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("tablemanager", "dummy", name);
        objective.setDisplaySlot(displaySlot);

        overrideScores();

        for(Player player : players) {
            addPlayer(player);
        }
    }

    private void overrideScores() {
        int i = lines.size();

        for(String line : lines) {
            i--;

            objective.getScore(ChatColor.translateAlternateColorCodes('&', line)).setScore(i);

        }

    }

    private String getCurrentBlank() {
        StringBuilder stringBuilder = new StringBuilder(" ");

        for(int i = 0; i < blankLineNum; i++) {

            stringBuilder.append(" ");

        }

        return stringBuilder.toString();
    }
/*
    public void setValue(String key, String value, boolean inLine, boolean blankLineBefore, boolean blankLineAfter) {
        if(blankLineBefore) {

            lines.add(getCurrentBlank());

        }

        if(inLine) {

            lines.add(key + value);

        } else {

            lines.add(key);
            lines.add(value);

        }

        if(blankLineAfter) {

            lines.add(getCurrentBlank());

        }

        init();
    }

 */

    public static void addPlayer(Player player) {
        player.setScoreboard(scoreboard);

        if(!players.contains(player))
            players.add(player);
    }

    public static void removePlayer(Player player) {
        player.setScoreboard(Plugin.getInstance().getServer().getScoreboardManager().getNewScoreboard());
        players.remove(player);
    }

}
