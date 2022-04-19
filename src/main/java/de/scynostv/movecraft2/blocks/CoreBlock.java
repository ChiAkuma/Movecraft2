package de.scynostv.movecraft2.blocks;

import de.polygondev.inventoryapi.inventory.Inventory;
import de.scynostv.movecraft2.core.Ship;
import org.bukkit.Location;
import org.bukkit.Material;

public class CoreBlock extends ShipBlock{

    public CoreBlock(Location _location) {
        super(_location, Material.REDSTONE_BLOCK);
    }

    public Inventory touch() {
        return null;
    }
}
