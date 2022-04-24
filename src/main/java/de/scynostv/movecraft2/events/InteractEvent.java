package de.scynostv.movecraft2.events;

import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import de.scynostv.movecraft2.core.Ship;
import de.scynostv.movecraft2.inventories.core.MenuCore;
import de.scynostv.movecraft2.utils.BlockUtils;

public class InteractEvent implements Listener {
    
   
    @EventHandler 
    public void rightClickOnShip(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return; 

        var location = e.getClickedBlock().getLocation(); 

        var ship = Ship.getShipAtLocation(location); 
        if (ship == null) return;  //We only want to continue if the block is of a ship

        coreBlockRightClick(e, ship); //All the actions relating to right clicking the core block
        containerOpen(e, ship); //In case the container is being opened while the ship is mounted
    } 


    public void coreBlockRightClick(PlayerInteractEvent e, Ship ship) {
        var location = e.getClickedBlock().getLocation(); 
        var p = e.getPlayer(); 
        var coreblock = ship.getCoreBlock(); 

        if (p.isSneaking()) return; //If the player is sneaking, the menu should not open
        if (!BlockUtils.LocationsEqual(coreblock.getLocation(), location)) return; //We only want to continue, if it's the core block
        
        var menu = new MenuCore(ship);
        menu.open(p);

        p.sendMessage("§cThis is the coreblock of a ship.");
    }

    public void containerOpen(PlayerInteractEvent e, Ship ship) {
        var block = e.getClickedBlock();
        var blockState = block.getState(); 
        var p = e.getPlayer(); 

        if (!ship.isMounted()) return; 

        if (!(blockState instanceof Container)) return; 

        e.setCancelled(true);
        
        p.sendMessage("§4This cannot be opened while the ship is mounted.");

    }

}