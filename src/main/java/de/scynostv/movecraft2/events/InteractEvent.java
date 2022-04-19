package de.scynostv.movecraft2.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.scynostv.movecraft2.core.Ship;
import de.scynostv.movecraft2.utils.BlockUtils;

public class InteractEvent {
    
   
    @EventHandler 
    public void rightClickOnShip(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return; 

        var location = e.getClickedBlock().getLocation(); 

        var ship = Ship.getShipAtLocation(location); 
        if (ship == null) return;  //We only want to continue if the block is of a ship

        coreBlockRightClick(e, ship); //All the actions relating to right clicking the core block
    } 


    public void coreBlockRightClick(PlayerInteractEvent e, Ship ship) {
        var location = e.getClickedBlock().getLocation(); 

        var coreblock = ship.getCoreBlock(); 
        if (!BlockUtils.LocationsEqual(coreblock.getLocation(), location)) return; //We only want to continue, if its the core block


        var p = e.getPlayer(); 
        p.sendMessage("&cThis is the coreblock of a ship.");
    }

}