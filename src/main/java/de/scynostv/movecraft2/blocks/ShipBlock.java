package de.scynostv.movecraft2.blocks;

import de.scynostv.movecraft2.core.Ship;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class ShipBlock {

    Ship ship;
    Location location;
    Material blockMaterial;


    public ShipBlock(Ship _ship, Location _location, Material _blockMaterial) {
        this.ship = _ship;
        this.location = _location;
        this.blockMaterial = _blockMaterial;
    }

    public void deleteBlock() {
        this.location.getBlock().setType(Material.AIR);
    }

    public void generateBlock() {
        this.location.getBlock().setType(blockMaterial);
    }

}
