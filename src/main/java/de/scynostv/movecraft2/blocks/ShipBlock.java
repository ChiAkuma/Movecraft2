package de.scynostv.movecraft2.blocks;

import de.scynostv.movecraft2.core.Ship;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class ShipBlock {

    public static ShipBlock make(Block block) {
        return new ShipBlock(block.getLocation(), block.getBlockData().getMaterial()); 
    }


    private Ship ship;
    private Location location;
    private Material blockMaterial;

    public ShipBlock(Location _location, Material _blockMaterial) {
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

    public void setShip(Ship ship) {
        this.ship = ship; 
    }

    public Ship getShip() {
        return this.ship; 
    }

    public boolean checkBlock() {
        if (blockMaterial.equals(this.location.getBlock().getType())) return true;
        return false;
    }

    public Location getLocation() {
        return this.location; 
    }

}
