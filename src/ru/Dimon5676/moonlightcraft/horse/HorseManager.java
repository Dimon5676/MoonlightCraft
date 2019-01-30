package ru.Dimon5676.moonlightcraft.horse;

import org.bukkit.ChatColor;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.Dimon5676.moonlightcraft.Main;

public class HorseManager implements Listener {

    @EventHandler
    public void onSaddle(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!(event.hasItem())) return;
        ItemStack item = event.getItem();
        if (!(item.hasItemMeta())) return;
        ItemMeta meta = item.getItemMeta();
        if (!(meta.hasDisplayName())) return;
        event.setCancelled(true);

        if (meta.getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "brown horse")) {
            BrownHorse horse = getHorseByOwnerName(player.getDisplayName());
            if (horse.isSpawned) {
                horse.hide();
            } else {
                horse.spawn();
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Horse)) return;
        event.setCancelled(true);
    }

    public BrownHorse getHorseByOwnerName(String name) {
        BrownHorse result = null;
        for (BrownHorse horse : Main.getPlugin(Main.class).horses) {
            if (horse.owner.getDisplayName().equalsIgnoreCase(name)) {
                result = horse;
            }
        }
        return result;
    }

}
