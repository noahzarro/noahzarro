package me.attila.ctf.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.attila.ctf.logic.FlagBlockHandler;
import me.attila.ctf.logic.PlayerHandler;

/**
 * SetTeam
 */
public class SetTeam implements CommandExecutor {

    private PlayerHandler playerHandler;
    private FlagBlockHandler flagBlockHandler;

    public SetTeam(PlayerHandler playerHandler, FlagBlockHandler flagBlockHandler) {
        this.playerHandler = playerHandler;
        this.flagBlockHandler = flagBlockHandler;
    }

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("setTeam")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                int team_number = Integer.parseInt(args[0]);
                this.playerHandler.setTeam(team_number, player.getUniqueId());
                player.sendMessage("Du bisch ez team " + flagBlockHandler.getFlagColor(team_number)
                        + ", da isch imfall s lausigste");
                return true;
            } else {
                sender.sendMessage("setTeam is only possible as Player");
                return false;
            }
        }
        return false;
    }
}