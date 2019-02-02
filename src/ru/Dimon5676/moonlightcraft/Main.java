package ru.Dimon5676.moonlightcraft;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;
import ru.Dimon5676.moonlightcraft.commands.CommandManager;
import ru.Dimon5676.moonlightcraft.horse.HorseMoonlight;
import ru.Dimon5676.moonlightcraft.horse.HorseManager;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public ArrayList<HorseMoonlight> horses = new ArrayList<HorseMoonlight>();
    private FileConfiguration config;

    @Override
    public void onEnable() {
        getLogger().info("Registering serialization");
        ConfigurationSerialization.registerClass(HorseMoonlight.class);
        getLogger().info("Getting config");
        config = getConfig();
        getLogger().info("Config has been got");
        getLogger().info("Registering event listeners and commands executor");
        getServer().getPluginManager().registerEvents(new HorseManager(), this);
        getCommand("testhorse").setExecutor(new CommandManager());
        getCommand("buyhorse").setExecutor(new CommandManager());
        getLogger().info("Done");

        getLogger().info("Getting horses from config");
        horses = (ArrayList<HorseMoonlight>) config.get("horses", horses);
        getLogger().info("Done");
        getLogger().info("Plugin loaded successful!");
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i) == null) {
                horses.remove(i);
                i--;
            }
        }

        getLogger().info("Horses size: " + horses.size());
    }

    @Override
    public void onDisable() {
        getLogger().info("Saving horses");
        config.set("horses", horses);
        saveConfig();
        getLogger().info("Done");
        getLogger().info("Plugin disabled");
    }
}
