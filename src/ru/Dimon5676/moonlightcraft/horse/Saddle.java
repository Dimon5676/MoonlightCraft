package ru.Dimon5676.moonlightcraft.horse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Saddle extends ItemStack {
    String color;
    int lvl;
    double speed;
    double jump;
    int xp;
    boolean armor;

    List<String> lore = new ArrayList<>();

    public Saddle(String color, int lvl, double speed, double jump, int xp, boolean armor) {
        super(Material.SADDLE);
        this.color = color;
        this.lvl = lvl;
        this.speed = speed;
        this.jump = jump;
        this.xp = xp;
        this.armor = armor;

        ItemMeta meta = this.getItemMeta();
        lore.add(ChatColor.GREEN + "LVL: " + lvl);
        lore.add(ChatColor.GOLD + "Speed: " + speed);
        lore.add(ChatColor.GOLD + "Jump: " + jump);
        lore.add(ChatColor.AQUA + "XP: " + xp);
        if (armor) {
            lore.add(ChatColor.DARK_PURPLE + "Armor: equipped");
        } else {
            lore.add(ChatColor.DARK_PURPLE + "Armor: NONE");
        }

        meta.setDisplayName(color);
        meta.setLore(lore);
        this.setItemMeta(meta);
    }
}
