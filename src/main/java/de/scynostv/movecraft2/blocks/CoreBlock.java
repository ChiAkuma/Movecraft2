package de.scynostv.movecraft2.blocks;

import de.scynostv.movecraft2.core.Ship;
import org.bukkit.Location;

public class CoreBlock {

    Ship ship;
    Location location;

    public CoreBlock(Location _location, Ship _ship) {
        this.location = _location;
        this.ship = _ship;
    }


}
