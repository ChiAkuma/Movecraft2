package de.scynostv.movecraft2.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import de.scynostv.movecraft2.utils.BlockUtils;
import de.scynostv.movecraft2.utils.PlayerInterface;

public class MoveEvent implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        handleMountedMove(e);
    }   
    
    public void handleMountedMove(PlayerMoveEvent e) {
        var p = e.getPlayer(); 
        var playerInterface = PlayerInterface.getByPlayer(p); 
        var ship = playerInterface.getMounting();

        if (ship == null) return; 

        var from = e.getFrom(); 
        var to = e.getTo(); 

        //Only move the ship if the player has moved one full block.
        if (BlockUtils.LocationsEqual(from, to)) return; 

        //Calculating the direction. For some reason to.subtract(from) does NOT work.
        int fromX = from.getBlockX(), fromY = from.getBlockY(), fromZ = from.getBlockZ(); 
        int toX = to.getBlockX(), toY = to.getBlockY(), toZ = to.getBlockZ(); 
        int x = toX-fromX, y = toY-fromY, z = toZ-fromZ; 

        ship.move(x, y, z);
    } 
}
