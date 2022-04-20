package de.scynostv.movecraft2.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.scynostv.movecraft2.blocks.CoreBlock;
import de.scynostv.movecraft2.blocks.ShipBlock;
import de.scynostv.movecraft2.core.Ship;

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
        if (ship.getOwner() != p.getUniqueId()) return;


        var sBlock = ShipBlock.make(block); 
        ship.addBlock(sBlock);

        p.sendMessage("§6Block added to the ship!");

    }
}
