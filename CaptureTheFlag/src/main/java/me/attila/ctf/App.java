package me.attila.ctf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.print.attribute.IntegerSyntax;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.attila.ctf.commands.AddFlag;
import me.attila.ctf.commands.SetTeam;
import me.attila.ctf.events.FlagBaseListener;
import me.attila.ctf.events.FlagBreakListener;
import me.attila.ctf.events.InventoryClickListener;
import me.attila.ctf.logic.FlagBlockHandler;
import me.attila.ctf.logic.GameHandler;
import me.attila.ctf.logic.PlayerHandler;

public class App extends JavaPlugin {

    private String[] flag_color_strings = { "Weiss", "Rot", "Blau", "Grüen" };

    @Override
    public void onEnable() {

        this.saveDefaultConfig();

        getLogger().info("Capture the Flag loaded");

        // create logic objects
        FlagBlockHandler flag_block_handler = new FlagBlockHandler(flag_color_strings, this);
        PlayerHandler player_handler = new PlayerHandler();
        GameHandler game_handler = new GameHandler(flag_color_strings);

        // create event listeners
        getServer().getPluginManager()
                .registerEvents(new FlagBaseListener(flag_block_handler, player_handler, game_handler), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new FlagBreakListener(flag_block_handler, player_handler), this);

        // register commands
        this.getCommand("addFlag").setExecutor(new AddFlag(flag_block_handler));
        this.getCommand("setTeam").setExecutor(new SetTeam(player_handler, flag_block_handler));

    }

    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}