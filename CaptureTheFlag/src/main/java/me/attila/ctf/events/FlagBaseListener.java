package me.attila.ctf.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.attila.ctf.logic.FlagBlockHandler;
import me.attila.ctf.logic.GameHandler;
import me.attila.ctf.logic.PlayerHandler;

public class FlagBaseListener implements Listener {

    private FlagBlockHandler flagBlockHandler;
    private PlayerHandler playerHandler;
    private GameHandler game_handler;

    public FlagBaseListener(FlagBlockHandler flagBlockHandler, PlayerHandler playerHandler, GameHandler game_handler) {
        this.flagBlockHandler = flagBlockHandler;
        this.playerHandler = playerHandler;
        this.game_handler = game_handler;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Block clickedBlock = event.getClickedBlock();
        if (event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        // it was no block
        if (clickedBlock == null) {
            return;
        }

        // get team number of base
        int team_number = flagBlockHandler.getTeamNumber(clickedBlock.getX(), clickedBlock.getY(), clickedBlock.getZ());

        // if center check if player has correct item
        if (team_number == 0) {
            int flag_team_number = 0;
            // try all flag colors
            for (int i = 0; i < 4; i++) {
                ItemStack helmet = event.getPlayer().getInventory().getHelmet();
                // check if it is the right flag
                if (helmet.getType() == this.flagBlockHandler.getFlagMaterial(i)) {

                    // check if it is enchanted
                    if (helmet.containsEnchantment(Enchantment.DAMAGE_UNDEAD)) {
                        flag_team_number = i;
                        break;
                    }
                }
            }

            int player_team = playerHandler.getTeam(event.getPlayer().getUniqueId());

            // if it is an actual flag
            if (flag_team_number != 0) {
                event.getPlayer().getInventory().setHelmet(new ItemStack(Material.AIR));
                Bukkit.getServer()
                        .broadcastMessage("D flagge vom team " + this.flagBlockHandler.getFlagColor(flag_team_number)
                                + " isch vom team " + this.flagBlockHandler.getFlagColor(player_team) + " abgeh worde");

                Bukkit.getServer().broadcastMessage(
                        "Team " + this.flagBlockHandler.getFlagColor(flag_team_number) + " isch jo mega lausig!");

                game_handler.removePoint(flag_team_number);
                game_handler.addPoint(player_team);
                game_handler.broadcastGameStats();
            }
        }
    }
}
