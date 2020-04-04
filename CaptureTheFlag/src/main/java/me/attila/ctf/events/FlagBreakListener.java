package me.attila.ctf.events;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import me.attila.ctf.logic.FlagBlockHandler;
import me.attila.ctf.logic.PlayerHandler;

/**
 * FlagBreakEvent
 */
public class FlagBreakListener implements Listener {

    private FlagBlockHandler flagBlockHandler;
    private PlayerHandler playerHandler;

    public FlagBreakListener(FlagBlockHandler flagBlockHandler, PlayerHandler playerHandler) {
        this.flagBlockHandler = flagBlockHandler;
        this.playerHandler = playerHandler;
    }

    @EventHandler
    public void onBlockBreak​(BlockBreakEvent event) {
        // * check if it is a flag
        int flag_team = this.flagBlockHandler.getTeamNumber(event.getBlock().getX(), event.getBlock().getY() - 1,
                event.getBlock().getZ());
        if (flag_team != -1) {
            event.setCancelled(true);
            int player_team = this.playerHandler.getTeam(event.getPlayer().getUniqueId());
            if (player_team == -1) {
                event.getPlayer().sendMessage("Du bisch no i keim team");
            } else if (player_team == flag_team) {
                event.getPlayer().sendMessage("Da isch dini eiget flagge du volltrottel");
            } else {
                // give flag to player
                this.flagBlockHandler.pickUpFlag(flag_team);
                ItemStack old_helmet = event.getPlayer().getInventory().getHelmet();
                if (old_helmet != null) {
                    event.getPlayer().sendMessage("Du hesch kein helm agha");
                    event.getPlayer().getInventory().addItem(old_helmet);
                }
                ItemStack new_helmet = new ItemStack(this.flagBlockHandler.getFlagMaterial(flag_team));
                new_helmet.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);
                event.getPlayer().getInventory().setHelmet(new_helmet);
                Bukkit.getServer().broadcastMessage("D flagge vom team " + this.flagBlockHandler.getFlagColor(flag_team)
                        + " isch vom team " + this.flagBlockHandler.getFlagColor(player_team) + " gkluppet worde");
            }

        }

        // check if it is the flag stand
        int flag_stand_number = this.flagBlockHandler.getTeamNumber(event.getBlock().getX(), event.getBlock().getY(), event.getBlock().getZ());
        if (flag_stand_number != -1){
            event.setCancelled(true);
            event.getPlayer().sendMessage("Du bisch doch en abartige vollasi. Es wird sicher nöd de flaggeblock abbaue!");
        }
    }
}