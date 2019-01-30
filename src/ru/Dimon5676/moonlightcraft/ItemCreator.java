package ru.Dimon5676.moonlightcraft;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemCreator {

    public static ItemStack create(Material material, int amount, String name, ArrayList<String> lore) {
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack create(Material material, int amount, String name) {
        ArrayList<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GREEN + "Created by Moonlight");
        return create(material, amount, name, lore);
    }
}
