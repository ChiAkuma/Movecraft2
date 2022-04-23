package de.scynostv.movecraft2.inventories.core;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import de.scynostv.movecraft2.core.Ship;
import de.scynostv.movecraft2.utils.PlayerInterface;

public class MenuCore {

    public final static int[] POS_BUILD = {1,2};
    public final static int[] POS_MOUNT = {1,5};
    public static int getPos(int[] arr) {
        return arr[0]*9+arr[1];
    } 

    private Ship ship; 
    public MenuCore(Ship ship) {
        this.ship = ship; 
    }

    public void open(Player player) {
        var playerInterface = PlayerInterface.getByPlayer(player);

        if (playerInterface.getMenuShip() != null) return; //If its not null, then there is already a menu opened.

        playerInterface.setMenuShip(this.ship);

        var isBuild = new ItemStack(Material.IRON_PICKAXE);
        var isMount = new ItemStack(Material.DIAMOND_HORSE_ARMOR);

        var mBuild = isBuild.getItemMeta(); 
        var mMount = isMount.getItemMeta(); 

        mBuild.displayName(Component.text("§aBuild"));
        mMount.displayName(Component.text("§aMount"));

        isBuild.setItemMeta(mBuild);
        isMount.setItemMeta(mMount);


        Inventory inventory = Bukkit.createInventory(player, InventoryType.PLAYER);
        inventory.setItem(getPos(POS_BUILD), isBuild);
        inventory.setItem(getPos(POS_MOUNT), isMount);

        player.openInventory(inventory);

    }
}
