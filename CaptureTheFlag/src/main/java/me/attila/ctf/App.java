package me.attila.ctf;

import org.bukkit.plugin.java.JavaPlugin;

import me.attila.ctf.commands.AddFlag;
import me.attila.ctf.commands.SetTeam;
import me.attila.ctf.events.FlagBaseListener;
import me.attila.ctf.events.FlagBreakListener;
import me.attila.ctf.events.InventoryClickListener;
import me.attila.ctf.logic.FlagBlockHandler;
import me.attila.ctf.logic.PlayerHandler;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
        FlagBlockHandler flag_block_handler = new FlagBlockHandler();
        PlayerHandler player_handler = new PlayerHandler();
        getServer().getPluginManager().registerEvents(new FlagBaseListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new FlagBreakListener(flag_block_handler, player_handler), this);
        this.getCommand("addFlag").setExecutor(new AddFlag(flag_block_handler));
        this.getCommand("setTeam").setExecutor(new SetTeam(player_handler));

    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}