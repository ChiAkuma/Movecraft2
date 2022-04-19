package de.scynostv.movecraft2.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

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

    public static Player getByPlayer(Player player) {
        var id = player.getUniqueId(); 

        if (playerMap.containsKey(id)) 
        return (Player) playerMap.get(id); 

        return null; 
    }
    
    
    private Player playerReference; 
    public PlayerInterface(Player player) {
        this.playerReference = player; 
    }

    public Player getPlayer() {
        return this.playerReference; 
    }
}
