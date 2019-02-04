package ru.Dimon5676.moonlightcraft.horse;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import ru.Dimon5676.moonlightcraft.Main;

import java.util.HashMap;
import java.util.List;

public class HorseManager implements Listener {

    private Main plugin;
    private static HashMap<String, Horse> spawnedHorses = new HashMap<>();

    public HorseManager(Main plugin) {
        this.plugin = plugin;
    }

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
            Horse horse;
            ItemMeta saddle = event.getItem().getItemMeta();
            List<String> lore = saddle.getLore();
            if (lore.get(lore.size()-1).equalsIgnoreCase(ChatColor.RED + "spawned")) {
                horse = spawnedHorses.get(player.getDisplayName());
                spawnedHorses.remove(player.getDisplayName());
                horse.remove();
                lore.remove(lore.size()-1);
                meta.setLore(lore);
                item.setItemMeta(meta);
            } else {
                horse = player.getWorld().spawn(player.getLocation(), Horse.class);
                spawnedHorses.put(player.getDisplayName(), horse);
                horse.setCustomName(ChatColor.GOLD + player.getDisplayName() + "'s horse");
                horse.setCustomNameVisible(true);
                lore.add(ChatColor.RED + "SPAWNED");
                meta.setLore(lore);
                item.setItemMeta(meta);
                horse.setTamed(true);
                horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
                horse.setColor(Horse.Color.BROWN);
                horse.setStyle(Horse.Style.NONE);
                horse.setAge(1);
            }
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player p = event.getPlayer();
        if (spawnedHorses.containsKey(p.getDisplayName())) {
            spawnedHorses.get(p.getDisplayName()).remove();
            spawnedHorses.remove(p.getDisplayName());
        }
        Inventory inv = p.getInventory();
        ItemStack currItem;
        ItemMeta meta;
        List<String> lore;
        if (!inv.contains(Material.SADDLE)) return;
        for (int i = 0; i < inv.getSize(); i++) {
            currItem = inv.getItem(i);
            if (currItem == null) continue;
            if (!(currItem.getType().equals(Material.SADDLE))) continue;
            meta = currItem.getItemMeta();
            lore = meta.getLore();
            if (lore.get(lore.size()-1).equalsIgnoreCase(ChatColor.RED + "SPAWNED")) {
                lore.remove(lore.size()-1);
                meta.setLore(lore);
                currItem.setItemMeta(meta);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Horse)) return;
        if (event instanceof EntityDamageByEntityEvent) {
            EntityDamageByEntityEvent e = (EntityDamageByEntityEvent) event;
            if (e.getDamager() instanceof Player) {
                Player p = (Player) e.getDamager();
                if (p.getGameMode() == GameMode.CREATIVE && p.getDisplayName().equalsIgnoreCase("Dimon5676")) {
                    e.getEntity().remove();
                }
            }
        }
        event.setCancelled(true);
    }

    public static void clearHorses(){
        spawnedHorses.clear();
    }
}
