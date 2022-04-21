package de.scynostv.movecraft2.events;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import de.scynostv.movecraft2.utils.PlayerInterface;

public class SneakEvent implements Listener{
    
    @EventHandler
    public void onSneakToggle(PlayerToggleSneakEvent e) {
        var p = e.getPlayer(); 
        var playerInterface = PlayerInterface.getByPlayer(p);
        
        if (!e.isSneaking()) return; 

        var ship = playerInterface.getMounting();
        if (ship == null) return; 

        ship.move(0, -1, 0);

        /*
            If the ship was simply moving one block down, then the player would fall down one block. Therefore, his position would
            changed resulting in the ship moving down again, resulting in an infinite loop. 
            Teleporting the player down, fixes this issue. 
        */
        var locPlayer = p.getLocation(); 
        var newLoc = new Location(locPlayer.getWorld(), locPlayer.getX(), locPlayer.getY()-1, locPlayer.getZ());
        newLoc.setYaw(locPlayer.getYaw()); //Setting yaw and pitch, so that the player's head stays oriented the same way.
        newLoc.setPitch(locPlayer.getPitch());
        p.teleport(newLoc);  
    }

}
