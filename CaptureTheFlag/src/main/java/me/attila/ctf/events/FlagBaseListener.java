package me.attila.ctf.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class FlagBaseListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock.getType() == Material.BEDROCK) {
            event.getPlayer().sendMessage("Bedrock clicked at X = " + clickedBlock.getX());
        }
    }
}