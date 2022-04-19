package de.scynostv.movecraft2.core;

import de.scynostv.movecraft2.blocks.CoreBlock;
import de.scynostv.movecraft2.blocks.ShipBlock;
import de.scynostv.movecraft2.utils.BlockUtils;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ship {

    private Location location;
    private String ownerUUID;
    private List<String> shipMembersUUID = new ArrayList<>();

    private CoreBlock coreBlock;
    private List<ShipBlock> shipBlockList = new ArrayList<>(); 

    public Ship() {
        
    }

    public Ship getShipAtLocation(Location loc) {
        
        for (var block : shipBlockList) {
            if (BlockUtils.LocationsEqual(loc, block.getLocation())) {
                return this; 
            }
        }

        return null; 
    }

    public CoreBlock getCoreBlock() {
        return this.coreBlock; 
    }

}
