package ru.Dimon5676.moonlightcraft.horse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import ru.Dimon5676.moonlightcraft.ItemCreator;

import java.util.ArrayList;

public class SaddleCreator {

    public static ItemStack create(String color, int lvl, double speed, double jump, int xp, boolean armor) {
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GREEN + "LVL: " + lvl);
        lore.add(ChatColor.GOLD + "Speed: " + speed);
        lore.add(ChatColor.GOLD + "Jump: " + jump);
        lore.add(ChatColor.AQUA + "XP: " + xp);
        if (armor) {
            lore.add(ChatColor.DARK_PURPLE + "Armor: equipped");
        } else {
            lore.add(ChatColor.DARK_PURPLE + "Armor: NONE");
        }
        return ItemCreator.create(Material.SADDLE, 1, color, lore);
    }
}
