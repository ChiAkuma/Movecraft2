package de.scynostv.movecraft2.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import de.scynostv.movecraft2.utils.PlayerInterface;


public class PlayerInterfaceEventResolver implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        PlayerInterface.add(e.getPlayer());
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent e) {
        PlayerInterface.delete(e.getPlayer());
    }
    
}
