package me.attila.ctf;

import org.bukkit.plugin.java.JavaPlugin;

import me.attila.ctf.events.FlagBaseListener;

public class App extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("Hello, SpigotMC!");
        getServer().getPluginManager().registerEvents(new FlagBaseListener(), this);
    }
    @Override
    public void onDisable() {
        getLogger().info("See you again, SpigotMC!");
    }
}