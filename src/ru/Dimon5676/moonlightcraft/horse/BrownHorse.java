package ru.Dimon5676.moonlightcraft.horse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BrownHorse {
    private double maxSpeed = 10;
    private double currentSpeed = 1;
    private double maxJump = 10;
    private double currentJump = 0.6;
    private Horse.Color color = Horse.Color.BROWN;
    private Horse horse;
    protected Player owner;

    protected boolean isSpawned = false;

    private int xp = 0;

    public BrownHorse(Player owner) {
        this.owner = owner;
    }

    protected void spawn() {
        if (isSpawned) return;
        horse = owner.getWorld().spawn(owner.getLocation(), Horse.class);
        horse.setColor(color);
        horse.setJumpStrength(currentJump);
        horse.setCustomNameVisible(true);
        horse.setStyle(Horse.Style.NONE);

        Inventory horseInv = horse.getInventory();
        horseInv.addItem(new ItemStack(Material.SADDLE));

        horse.setAge(1);
        horse.setTamed(true);
        horse.setCustomName(ChatColor.GOLD + owner.getDisplayName() + "'s horse");
        isSpawned = true;
    }

    protected void hide() {
        if (!(isSpawned)) return;
        horse.remove();
        isSpawned = false;
    }
}
