package me.attila.ctf.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.attila.ctf.logic.PlayerHandler;

/**
 * SetTeam
 */
public class SetTeam implements CommandExecutor {

    private PlayerHandler playerHandler;

    public SetTeam(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("setTeam")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                this.playerHandler.setTeam(Integer.parseInt(args[0]), player.getUniqueId());
            } else {
                sender.sendMessage("setTeam is only possible as Player");
                return false;
            }
        }
        return false;
    }
}