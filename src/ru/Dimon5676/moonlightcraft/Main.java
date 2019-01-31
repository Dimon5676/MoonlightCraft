package ru.Dimon5676.moonlightcraft;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import ru.Dimon5676.moonlightcraft.commands.CommandManager;
import ru.Dimon5676.moonlightcraft.horse.HorseMoonlight;
import ru.Dimon5676.moonlightcraft.horse.HorseManager;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends JavaPlugin {

    public ArrayList<HorseMoonlight> horses = new ArrayList<>();
    private FileConfiguration config;

    @Override
    public void onEnable() {
        config = getConfig();
        ConfigurationSerialization.registerClass(HorseMoonlight.class);
        getLogger().info("Plugin loaded successful!");
        getServer().getPluginManager().registerEvents(new HorseManager(), this);
        getCommand("testhorse").setExecutor(new CommandManager());
        getCommand("buyhorse").setExecutor(new CommandManager());
        horses = (ArrayList<HorseMoonlight>) config.get("horses", horses);
        for (HorseMoonlight name : horses) {
            getLogger().info(name.getOwner().getDisplayName());
        }
    }

    @Override
    public void onDisable() {
        config.set("horses", horses);
        saveConfig();
        getLogger().info("Plugin disabled");
    }
}
