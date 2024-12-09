package phrase.tableManager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import phrase.tableManager.Plugin;
import phrase.tableManager.scoreboard.AdventureScoreboard;
import phrase.tableManager.utils.ChatUtil;

public class SbCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command,
                             String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            ChatUtil.sendMessage(commandSender, Plugin.getInstance().getConfig().getString("isAPlayer"));
            return true;
        }

        Player player = (Player) commandSender;

        if(AdventureScoreboard.players.contains(player)) {
            AdventureScoreboard.removePlayer(player);
            ChatUtil.sendMessage(player, Plugin.getInstance().getConfig().getString("message.disable"));
            return true;
        }

        AdventureScoreboard.addPlayer(player);
        ChatUtil.sendMessage(player, Plugin.getInstance().getConfig().getString("message.enable"));

        return true;
    }
}
