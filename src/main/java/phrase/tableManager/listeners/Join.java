package phrase.tableManager.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import phrase.tableManager.scoreboard.AdventureScoreboard;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        AdventureScoreboard.addPlayer(event.getPlayer());
    }

}
