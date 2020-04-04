package me.attila.ctf.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.attila.ctf.logic.FlagBlockHandler;

public class AddFlag implements CommandExecutor {

    private FlagBlockHandler flagBlockHandler;

    public AddFlag(FlagBlockHandler flagBlockHandler) {
        this.flagBlockHandler = flagBlockHandler;
    }

    // This method is called, when somebody uses our command
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("addflag")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Block newFlagBlock = player.getTargetBlock(null, 100);
                int team_number = Integer.parseInt(args[0]);
                if (team_number == 0) {
                    this.flagBlockHandler.setFlagBlock(newFlagBlock.getX(), newFlagBlock.getY(), newFlagBlock.getZ(),
                            team_number);
                    player.sendMessage("Block set successfully as center flag");
                    return true;
                } else if (team_number > 0 && team_number <= 3) {
                    this.flagBlockHandler.setFlagBlock(newFlagBlock.getX(), newFlagBlock.getY(), newFlagBlock.getZ(),
                            team_number);
                    player.sendMessage("Block set successfully as flag base for team " + team_number);
                    return true;
                } else {
                    player.sendMessage(args[0] + " is no valid team number");
                    return false;
                }

            } else {
                sender.sendMessage("addFlag is only possible as Player");
                return false;
            }
        } else {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                player.sendMessage("du hesch " + label + "gschribe");
            }
        }
        return false;
    }
}