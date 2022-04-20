package de.scynostv.movecraft2.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import de.scynostv.movecraft2.utils.PlayerInterface;

public class CMD_ShipBuild implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) return false; 
        var p = (Player) sender; 
        var playerInterface = PlayerInterface.getByPlayer(p);


        /*
        
            THIS COMMAND IS ONLY FOR TEST PURPOSES!
        */
        if (args.length >= 1) 
        {
            if (args[0].equalsIgnoreCase("movetest")) {
                var ship = playerInterface.getMounting();

                if (ship == null) {
                    p.sendMessage("§4Mount a ship first!");
                    return true; 
                }

                ship.move(0, 1, 0);
                return true;
            }
        }
        return false;
    }
}
