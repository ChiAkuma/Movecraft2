package de.scynostv.movecraft2.blocks;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CoreBlock {
    Location location;
    Player owner;
    ArrayList<Player> players_access = new ArrayList<Player>();

    public CoreBlock() {

    }
}
