package de.scynostv.movecraft2.core;

import de.scynostv.movecraft2.blocks.CoreBlock;
import de.scynostv.movecraft2.blocks.ShipBlock;
import de.scynostv.movecraft2.utils.BlockUtils;
import de.scynostv.movecraft2.utils.PlayerInterface;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Ship {

    private static List<Ship> shipsLoaded = new ArrayList<>();

    public static Ship getShipAtLocation(Location loc) {
        

        for (var ship : shipsLoaded ){
            for (var block : ship.getShipBlocks()) {
                if (BlockUtils.LocationsEqual(loc, block.getLocation())) {
                    return ship; 
                }
            }
        }
        

        return null; 
    }

    public static Ship generateShipFromCoreBlock(CoreBlock block, UUID owner) {
        Ship ship = new Ship(block, owner);
        ship.addBlock(block);

        return ship; 
    }

    public static void registerShip(Ship ship) {
        shipsLoaded.add(ship);
    }



    private Location location;
    private String ownerUUID;
    private List<String> shipMembersUUID = new ArrayList<>();

    private CoreBlock coreBlock;
    private List<ShipBlock> shipBlockList = new ArrayList<>(); 
    private UUID owner;  //Storing the player as a UUID reference makes more sense in case he/she is offline.
    private boolean buildMode; 

    public Ship(UUID owner) {
        this.owner = owner; 
    }

    public Ship(CoreBlock block, UUID owner) {
        this.coreBlock = block; 
        this.owner = owner; 
    }


    public CoreBlock getCoreBlock() {
        return this.coreBlock; 
    }


    public List<ShipBlock> getShipBlocks() {
        return this.shipBlockList; 
    }

    public void addBlock(ShipBlock block) {
        this.shipBlockList.add(block); 
        block.setShip(this);
    }

    public void removeBlock(Location loc) {
        for (int i = 0; i<shipBlockList.size(); i++) { //Standard for-loop to avoid concurrent modifcation exception
            var shipBlock = shipBlockList.get(i);
            if (!BlockUtils.LocationsEqual(shipBlock.getLocation(), loc))
                continue;
            
            shipBlockList.remove(i);
        }
    }

    public UUID getOwner() {
        return this.owner; 
    }

    public Player getOwnerAsPlayer() {
        return PlayerInterface.get(this.owner).getPlayer(); 
    }

    public boolean isBuildMode() {
        return buildMode; 
    }

    public void setBuildMode(boolean b) {
        this.buildMode = b; 
    }

    public List<ShipBlock> getAllBlocksAtLocation(Location loc) {

        var list = new ArrayList<ShipBlock>(1); 

        for (var block : shipBlockList) {
            if (BlockUtils.LocationsEqual(loc, block.getLocation()))
                list.add(block);
        }

        return list;
    }

    public void move(int x, int y, int z) {
        for (var block : shipBlockList) {
            block.deleteBlock();
            block.setLocation(block.getLocation().add(x, y, z));
            block.generateBlock();
        }

        this.location = coreBlock.getLocation();
    }
}
