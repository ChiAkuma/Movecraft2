package de.scynostv.movecraft2.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import de.scynostv.movecraft2.core.Ship;

public class PlayerInterface {
    private static Map<UUID, PlayerInterface> playerMap = new HashMap<>();

    public static void add(Player player) {
        playerMap.put(player.getUniqueId(), new PlayerInterface(player)); 
    }

    public static void delete(Player player) {
        var id = player.getUniqueId(); 

        if (!playerMap.containsKey(id)) return;

        playerMap.remove(id);
    }

    public static void addAll() {
        Bukkit.getOnlinePlayers().forEach(PlayerInterface::add);
    }

    public static PlayerInterface get(UUID id) {
        return (PlayerInterface) playerMap.get(id);
    }

    public static PlayerInterface getByName(String name) {
        for (var key : playerMap.keySet()) {
            var pif = (PlayerInterface) playerMap.get(key);

            if (pif.getPlayer().getName().equalsIgnoreCase(name)) return pif; 
        }

        return null; 
    }

    public static PlayerInterface getByPlayer(Player player) {
        var id = player.getUniqueId(); 

        if (playerMap.containsKey(id)) 
        return playerMap.get(id); 

        return null; 
    }
    
    
    private Player playerReference; 

    private Ship mounting = null; 
    private Ship menuShip = null; 
    public PlayerInterface(Player player) {
        this.playerReference = player; 
    }

    public Player getPlayer() {
        return this.playerReference; 
    }

    public Ship getMounting() {
        return this.mounting; 
    }

    public void setMounting(Ship ship) {
        this.mounting = ship; 
    }

    public void mount(Ship ship) {
        if (ship == mounting) {
            mounting = null;
            ship.setMounted(false);
            return;
        }

        if (ship.isMounted()) return;

        mounting = ship;
        ship.setMounted(true);


        var locCore = ship.getCoreBlock().getLocation(); 
        var locTarget = new Location(locCore.getWorld(), locCore.getX(), locCore.getY()+1, locCore.getZ()); //Location that is on top of the core block.
        playerReference.teleport(locTarget);
        
        ship.closeAllInventories();
        ship.updateAllInventories();

    }

    public void setMenuShip(Ship ship){
        this.menuShip = ship;
    }

    public Ship getMenuShip() {
        return this.menuShip; 
    }
}
