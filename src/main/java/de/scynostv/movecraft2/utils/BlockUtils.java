package de.scynostv.movecraft2.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;

public class BlockUtils {

    public static boolean LocationsEqual(Location a, Location b)
    {
        return (a.getBlockX() == b.getBlockX() &&
                a.getBlockY() == b.getBlockY() &&
                a.getBlockZ() == b.getBlockZ()); 
    }

    public static Inventory getInventoryFromBlock(Block block) {
        System.out.println("Checking inventory of block " + block.getBlockData().getMaterial().name());


        BlockState bs = block.getState(); 
        if (!(bs instanceof Container)) return null; 

        System.out.println("Check of inventory successfull!");
        var container = (Container) bs; 
        return container.getInventory();
    }
}
