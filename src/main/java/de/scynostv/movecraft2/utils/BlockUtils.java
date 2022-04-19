package de.scynostv.movecraft2.utils;

import org.bukkit.Location;

public class BlockUtils {

    public static boolean LocationsEqual(Location a, Location b)
    {
        return (a.getBlockX() == b.getBlockX() &&
                a.getBlockY() == b.getBlockY() &&
                b.getBlockZ() == b.getBlockZ()); 
    }

    
}
