package de.scynostv.movecraft2.utils;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerInterface {

    private static Map playerMap = new HashMap<UUID, PlayerInterface>();

    public static void addPlayer(Player player) {
        playerMap.put(player.getUniqueId(), new PlayerInterface(player));
        
        
        
    }
    
    public static void deletePlayer(Player player) {
        
        var id = player.getUniqueId(); 
        
        if (!playerMap.containsKey(id)) return; 
        
        playerMap.remove(player.getUniqueId());
    }



    private Player playerReference;

    public PlayerInterface(Player player) {
        this.playerReference = player;
    }



}
