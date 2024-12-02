package phrase.tableManager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import phrase.tableManager.Plugin;
import phrase.tableManager.utils.ChatUtil;

public class TableManagerCMD implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command,
                             String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            ChatUtil.sendMessage(commandSender, Plugin.getInstance().getConfig().getString("isAPlayer"));
            return true;
        }

        Player player = (Player) commandSender;

        if(strings.length < 1) {
            ChatUtil.sendMessage(player, Plugin.getInstance().getConfig().getString("usage"));
            return true;
        }

        if(strings[0].equalsIgnoreCase("reload")) {
            Plugin.getInstance().onDisable();
            Plugin.getInstance().onEnable();
            Plugin.getInstance().reloadConfig();

            ChatUtil.sendMessage(player, Plugin.getInstance().getConfig().getString("reload"));
        }

        return true;
    }
}
