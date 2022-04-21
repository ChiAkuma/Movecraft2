package de.scynostv.movecraft2.events;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.scynostv.movecraft2.blocks.CoreBlock;
import de.scynostv.movecraft2.blocks.ShipBlock;
import de.scynostv.movecraft2.core.Ship;
import de.scynostv.movecraft2.utils.BlockUtils;

public class PlaceEvent implements Listener {


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        
        checkCorePlacement(e);
        checkBlockExtensionPlacement(e);
    }

    /* All the different uses for block placing will be offloaded below... */


    public void checkCorePlacement(BlockPlaceEvent e) {
        if (e.getBlock().getBlockData().getMaterial() != Material.BEDROCK) return; 
        var p = e.getPlayer();

        CoreBlock block = new CoreBlock(e.getBlock().getLocation()); 

        //Creating new ship right here
        Ship ship = Ship.generateShipFromCoreBlock(block, p.getUniqueId()); 
        Ship.registerShip(ship);
 
        p.sendMessage("§c Ship created!");

    }


    public void checkBlockExtensionPlacement(BlockPlaceEvent e) {
        var block = e.getBlock(); 
        var p = e.getPlayer(); 

        var blockAgainst = e.getBlockAgainst(); //The block, the block being placed is placed at
        if (blockAgainst == null) return; //Not sure, wether there is a scenario, where this block is null but just in case.
        
        //Is the block placed to a ship, owned by the player? 
        var ship = Ship.getShipAtLocation(blockAgainst.getLocation());
        if (ship == null) return; 


        if (ship.getOwner() != p.getUniqueId()) {
            p.sendMessage("§4You cannot modify other people's ships!");
            e.setCancelled(true);
            return;
        } 

        if (!ship.isBuildMode()) {
            p.sendMessage("§4Enable build mode first!");
            e.setCancelled(true);
            return;
        }

        //Checking, whether the block is above the core.
        var coreLoc = ship.getCoreBlock().getLocation();
        for (int i = 1; i<=2; i++) {
            Location locAbove = new Location(coreLoc.getWorld(), coreLoc.getX(), coreLoc.getY()+i, coreLoc.getZ());
            if (BlockUtils.LocationsEqual(locAbove, block.getLocation())) {
                p.sendMessage("§4You have to have at least two free blocks above the core!");
                e.setCancelled(true);
                return;
            }
        }
        
        var sBlock = ShipBlock.make(block); 
        ship.addBlock(sBlock);

        p.sendMessage("§6Block added to the ship!");

    }
}
