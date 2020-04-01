package me.attila.ctf.events;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getSlot() == 39) {
            event.getWhoClicked().sendMessage("Du wechslisch ez gad din Helm");
            if (event.getCurrentItem().containsEnchantment(Enchantment.DAMAGE_UNDEAD)) {
                Material material = event.getCurrentItem().getType();
                if (material == Material.BLUE_BANNER || material == Material.GREEN_BANNER
                        || material == Material.RED_BANNER) {

                    event.setCancelled(true);
                    event.getWhoClicked().sendMessage(
                            "Sicher nöd eifach d Fahne vom Chopf wäg neh, sus gseht me jo gar nöd das du sie hesch, du Lano!");
                }
            }
        }

    }
}