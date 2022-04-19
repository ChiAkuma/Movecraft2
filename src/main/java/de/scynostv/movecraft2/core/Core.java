package de.scynostv.movecraft2.core;

import org.bukkit.plugin.java.JavaPlugin;
import de.scynostv.movecraft2.commands.*;

import java.util.Objects;

public final class Core extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Objects.requireNonNull(this.getCommand("ship")).setExecutor(new CMD_ShipBuild());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
