package de.scynostv.movecraft2.core;

import de.scynostv.movecraft2.blocks.CoreBlock;
import de.scynostv.movecraft2.blocks.ShipBlock;
import org.bukkit.Location;

import java.util.ArrayList;

public class Ship {

    Location location;
    String ownerUUID;
    ArrayList<String> shipMembersUUID = new ArrayList<String>();

    CoreBlock coreBlock;
    ArrayList<ShipBlock> shipBlockList = new ArrayList<ShipBlock>();

    public Ship() {

    }

}
