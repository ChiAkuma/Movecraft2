package de.scynostv.movecraft2.blocks;

import de.scynostv.movecraft2.core.Ship;
import org.bukkit.Location;
import org.bukkit.Material;

public class ShipBlock {

    Ship ship;
    Location location;
    Material blockMaterial;


    public ShipBlock(Ship _ship, Location _location, Material _blockMaterial) {
        this.ship = _ship;
        this.location = _location;
        this.blockMaterial = _blockMaterial;
        if (!checkBlock()) regenerateBlock();
    }

    public void deleteBlock() {
        this.location.getBlock().setType(Material.AIR);
    }

    public void generateBlock() {
        this.location.getBlock().setType(blockMaterial);
    }

    public void regenerateBlock() {
        deleteBlock();
        generateBlock();
    }

    public boolean checkBlock() {
        if (blockMaterial.equals(this.location.getBlock().getType())) return true;
        return false;
    }

    public Location getLocation() {
        return this.location; 
    }

}
