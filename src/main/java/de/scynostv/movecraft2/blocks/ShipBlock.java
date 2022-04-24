package de.scynostv.movecraft2.blocks;

import de.scynostv.movecraft2.core.Ship;
import de.scynostv.movecraft2.utils.BlockUtils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Container;
import org.bukkit.inventory.Inventory;

public class ShipBlock {

    public static ShipBlock make(Block block) {
        var shipBlock =  new ShipBlock(block.getLocation(), block.getBlockData().getMaterial()); 
    
        var inv = BlockUtils.getInventoryFromBlock(block);
        if (inv != null) {
            shipBlock.setInventory(inv);
            System.out.println("Set inventory");
        }
            

        return shipBlock; 
    }


    private Ship ship;
    private Location location;
    private Material blockMaterial;
    private Inventory inventory = null;  //If we are dealing with a container (e.g. chest, furnace)

    public ShipBlock(Location _location, Material _blockMaterial) {
        this.location = _location;
        this.blockMaterial = _blockMaterial;
        if (!checkBlock()) regenerateBlock();
    }

    public void deleteBlock() {
        //If during movement, another block has also been assigned this location, then filling the place with air would delete the other block
        if (ship != null && ship.getAllBlocksAtLocation(location).size() > 1) return; 
        this.location.getBlock().setType(Material.AIR);
    }

    public void generateBlock() {
        var block = this.location.getBlock(); 
        block.setType(blockMaterial);

        //If we are dealing with a container, then we want to transfer the items. 
        var blockState = block.getState();
        if (blockState instanceof Container) {
            var container = (Container) blockState;
            container.getInventory().setContents(this.inventory.getContents());
        }
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

    public void setLocation(Location loc) {
        this.location = loc; 
    }

    public void setInventory(Inventory inv) {
        this.inventory = inv; 
    }

    public void updateInventory() {
        var block = this.location.getBlock(); 
        var inv = BlockUtils.getInventoryFromBlock(block); 
        if (inv != null) inventory = inv; 
    }

    public Block getBlockAtLocation() {
        return this.location.getBlock(); 
    }

}
