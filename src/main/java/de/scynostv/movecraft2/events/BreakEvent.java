package de.scynostv.movecraft2.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import de.scynostv.movecraft2.core.Ship;
import de.scynostv.movecraft2.utils.BlockUtils;

public class BreakEvent implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        checkShipBlockBreaking(e);
    }

    public void checkShipBlockBreaking(BlockBreakEvent e) {
        var block = e.getBlock(); 
        var loc = block.getLocation(); 
        var p = e.getPlayer();

        var ship = Ship.getShipAtLocation(loc); 
        if (ship == null) return;

        if (ship.getOwner() != p.getUniqueId()) {
            p.sendMessage("§4You cannot modify other people's ships!");
            e.setCancelled(true);
            return;
        } 
        
        //Only allow damaging in build mode.
        if (!ship.isBuildMode()) {
            p.sendMessage("§4Enable build mode first!");
            e.setCancelled(true);
            return;
        }

        //The core block shoudn't be breakable for now
        if (BlockUtils.LocationsEqual(ship.getCoreBlock().getLocation(), loc)) {
            p.sendMessage("§4The core block cannot be broken!");
            e.setCancelled(true);
            return; 
        }

        ship.removeBlock(loc);
        p.sendMessage("§aBlock removed from ship!");
    }
    
}
