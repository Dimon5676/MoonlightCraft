package ru.Dimon5676.moonlightcraft;

import org.bukkit.plugin.java.JavaPlugin;
import ru.Dimon5676.moonlightcraft.commands.CommandManager;
import ru.Dimon5676.moonlightcraft.horse.HorseManager;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Registering event listeners and commands executor");
        getServer().getPluginManager().registerEvents(new HorseManager(this), this);
        getCommand("testhorse").setExecutor(new CommandManager());
        getCommand("buyhorse").setExecutor(new CommandManager());
        getLogger().info("Done");
        getLogger().info("Plugin loaded successful!");
    }

    @Override
    public void onDisable() {
        HorseManager.clearHorses();
        getLogger().info("Plugin disabled");
    }
}
