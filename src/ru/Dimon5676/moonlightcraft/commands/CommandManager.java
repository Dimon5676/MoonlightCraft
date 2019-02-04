package ru.Dimon5676.moonlightcraft.commands;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import ru.Dimon5676.moonlightcraft.ItemCreator;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only players can use this command");
            return true;
        }

        Player player = ((Player) commandSender).getPlayer();
        Inventory plInv = player.getInventory();

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "LVL: 1");

        switch (command.getName()) {
            case "testhorse":
                plInv.addItem(ItemCreator.create(Material.SADDLE, 1, ChatColor.GREEN + "Brown Horse", lore));
                return true;
            case "buyhorse":
                ItemStack price = new ItemStack(Material.EMERALD_BLOCK);
                price.setAmount(64);
                if (plInv.contains(price)) {
                    plInv.remove(price);
                    plInv.addItem(ItemCreator.create(Material.SADDLE, 1, ChatColor.GREEN + "Brown Horse", lore));
                    Firework fw = player.getWorld().spawn(player.getLocation(), Firework.class);
                    FireworkMeta fwMeta = fw.getFireworkMeta();
                    fwMeta.addEffect(FireworkEffect.builder().withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).withColor(Color.RED, Color.BLUE, Color.AQUA).build());
                    fw.setFireworkMeta(fwMeta);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Вы не можете себе это позволить");
                    return true;
                }
        }
        return true;
    }
}
