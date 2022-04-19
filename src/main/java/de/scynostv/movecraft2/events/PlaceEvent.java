package de.scynostv.movecraft2.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import de.scynostv.movecraft2.blocks.CoreBlock;
import de.scynostv.movecraft2.core.Ship;

public class PlaceEvent implements Listener {


    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        
        checkCorePlacement(e);

    }

    /* All the different uses for block placing will be offloaded below... */


    public void checkCorePlacement(BlockPlaceEvent e) {
        if (e.getBlock().getBlockData().getMaterial() != Material.BEDROCK) return; 

        CoreBlock block = new CoreBlock(e.getBlock().getLocation()); 

        //Creating new ship right here
        Ship ship = Ship.generateShipFromCoreBlock(block); 
        Ship.registerShip(ship);

        var p = e.getPlayer(); 
        p.sendMessage("§c Ship created!");

    }
}
