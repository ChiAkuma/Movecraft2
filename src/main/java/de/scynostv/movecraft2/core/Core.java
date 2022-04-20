package de.scynostv.movecraft2.core;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.scynostv.movecraft2.commands.*;
import de.scynostv.movecraft2.events.BreakEvent;
import de.scynostv.movecraft2.events.InteractEvent;
import de.scynostv.movecraft2.events.PlaceEvent;
import de.scynostv.movecraft2.events.PlayerInterfaceEventResolver;
import de.scynostv.movecraft2.inventories.core.MenuCoreListener;
import de.scynostv.movecraft2.utils.PlayerInterface;

import java.util.Objects;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("ship")).setExecutor(new CMD_ShipBuild());
        registerEvents();


        PlayerInterface.addAll();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerEvents() {
        var manager = Bukkit.getPluginManager();
        manager.registerEvents(new PlaceEvent(), this);
        manager.registerEvents(new PlayerInterfaceEventResolver(), this);
        manager.registerEvents(new InteractEvent(), this);
        manager.registerEvents(new MenuCoreListener(), this);
        manager.registerEvents(new BreakEvent(), this);

    }
}
