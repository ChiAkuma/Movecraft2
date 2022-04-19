package de.scynostv.movecraft2.core;

import org.bukkit.plugin.java.JavaPlugin;
import de.scynostv.movecraft2.commands.*;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.getCommand("ship").setExecutor(new CMD_ShipBuild());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
