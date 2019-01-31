package ru.Dimon5676.moonlightcraft.horse;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class HorseMoonlight implements ConfigurationSerializable {
    private Horse horse;
    private double maxSpeed = 10;
    private double currentSpeed = 1;
    private double maxJump = 10;
    private double currentJump = 0.6;
    private Horse.Color color = Horse.Color.BROWN;
    private int xp = 0;
    protected Player owner;

    protected boolean isSpawned = false;

    public HorseMoonlight(Map<String, Object> map) {
        this.horse = (Horse) map.get("Horse");
        this.maxSpeed = Double.valueOf((String) map.get("maxSpeed"));
        this.currentSpeed = Double.valueOf((String) map.get("currentSpeed"));
        this.maxJump = Double.valueOf((String) map.get("maxJump"));
        this.currentJump = Double.valueOf((String) map.get("currentJump"));
        this.color = (Horse.Color) map.get("Color");
        this.xp = (int) map.get("xp");
        this.owner = (Player) map.get("owner");
        this.isSpawned = (boolean) map.get("isSpawned");
    }

    public Horse getHorse() {
        return horse;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public double getMaxJump() {
        return maxJump;
    }

    public void setMaxJump(double maxJump) {
        this.maxJump = maxJump;
    }

    public double getCurrentJump() {
        return currentJump;
    }

    public Horse.Color getColor() {
        return color;
    }

    public void setColor(Horse.Color color) {
        this.color = color;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public HorseMoonlight(Player owner) {
        this.owner = owner;
    }

    protected void spawn() {
        if (isSpawned) return;
        horse = owner.getWorld().spawn(owner.getLocation(), Horse.class);
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

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Horse", horse);
        map.put("maxSpeed", String.valueOf(maxSpeed));
        map.put("currentSpeed", String.valueOf(currentSpeed));
        map.put("maxJump", String.valueOf(maxJump));
        map.put("currentJump", String.valueOf(currentJump));
        map.put("Color", color);
        map.put("xp", xp);
        map.put("owner", owner);
        map.put("isSpawned", isSpawned);
        return map;
    }

    public static HorseMoonlight deserialize(Map<String, Object> map) {
        return new HorseMoonlight(map);
    }
}
