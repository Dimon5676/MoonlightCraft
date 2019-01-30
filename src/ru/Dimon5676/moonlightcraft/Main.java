package ru.Dimon5676.moonlightcraft;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ru.Dimon5676.moonlightcraft.commands.CommandManager;
import ru.Dimon5676.moonlightcraft.horse.BrownHorse;
import ru.Dimon5676.moonlightcraft.horse.HorseManager;

import java.util.ArrayList;

public class Main extends JavaPlugin {

    public ArrayList<BrownHorse> horses = new ArrayList<>();

    @Override
    public void onEnable() {
        getLogger().info("Plugin loaded successful!");
        getServer().getPluginManager().registerEvents(new HorseManager(), this);
        getCommand("testhorse").setExecutor(new CommandManager());
        getCommand("buyhorse").setExecutor(new CommandManager());

        for (Player player : getServer().getOnlinePlayers()) {
            horses.add(new BrownHorse(player));
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }
}
