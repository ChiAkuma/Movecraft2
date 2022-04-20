package de.scynostv.movecraft2.inventories.core;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import de.scynostv.movecraft2.core.Ship;
import de.scynostv.movecraft2.utils.PlayerInterface;

public class MenuCoreListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        var p = (Player) e.getWhoClicked();
        var playerInterface = PlayerInterface.getByPlayer(p); 
        int slot = e.getSlot(); 

        if (playerInterface.getMenuShip() == null) return; 

        e.setCancelled(true);

        if (slot == MenuCore.getPos(MenuCore.POS_BUILD)) {
            onBuildClick(playerInterface.getMenuShip(), playerInterface);
        }

        else if (slot == MenuCore.getPos(MenuCore.POS_MOUNT)) {
            onMountClick(playerInterface.getMenuShip(), playerInterface);
        }
        
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        var p = (Player) e.getPlayer(); 
        var playerInterface = PlayerInterface.getByPlayer(p); 

        if (playerInterface.getMenuShip() != null)
            playerInterface.setMenuShip(null);
    }

    public void onBuildClick(Ship ship, PlayerInterface playerInterface) {
        ship.setBuildMode(!ship.isBuildMode());
        
        playerInterface.getPlayer().sendMessage("§aBuild mode set to " + ship.isBuildMode());
        playerInterface.getPlayer().closeInventory();
        
    }

    public void onMountClick(Ship ship, PlayerInterface playerInterface) {

        if (playerInterface.getMounting() == ship) {
            playerInterface.setMounting(null);
        } else {
            playerInterface.setMounting(ship);
        }

        playerInterface.getPlayer().sendMessage("§aMount mode changed to " + (playerInterface.getMounting() != null));
        playerInterface.getPlayer().closeInventory();
    }
    
}
